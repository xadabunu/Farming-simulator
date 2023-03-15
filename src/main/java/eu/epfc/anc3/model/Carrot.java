package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Carrot extends Growable {

    private final ObjectProperty<CarrotStates> stateProperty = new SimpleObjectProperty<>();

    public Carrot(boolean onGrass) {
        super(onGrass);
        stateProperty.set(new CarrotState1(this));
    }

    @Override
    void fertilize() {
        if (state.ordinal() < 2) {
            state = GrowingState.STATE_3;
            stateProperty().set(GrowingState.STATE_3);
            stateProperty.set(new CarrotState3(this));
        }
    }

    int grow() {
        return stateProperty.get().canGrow() ? 0 : stateProperty.get().reap();
    }

    int reap() {
        int score = stateProperty.get().reap();
        stateProperty.set(null);
        return score;
    }

    void changeState() {
        stateProperty.set(stateProperty.get().grow());
        this.stateProperty().set(
                switch (stateProperty().get()){
                    case STATE_1 -> GrowingState.STATE_2;
                    case STATE_2 -> GrowingState.STATE_3;
                    case STATE_3 -> GrowingState.STATE_4;
                    case STATE_4 -> GrowingState.ROTTEN;
                    case ROTTEN -> null;
                }
            );
    }
}

abstract class CarrotStates implements State {

    final int maximum_score = 100;
    private final int duration;
    final Carrot carrot;
    static GrowingState growingState;

    int age = 0;

    CarrotStates(int duration, Carrot carrot) {
        this.duration = duration;
        this.carrot = carrot;
    }

    int reap() {
        return getScore();
    }

    boolean canGrow() {
        ++age;
        if (age == duration) {
            carrot.changeState();
            return growingState != null;
        }
        return true;
    }

    abstract CarrotStates grow();
}

class CarrotState1 extends CarrotStates {

    private static final int duration = 3;

    CarrotState1(Carrot carrot) {
        super(duration, carrot);
        growingState = GrowingState.STATE_1;
    }

    @Override
    public int getScore() {
        return maximum_score / 10;
    }

    @Override
    public CarrotStates grow() {
        return new CarrotState2(carrot);
    }
}

class CarrotState2 extends CarrotStates {

    private static final int duration = 3;

    CarrotState2(Carrot carrot) {
        super(duration, carrot);
        growingState = GrowingState.STATE_2;
    }

    @Override
    public int getScore() {
        return maximum_score / 5;
    }

    @Override
    public CarrotStates grow() {
        return new CarrotState3(carrot);
    }
}

class CarrotState3 extends CarrotStates {

    private static final int duration = 3;

    CarrotState3(Carrot carrot) {
        super(duration, carrot);
        growingState = GrowingState.STATE_3;
    }

    @Override
    public int getScore() {
        return maximum_score / 2;
    }

    @Override
    public CarrotStates grow() {
        return new CarrotState4(carrot);
    }
}

class CarrotState4 extends CarrotStates {

    private static final int duration = 3;

    CarrotState4(Carrot carrot) {
        super(duration, carrot);
        growingState = GrowingState.STATE_4;
    }

    @Override
    public int getScore() {
        return maximum_score;
    }

    @Override
    public CarrotStates grow() {
        return new RottenCarrotState(carrot);
    }
}

class RottenCarrotState extends CarrotStates {

    private static final int duration = 10;

    RottenCarrotState(Carrot carrot) {
        super(duration, carrot);
        growingState = GrowingState.ROTTEN;
    }

    @Override
    CarrotStates grow() {
        carrot.stateProperty().set(null);
        growingState = null;
        return this;
    }

    @Override
    public int getScore() {
        return - (maximum_score * age) / 10;
    }
}

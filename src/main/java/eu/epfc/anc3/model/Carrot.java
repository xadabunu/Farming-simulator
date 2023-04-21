package eu.epfc.anc3.model;

import javafx.beans.property.SimpleObjectProperty;

class Carrot extends Growable {

    Carrot(boolean onGrass) {
        super(onGrass);
        stateProp.set(new CarrotState1(this));
        stateProp.addListener((obs, oldV, newV) -> {
            if (newV != null)
                growingStateProperty.set(newV.getGrowingState().get());
        });
    }

    Carrot(Growable growable) {
        super(growable);
        stateProp.addListener((obs, oldV, newV) -> {
            if (newV != null)
                growingStateProperty.set(newV.getGrowingState().get());
        });
        switch (growable.growingStateProperty.get()) {
            case STATE_1 -> this.stateProp.set(new CarrotState1(this));
            case STATE_2 -> this.stateProp.set(new CarrotState2(this));
            case STATE_3 -> this.stateProp.set(new CarrotState3(this));
            case STATE_4 -> this.stateProp.set(new CarrotState4(this));
            case ROTTEN -> this.stateProp.set(new RottenCarrotState(this));
        }
        int agge = growable.getAge();
        this.stateProp.get().setAge(agge);

        this.growingStateProperty.set(growable.growingStateProperty.get());
    }

    @Override
    void setStateProp(State state) {
        stateProp.set(state);
    }
}

abstract class CarrotStates implements State {

    final int maximum_score = 100;
    private final int duration;
    final Carrot carrot;
    final SimpleObjectProperty<GrowingState> growingState = new SimpleObjectProperty<>();

    @Override
    public SimpleObjectProperty<GrowingState> getGrowingState() {
        return growingState;
    }

    int age = 0;

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }


    CarrotStates(int duration, Carrot carrot) {
        this.duration = duration;
        this.carrot = carrot;
    }
}

class CarrotState1 extends CarrotStates {

    private static final int duration = 3;

    CarrotState1(Carrot carrot) {
        super(duration, carrot);
        growingState.set(GrowingState.STATE_1);
    }

    @Override
    public int reap() {
        return maximum_score / 10;
    }

    @Override
    public void fertilize() {
        carrot.setStateProp(new CarrotState3(carrot));
    }

    @Override
    public int grow() {
        ++age;
        if (age == duration) {
            carrot.setStateProp(new CarrotState2(carrot));
        }
        return 0;
    }
}

class CarrotState2 extends CarrotStates {

    private static final int duration = 3;

    CarrotState2(Carrot carrot) {
        super(duration, carrot);
        growingState.set(GrowingState.STATE_2);
    }

    @Override
    public int reap() {
        return maximum_score / 5;
    }

    @Override
    public int grow() {
        ++age;
        if (age == duration) {
            carrot.setStateProp(new CarrotState3(carrot));
        }
        return 0;
    }

    @Override
    public void fertilize() {
        carrot.setStateProp(new CarrotState3(carrot));
    }
}

class CarrotState3 extends CarrotStates {

    private static final int duration = 3;

    CarrotState3(Carrot carrot) {
        super(duration, carrot);
        growingState.set(GrowingState.STATE_3);
    }


    @Override
    public int reap() {
        return maximum_score / 2;
    }

    @Override
    public void fertilize() {

    }

    @Override
    public int grow() {
        ++age;
        if (age == duration) {
            carrot.setStateProp(new CarrotState4(carrot));
        }
        return 0;
    }
}

class CarrotState4 extends CarrotStates {

    private static final int duration = 3;

    CarrotState4(Carrot carrot) {
        super(duration, carrot);
        growingState.set(GrowingState.STATE_4);
    }


    @Override
    public int reap() {
        return maximum_score;
    }

    @Override
    public void fertilize() {

    }

    @Override
    public int grow() {
        ++age;
        if (age == duration) {
            carrot.setStateProp(new RottenCarrotState(carrot));
        }
        return 0;
    }
}

class RottenCarrotState extends CarrotStates {

    private static final int duration = 10;

    RottenCarrotState(Carrot carrot) {
        super(duration, carrot);
        growingState.set(GrowingState.ROTTEN);
    }


    @Override
    public int grow() {
        int score = 0;

        ++age;
        if (age == duration) {
            score = reap();
            carrot.stateProperty().set(null);
        }
        return score;
    }

    @Override
    public int reap() {
        return - (maximum_score * age) / 10;
    }

    @Override
    public void fertilize() {

    }
}

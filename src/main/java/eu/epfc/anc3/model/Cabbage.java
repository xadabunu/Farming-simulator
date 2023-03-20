package eu.epfc.anc3.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

class Cabbage extends Growable {

    private final ObjectProperty<State> stateProperty = new SimpleObjectProperty<>();

    Cabbage(boolean onGrass) {
        super(onGrass);
        stateProperty.set(new CabbageState1(onGrass,this));
    }

    @Override
    void fertilize() {
    }

    void changeState() {
        stateProperty.set(stateProperty.get().grow());
        if (stateProperty().isNotNull().get()) {
            this.stateProperty().set(
                    switch (stateProperty().get()) {
                        case STATE_1 -> GrowingState.STATE_2;
                        case STATE_2 -> GrowingState.STATE_3;
                        case STATE_3 -> GrowingState.STATE_4;
                        case STATE_4 -> GrowingState.ROTTEN;
                        case ROTTEN -> null;
                    }
            );
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
}

abstract class CabbageStates implements State {

    final int maximum_score = 200;
    private final int duration;
    final BooleanProperty onGrass;
    int age = 0;
    final Cabbage cabbage;

    GrowingState growingState;

    CabbageStates(int duration, boolean onGrass, Cabbage cabbage) {
        this.duration = duration;
        this.onGrass = new SimpleBooleanProperty(onGrass);
        this.cabbage = cabbage;
    }

    public boolean canGrow() {
        ++age;
        if (age == duration) {
            cabbage.changeState();
            return growingState != null;
        }
        return true;
    }
}

class CabbageState1 extends CabbageStates {

    private static final int[] durations = {5, 4};

    CabbageState1(boolean onGrass, Cabbage cabbage) {
        super(durations[onGrass ? 1 : 0], onGrass, cabbage);
        growingState = GrowingState.STATE_1;
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public State grow() {
        return new CabbageState2(onGrass.get(), cabbage);
    }
}

class CabbageState2 extends CabbageStates {

    private static final int[] duration = {4, 3};

    CabbageState2(boolean onGrass, Cabbage cabbage) {
        super(duration[onGrass ? 1 : 0], onGrass, cabbage);
        growingState = GrowingState.STATE_2;
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public State grow() {
        return new CabbageState3(onGrass.get(), cabbage);
    }
}

class CabbageState3 extends CabbageStates {

    private static final int[] duration = {3, 2};

    CabbageState3(boolean onGrass, Cabbage cabbage) {
        super(duration[onGrass ? 1 : 0], onGrass, cabbage);
        growingState = GrowingState.STATE_3;
    }

    @Override
    public int getScore() {
        return maximum_score * 3/4;
    }

    @Override
    public State grow() {
        return new CabbageState4(onGrass.get(), cabbage);
    }
}

class CabbageState4 extends CabbageStates {

    private static final int[] duration = {2, 1};


    CabbageState4(boolean onGrass, Cabbage cabbage) {
        super(duration[onGrass ? 1 : 0], onGrass, cabbage);
        growingState = GrowingState.STATE_4;
    }

    @Override
    public int getScore() {
        return maximum_score;
    }

    @Override
    public State grow() {
        return new RottenCabbageState(onGrass.get(), cabbage);
    }
}

class RottenCabbageState extends CabbageStates {

    private static final int [] duration = {10, 5};
    RottenCabbageState(Boolean onGrass, Cabbage cabbage) {
        super(duration[onGrass ? 1 : 0], onGrass, cabbage);
        growingState = GrowingState.ROTTEN;
    }

    @Override
    public State grow() {
        cabbage.stateProperty().set(null);
        growingState = null;
        return this;
    }

    @Override
    public int getScore() {
        return - (maximum_score * age) / 10;
    }
}
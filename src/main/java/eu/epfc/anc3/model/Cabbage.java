package eu.epfc.anc3.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Cabbage extends Growable {

    private static final int MAXIMUM_SCORE = 200;
    private static final int[] STATE_DURATION = {5, 4, 3, 2, 10};
    private static final int[] GRASS_STATE_DURATION = {4, 3, 2, 1, 5};


    public Cabbage(boolean onGrass) {
        super(MAXIMUM_SCORE, onGrass);
        days_until_next_state = getStateDuration();
    }

    @Override
    protected int getStateDuration() {
        if (state == null) {
            return 0;
        }
        return onGrass ? GRASS_STATE_DURATION[state.ordinal()] : STATE_DURATION[state.ordinal()];
    }

    @Override
    int getScore() {
        return switch (state) {
            case STATE_1, STATE_2 -> 0;
            case STATE_3 -> MAXIMUM_SCORE * 3/4;
            case STATE_4 -> MAXIMUM_SCORE;
            case ROTTEN -> getRottenScore();
        };
    }

    @Override
    void fertilize() {

    }
}

abstract class CabbageStates implements State {

    final int maximum_score = 200;
    private final int duration;
    final BooleanProperty onGrass;
    int age = 0;

    CabbageStates(int duration, boolean onGrass) {
        this.duration = duration;
        this.onGrass = new SimpleBooleanProperty(onGrass);
    }

    boolean canGrow() {
        ++age;
        return age != duration;
    }
}

class CabbageState1 extends CabbageStates {

    private static final int[] durations = {5, 4};

    CabbageState1(boolean onGrass) {
        super(durations[onGrass ? 1 : 0], onGrass);
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public State grow() {
        return new CabbageState2(onGrass.get());
    }
}

class CabbageState2 extends CabbageStates {

    private static final int[] duration = {4, 3};

    CabbageState2(boolean onGrass) {
        super(duration[onGrass ? 1 : 0], onGrass);
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public State grow() {
        return new CabbageState3(onGrass.get());
    }
}

class CabbageState3 extends CabbageStates {

    private static final int[] duration = {4, 3};

    CabbageState3(boolean onGrass) {
        super(duration[onGrass ? 1 : 0], onGrass);
    }

    @Override
    public int getScore() {
        return maximum_score * 3/4;
    }

    @Override
    public State grow() {
        return new CabbageState4(onGrass.get());
    }
}

class CabbageState4 extends CabbageStates {

    private static final int[] duration = {3, 2};

    CabbageState4(boolean onGrass) {
        super(duration[onGrass ? 1 : 0], onGrass);
    }

    @Override
    public int getScore() {
        return maximum_score;
    }

    @Override
    public State grow() {
        return new RottenState();
    }
}
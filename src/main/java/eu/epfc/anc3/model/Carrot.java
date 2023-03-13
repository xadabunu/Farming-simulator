package eu.epfc.anc3.model;

public class Carrot extends Growable {

    private static final int MAXIMUM_SCORE = 100;
    private static final int[] STATES_DAYS = {3, 3, 3, 3, 10};

    public Carrot(boolean onGrass) {
        super(MAXIMUM_SCORE, onGrass);
        days_until_next_state = getStateDuration();
    }

    @Override
    protected int getStateDuration() {
        return state == null ? 0 : STATES_DAYS[state.ordinal()];
    }

    @Override
    int getScore() {
        return switch (state) {
            case STATE_1 -> MAXIMUM_SCORE / 10;
            case STATE_2 -> MAXIMUM_SCORE / 5;
            case STATE_3 -> MAXIMUM_SCORE / 2;
            case STATE_4 -> MAXIMUM_SCORE;
            case ROTTEN -> getRottenScore();
        };
    }

    @Override
    void fertilize() {
        if(state.ordinal() < 2) {
            state = GrowingState.STATE_3;
        }
    }
}

abstract class CarrotStates implements State {

    final int MAXIMUM_SCORE = 100;
    private final int duration;

    int age = 0;

    CarrotStates(int duration) {
        this.duration = duration;
    }

    boolean canGrow() {
        ++age;
        return age != duration;
    }
}

class CarrotState1 extends CarrotStates {

    private static final int duration = 3;

    CarrotState1() {
        super(duration);
    }

    @Override
    public int getScore() {
        return MAXIMUM_SCORE / 10;
    }

    @Override
    public State grow() {
        return new CarrotState2();
    }
}

class CarrotState2 extends CarrotStates {

    private static final int duration = 3;

    CarrotState2() {
        super(duration);
    }

    @Override
    public int getScore() {
        return MAXIMUM_SCORE / 5;
    }

    @Override
    public State grow() {
        return new CarrotState3();
    }
}

class CarrotState3 extends CarrotStates {

    private static final int duration = 3;

    CarrotState3() {
        super(duration);
    }

    @Override
    public int getScore() {
        return MAXIMUM_SCORE / 2;
    }

    @Override
    public State grow() {
        return new CarrotState4();
    }
}

class CarrotState4 extends CarrotStates {

    private static final int duration = 3;

    CarrotState4() {
        super(duration);
    }

    @Override
    public int getScore() {
        return MAXIMUM_SCORE;
    }

    @Override
    public State grow() {
        return new RottenState();
    }
}

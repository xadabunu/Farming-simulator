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
    protected int getScore() {
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

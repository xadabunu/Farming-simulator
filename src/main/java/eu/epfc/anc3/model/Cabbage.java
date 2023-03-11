package eu.epfc.anc3.model;

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
    protected int getScore() {
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

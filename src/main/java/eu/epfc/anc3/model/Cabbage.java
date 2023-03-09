package eu.epfc.anc3.model;

public class Cabbage extends Growable implements Plantable {

    private static final int MAXIMUM_SCORE = 200;
    private static final int ROTTEN_DAYS = 14;

    public Cabbage() {
        super(MAXIMUM_SCORE, ROTTEN_DAYS);
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
}

package eu.epfc.anc3.model;

public class Carrot extends Growable implements Plantable {

    private static final int MAXIMUM_SCORE = 100;
    private static final int ROTTING_AGE = 12;

    public Carrot() {
        super(MAXIMUM_SCORE, ROTTING_AGE);
    }

    @Override
    int getScore() {
        return switch (this.state) {
            case STATE_1 -> MAXIMUM_SCORE / 10;
            case STATE_3 -> MAXIMUM_SCORE / 5;
            case STATE_2 -> MAXIMUM_SCORE / 2;
            case STATE_4 -> MAXIMUM_SCORE;
            case ROTTEN -> getRottenScore();
        };
    }
}

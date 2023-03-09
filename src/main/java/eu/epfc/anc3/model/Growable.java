package eu.epfc.anc3.model;

public abstract class Growable {

    final int MAXIMUM_SCORE;
    int age = 0;
    final int rotten_age;

    GrowingState state = GrowingState.STATE_1;

    Growable(int maximumScore, int rotten_age) {
        MAXIMUM_SCORE = maximumScore;
        this.rotten_age = rotten_age;
    }

    public void setState(GrowingState state) {
        this.state = state;
    }

    private int grow() {
        switch (state) {
            case STATE_1 -> setState(GrowingState.STATE_2);
            case STATE_2 -> setState(GrowingState.STATE_3);
            case STATE_3 -> setState(GrowingState.STATE_4);
            case STATE_4 -> setState(GrowingState.ROTTEN);
            case ROTTEN -> {
                return reap();
            }
        }
        return 0;
    }

    private int reap() {
        int points = getScore();
        //doit retirer le Growable
        return points;
    }

    abstract int getScore();

    int getRottenScore() {
        return - MAXIMUM_SCORE * (age - rotten_age) / 10;
    }
}

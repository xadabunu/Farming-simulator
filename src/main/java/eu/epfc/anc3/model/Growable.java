package eu.epfc.anc3.model;

public abstract class Growable implements Plantable{

    final int MAXIMUM_SCORE;
    int age = 0;
    protected int days_until_next_state;
    int rotten_age;

    GrowingState state = GrowingState.STATE_1;

    Growable(int maximumScore, int rotten_age) {
        MAXIMUM_SCORE = maximumScore;
        this.rotten_age = rotten_age;
    }

    public void setState(GrowingState state) {
        this.state = state;
    }

    int grow() {
        return canGrow() ? 0 : getRottenScore();
    }

    private boolean canGrow() {
        ++age;
        if (age == days_until_next_state) {
            state = state.grow();
            age = 0;
            days_until_next_state = getStateDuration();
        }
        return state != null;
    }

    protected abstract int getStateDuration();

    private int reap() {
        int points = getScore();
        state = null;
        return points;
    }

    protected abstract int getScore();

    int getRottenScore() {
        return - MAXIMUM_SCORE * (age - rotten_age) / 10;
    }
}

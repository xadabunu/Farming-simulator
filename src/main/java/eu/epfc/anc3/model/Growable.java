package eu.epfc.anc3.model;

import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public abstract class Growable extends Plantable{

    final int MAXIMUM_SCORE;
    protected int days_until_next_state;

    boolean onGrass;

    GrowingState state = GrowingState.STATE_1;
    SimpleObjectProperty<GrowingState> stateProperty = new SimpleObjectProperty<>(GrowingState.STATE_1);

    Growable(int maximumScore, boolean onGrass) {
        MAXIMUM_SCORE = maximumScore;
        this.onGrass = onGrass;
    }

    public void setState(GrowingState state) {
        this.state = state;
        stateProperty.set(state);
    }

    int grow() {
        return canGrow() ? 0 : getRottenScore();
    }

    private boolean canGrow() {
        if (state != null) {
            ++age;
        }
        if (age == days_until_next_state) {
            state = state.grow();
            stateProperty.set(stateProperty.getValue().grow());
            if (state != null) {
                age = 0;
            }
            days_until_next_state = getStateDuration();
            if(state != null)
                System.out.println(state.name());
        }
        return state != null;
    }

    protected abstract int getStateDuration();

    int reap() {
        int points = getScore();
        state = null;
        stateProperty.set(null);
        return points;
    }

    protected abstract int getScore();
    abstract  void fertilize();


    int getRottenScore() {
        System.out.println(MAXIMUM_SCORE +  " - " + age + " " + (- (MAXIMUM_SCORE * age) / 10));
        return - (MAXIMUM_SCORE * age) / 10;
    }

    ReadOnlyObjectProperty<GrowingState> stateProperty() {
        return stateProperty;
    }
}

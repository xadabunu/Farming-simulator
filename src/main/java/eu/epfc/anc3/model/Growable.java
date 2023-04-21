package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

abstract class Growable {

    final ObjectProperty<State> stateProp = new SimpleObjectProperty<>();
    boolean onGrass;

    final SimpleObjectProperty<GrowingState> growingStateProperty = new SimpleObjectProperty<>(GrowingState.STATE_1);

    Growable(boolean onGrass) {
        this.onGrass = onGrass;
    }

    int grow() {
        return this.stateProp.get().grow();
    }
    int reap() {
        int score = stateProp.get().reap();
        stateProp.set(null);
        return score;
    }
    void fertilize() {
        if (stateProp.get() != null) {
            stateProp.get().fertilize();
        }
    }

    abstract void setStateProp(State state);

    SimpleObjectProperty<GrowingState> stateProperty() {
        return growingStateProperty;
    }

    Growable(Growable growable) {
        this.onGrass = growable.onGrass;
    }


    public int getAge() {
        return this.stateProp.get().getAge();
    }

    public void setAge(int age) {
        this.stateProp.get().setAge(age);
    }

}

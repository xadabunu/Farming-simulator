package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

abstract class Growable extends Plantable {

    final ObjectProperty<State> stateProp = new SimpleObjectProperty<>();
    boolean onGrass;

    GrowingState state = GrowingState.STATE_1;
    final SimpleObjectProperty<GrowingState> growingStateProperty = new SimpleObjectProperty<>();

    Growable(boolean onGrass) {
        this.onGrass = onGrass;
    }

    abstract int grow();
    abstract int reap();
    abstract  void fertilize();
    abstract void setStateProp(State state);

    SimpleObjectProperty<GrowingState> stateProperty() {
        return growingStateProperty;
    }


}

package eu.epfc.anc3.model;

import javafx.beans.property.SimpleObjectProperty;

abstract class Growable extends Plantable {

    boolean onGrass;

    GrowingState state = GrowingState.STATE_1;
    private final SimpleObjectProperty<GrowingState> stateProperty = new SimpleObjectProperty<>(GrowingState.STATE_1);

    Growable(boolean onGrass) {
        this.onGrass = onGrass;
    }

    abstract int grow();
    abstract int reap();
    abstract  void fertilize();

    SimpleObjectProperty<GrowingState> stateProperty() {
        return stateProperty;
    }


}

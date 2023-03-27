package eu.epfc.anc3.model;

import javafx.beans.property.SimpleObjectProperty;

interface State {

    int reap();
    //boolean canGrow();
    int grow();
    SimpleObjectProperty<GrowingState> getGrowingState();
}

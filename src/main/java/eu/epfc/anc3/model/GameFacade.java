package eu.epfc.anc3.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class GameFacade {

    private final BooleanProperty isOn = new SimpleBooleanProperty(false);

    public ReadOnlyBooleanProperty isOnProperty() {return isOn;}

}

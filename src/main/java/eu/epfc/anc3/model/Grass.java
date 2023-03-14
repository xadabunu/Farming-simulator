package eu.epfc.anc3.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Grass extends Plantable {

    private static final int DURATION = 12;
    private final BooleanProperty isDead = new SimpleBooleanProperty(false);

    void grow() {
        if (++age == DURATION) {
            isDead.set(true);
        }
    }
}

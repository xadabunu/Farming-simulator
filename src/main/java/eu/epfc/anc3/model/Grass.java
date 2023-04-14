package eu.epfc.anc3.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

class Grass{

    Grass(int age) {
        this.age = age;
    }
    Grass() {
        this(0);
    }

    private int age;
    private static final int DURATION = 12;
    private final BooleanProperty isDead = new SimpleBooleanProperty(false);
    void grow() {
        if (++age == DURATION)
            isDead.set(true);
    }

    void resetGrass() {
        isDead.set(false);
        age = 0;
    }

    ReadOnlyBooleanProperty grassProperty() {
        return isDead;
    }

    public int getAge() {
        return age;
    }
}

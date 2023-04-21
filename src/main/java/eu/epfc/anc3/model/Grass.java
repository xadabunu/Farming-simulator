package eu.epfc.anc3.model;

import javafx.beans.property.*;

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
    void restore(Grass landGrass) {
        this.setAge(landGrass.getAge());
        this.setIsDead(landGrass.grassProperty().get());
    }

    ReadOnlyBooleanProperty grassProperty() {
        return isDead;
    }

    public int getAge() {
        return age;
    }
    protected void setAge(int age) {this.age = age;}
    protected void setIsDead(boolean isdead) {this.isDead.set(isdead);}
}

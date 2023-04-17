package eu.epfc.anc3.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;

class Farmer extends Character {

    private final IntegerProperty score = new SimpleIntegerProperty(0);

    Farmer(Field field) {
        super(field);
    }

    boolean plantGrass() {
        if (!position.get().hasGrass()) {
            position.get().setContent(LandContent.GRASS);
            return true;
        }
        return false;
    }

    boolean plantCarrot() {
        if (!position.get().hasGrowable()) {
            Growable g = new Carrot(position.get().hasGrass());
            position.get().plant(g);
            position.get().setGrowable(LandGrowable.CARROT);
            return true;
        }
        return false;
    }

    boolean plantCabbage() {
        if (!position.get().hasGrowable()) {
            Growable g = new Cabbage(position.get().hasGrass());
            position.get().plant(g);
            position.get().setGrowable(LandGrowable.CABBAGE);
            return true;
        }
        return false;
    }

    boolean unplant() {
        if (position.get().hasGrowable()) {
            reap();
            position.get().removeGrowable();
        }
        else if (position.get().hasGrass()) {
            position.get().setContent(LandContent.DIRT);
            return true;
        }
        return false;
    }

     void manageField() {
        for (int i = 0 ; i < Field.LINES; ++i) {
            for (int j = 0 ; j <  Field.COLUMNS; ++j) {
                score.set(score.get() + field.getLand(i, j).grow());
            }
        }
    }

    void fertilize() {
        position.get().fertilize();
    }

    void reap() {
        score.set(score.get() + position.get().reap());
    }

    IntegerProperty scoreProperty() {
        return score;
    }

    @Override
    void resetPosition() {
        super.resetPosition();
        score.set(0);
    }

    void restorePosition(ObjectProperty<Land> position) {
        this.position.set(position.get());
    }

    Farmer(Farmer farmer, Field field) {
        super(field);
        this.score.set(farmer.score.get());
        this.position.set(farmer.position.get());
    }
}

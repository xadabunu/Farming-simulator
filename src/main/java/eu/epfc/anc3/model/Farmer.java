package eu.epfc.anc3.model;

import javafx.beans.property.ReadOnlyIntegerProperty;

class Farmer extends Character {

    private int score = 0;

    Farmer(Field field) {
        super(field);
    }

    boolean plantGrass() {
        if (position.get().contentProperty().isEqualTo(LandContent.DIRT).get()) {
            position.get().setContent(LandContent.GRASS);
            return true;
        }
        return false;
    }

    boolean plantCarrot() {
        if (position.get().growableProperty().get() == (null)) {
            position.get().setGrowable(LandGrowable.CARROT);
            return true;
        }
        return false;
    }

    boolean plantCabbage() {
        if (position.get().growableProperty().get() == (null)) {
            position.get().setGrowable(LandGrowable.CABBAGE);
            return true;
        }
        return false;
    }

    boolean unplant() {
        if (!position.get().contentProperty().isEqualTo(LandContent.DIRT).get()) {
            position.get().setContent(LandContent.DIRT);
            return true;
        }
        return false;
    }

     void manageField() {
        for (int i = 0 ; i < Field.LINES; ++i) {
            for (int j = 0 ; j <  Field.COLUMNS; ++j)
                score += field.getLand(i, j).grow();
        }
    }
}

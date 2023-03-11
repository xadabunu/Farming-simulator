package eu.epfc.anc3.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

class Farmer extends Character {

    private final IntegerProperty score = new SimpleIntegerProperty(0);

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
            Growable g = new Carrot(position.get().contentProperty().isEqualTo(LandContent.GRASS).get());
            position.get().setGrowable(LandGrowable.CARROT);
            position.get().plant(g);
            return true;
        }
        return false;
    }

    boolean plantCabbage() {
        if (position.get().growableProperty().get() == (null)) {
            Growable g = new Cabbage(position.get().contentProperty().isEqualTo(LandContent.GRASS).get());
            position.get().setGrowable(LandGrowable.CABBAGE);
            position.get().plant(g);
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
                score.set(score.get() + field.getLand(i, j).grow());
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
}

package eu.epfc.anc3.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;

class Field {
    static final int LINES = 15, COLUMNS = 25;
    private final Land[][] lands;

    private final IntegerProperty grassCtr = new SimpleIntegerProperty();

    public Field() {
        lands = new Land[LINES][COLUMNS];
        for (int i = 0 ; i < LINES ; ++i) {
            for (int j = 0 ; j < COLUMNS ; ++j)
                lands[i][j] = new Land(new Position(i,j));
        }
    }

    LandContent getValue(int line, int col) {
        return lands[line][col].getValue();
    }

    public Land getLand(int line, int col) {return lands[line][col];}

    public ReadOnlyObjectProperty<LandContent> contentProperty(int line, int col) {
        return lands[line][col].valueProperty();
    }

    public ReadOnlyIntegerProperty grassCtrProperty() {
        return grassCtr;
    }


}

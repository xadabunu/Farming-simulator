package eu.epfc.anc3.model;

import javafx.beans.property.ReadOnlyObjectProperty;

class Field {
    static final int LINES = 15, COLUMNS = 25;
    private final Land[][] lands;

    public Field() {
        lands = new Land[LINES][COLUMNS];
        for (int i = 0 ; i < LINES ; ++i)
            for (int j = 0 ; j < COLUMNS ; ++j)
                lands[i][j] = new Land();
    }

    LandContent getValue(int line, int col) {
        return lands[line][col].getValue();
    }

    public Land getLand(int line, int col) {return lands[line][col];}

    public ReadOnlyObjectProperty<LandContent> contentProperty(int line, int col) {
        return lands[line][col].valueProperty();
    }
}

package eu.epfc.anc3.model;

import javafx.beans.property.*;

class Field {

    static final int LINES = 15, COLUMNS = 25;
    private final Land[][] lands;

    Field() {
        lands = new Land[LINES][COLUMNS];
        for (int i = 0 ; i < LINES ; ++i) {
            for (int j = 0 ; j < COLUMNS ; ++j)
                lands[i][j] = new Land(i, j);
        }
    }

    Land getLand(int line, int col) {
        return lands[line][col];
    }

    void reset() {
        for (int i = 0; i < LINES; ++i) {
            for (int j = 0; j < COLUMNS; ++j) {
                lands[i][j].setContent(LandContent.DIRT);
                lands[i][j].removeGrowable();
            }
        }
    }

    public ReadOnlyObjectProperty<LandContent> contentProperty(int line, int col) {
        return lands[line][col].contentProperty();
    }

    public ReadOnlyObjectProperty<LandGrowable> growableProperty(int line, int col) {
        return lands[line][col].growableProperty() ;
    }
    ReadOnlyObjectProperty<GrowingState> growableState(int line, int col) {
        return lands[line][col].growableState();
    }

    Field(Field field) {
        this.lands = new Land[LINES][COLUMNS];

        for (int i = 0 ; i < LINES ; ++i)
            for (int j = 0 ; j < COLUMNS ; ++j)
                lands[i][j] = new Land(field.getLand(i, j));
    }

    void restore(Field fieldCopy) {
        for (int i = 0 ; i < LINES ; ++i)
            for (int j = 0 ; j < COLUMNS ; ++j)
                lands[i][j].restore(fieldCopy.getLand(i, j));
    }
}

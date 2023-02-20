package eu.epfc.anc3.model;

import javafx.beans.property.*;

class Field {
    static final int LINES = 15, COLUMNS = 25;
    private final Land[][] lands;

    private final IntegerProperty grassCtr = new SimpleIntegerProperty();
    private final ObjectProperty<Land> characterPosition;

    public Field() {
        lands = new Land[LINES][COLUMNS];
        for (int i = 0 ; i < LINES ; ++i) {
            for (int j = 0 ; j < COLUMNS ; ++j)
                lands[i][j] = new Land(new Position(i,j));
        }
        characterPosition = new SimpleObjectProperty<>(lands[0][0]);
    }

    LandContent getValue(int line, int col) {
        return lands[line][col].getContent();
    }

    public Land getLand(int line, int col) {return lands[line][col];}

    public ReadOnlyObjectProperty<LandContent> contentProperty(int line, int col) {
        return lands[line][col].contentProperty();
    }

    public ObjectProperty<Position> characterPositionProperty() {
        return characterPosition.get().getPosition();
    }
}

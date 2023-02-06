package eu.epfc.anc3.model;

public class Field {
    static final int LINES = 15, COLUMNS =15;

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
}

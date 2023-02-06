package eu.epfc.anc3.model;

public class Field {
    static final int ROWS=3, COLUMNS = 10;
    public final Land[][] matrix;
    Field() {
        matrix = new Land[ROWS][];
        for (int i = 0; i < ROWS; ++i) {
            matrix[i] = new Land[COLUMNS];
            for (int j = 0; j < COLUMNS; ++j) {
                matrix[i][j] = new Land();
            }
        }
    }
    LandContent getValue(int line, int col) {
        return matrix[line][col].getValue();
    }

}

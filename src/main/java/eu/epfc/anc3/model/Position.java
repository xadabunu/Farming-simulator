package eu.epfc.anc3.model;

public class Position {

    private final int line, col;

    public Position(int line, int col) {
        this.line = line;
        this.col = col;
    }

    public int getCol() {
        return col;
    }

    public int getLine() {
        return line;
    }

}

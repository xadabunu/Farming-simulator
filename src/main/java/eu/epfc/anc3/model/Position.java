package eu.epfc.anc3.model;

public class Position {

    private int line, col;

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

    public void setLine(int line) {
        this.line = line;
    }

    public void setCol(int col) {
        this.col = col;
    }

    @Override
    public String toString() {
        return "Position{" +
                "line=" + line +
                ", col=" + col +
                '}';
    }
}

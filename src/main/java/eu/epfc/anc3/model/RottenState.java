package eu.epfc.anc3.model;

public class RottenState implements State {

    private static final int duration = 10;

    RottenState() {

    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public State grow() {
        return null;
    }
}

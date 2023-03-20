package eu.epfc.anc3.model;

interface State {

    int getScore();
    boolean canGrow();
    State grow();
    default int reap() {
        return getScore();
    }
}

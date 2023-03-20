package eu.epfc.anc3.model;

interface State {

    int reap();
    boolean canGrow();
    State grow();
}

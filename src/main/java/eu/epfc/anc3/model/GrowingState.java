package eu.epfc.anc3.model;

public enum GrowingState {
    STATE_1, STATE_2, STATE_3, STATE_4, ROTTEN;

    GrowingState grow() {
        return switch(this) {

            case STATE_1 -> STATE_2;
            case STATE_2 -> STATE_3;
            case STATE_3 -> STATE_4;
            case STATE_4 -> ROTTEN;
            case ROTTEN -> null;
        };
    }
}

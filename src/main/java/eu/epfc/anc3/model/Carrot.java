package eu.epfc.anc3.model;

class Carrot extends Growable {

    Carrot(boolean onGrass) {
        super(onGrass);
        stateProp.set(new CarrotState1(this));
    }

    @Override
    void fertilize() {
        if (state == GrowingState.STATE_1 || state == GrowingState.STATE_2) {
            state = GrowingState.STATE_3;
            stateProperty().set(GrowingState.STATE_3);
            stateProp.set(new CarrotState3(this));
        }
    }

    int grow() {
        return stateProp.get().canGrow() ? 0 : stateProp.get().reap();
    }

    int reap() {
        int score = stateProp.get().reap();
        stateProp.set(null);
        return score;
    }

    void changeState() {
        stateProp.set(stateProp.get().grow());
        if (stateProperty().isNotNull().get()) {
            this.stateProperty().set(
                    switch (stateProperty().get()) {
                        case STATE_1 -> GrowingState.STATE_2;
                        case STATE_2 -> GrowingState.STATE_3;
                        case STATE_3 -> GrowingState.STATE_4;
                        case STATE_4 -> GrowingState.ROTTEN;
                        case ROTTEN -> null;
                    }
            );
        }
    }
}

abstract class CarrotStates implements State {

    final int maximum_score = 100;
    private final int duration;
    final Carrot carrot;
    GrowingState growingState;

    int age = 0;

    CarrotStates(int duration, Carrot carrot) {
        this.duration = duration;
        this.carrot = carrot;
    }

    public boolean canGrow() {
        ++age;
        if (age == duration) {
            carrot.changeState();
            return growingState != null;
        }
        return true;
    }
}

class CarrotState1 extends CarrotStates {

    private static final int duration = 3;

    CarrotState1(Carrot carrot) {
        super(duration, carrot);
        growingState = GrowingState.STATE_1;
    }

    @Override
    public int reap() {
        return maximum_score / 10;
    }

    @Override
    public State grow() {
        return new CarrotState2(carrot);
    }
}

class CarrotState2 extends CarrotStates {

    private static final int duration = 3;

    CarrotState2(Carrot carrot) {
        super(duration, carrot);
        growingState = GrowingState.STATE_2;
    }

    @Override
    public int reap() {
        return maximum_score / 5;
    }

    @Override
    public State grow() {
        return new CarrotState3(carrot);
    }
}

class CarrotState3 extends CarrotStates {

    private static final int duration = 3;

    CarrotState3(Carrot carrot) {
        super(duration, carrot);
        growingState = GrowingState.STATE_3;
    }

    @Override
    public int reap() {
        return maximum_score / 2;
    }

    @Override
    public State grow() {
        return new CarrotState4(carrot);
    }
}

class CarrotState4 extends CarrotStates {

    private static final int duration = 3;

    CarrotState4(Carrot carrot) {
        super(duration, carrot);
        growingState = GrowingState.STATE_4;
    }

    @Override
    public int reap() {
        return maximum_score;
    }

    @Override
    public State grow() {
        return new RottenCarrotState(carrot);
    }
}

class RottenCarrotState extends CarrotStates {

    private static final int duration = 10;

    RottenCarrotState(Carrot carrot) {
        super(duration, carrot);
        growingState = GrowingState.ROTTEN;
    }

    @Override
    public State grow() {
        carrot.stateProperty().set(null);
        growingState = null;
        return this;
    }

    @Override
    public int reap() {
        return - (maximum_score * age) / 10;
    }
}

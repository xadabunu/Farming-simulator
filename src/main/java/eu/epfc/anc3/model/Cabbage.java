package eu.epfc.anc3.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

class Cabbage extends Growable {

    Cabbage(boolean onGrass) {
        super(onGrass);
        stateProp.set(new CabbageState1(onGrass,this));
        stateProp.addListener((obs, oldV, newV) -> {
            if (newV != null)
                growingStateProperty.set(newV.getGrowingState().get());
        });
    }

    Cabbage(Growable growable) {
        super(growable);
        stateProp.addListener((obs, oldV, newV) -> {
            if (newV != null)
                growingStateProperty.set(newV.getGrowingState().get());
        });
        switch (growable.growingStateProperty.get()) {
            case STATE_1 -> this.stateProp.set(new CabbageState1(growable.onGrass, this));
            case STATE_2 -> this.stateProp.set(new CabbageState2(growable.onGrass, this));
            case STATE_3 -> this.stateProp.set(new CabbageState3(growable.onGrass, this));
            case STATE_4 -> this.stateProp.set(new CabbageState4(growable.onGrass, this));
            case ROTTEN -> this.stateProp.set(new RottenCabbageState(growable.onGrass, this));
        }
        int agge = growable.getAge();
        this.stateProp.get().setAge(agge);

        this.growingStateProperty.set(growable.growingStateProperty.get());
    }

    @Override
    void setStateProp(State state) {
        stateProp.set(state);
    }
}

abstract class CabbageStates implements State {

    final int maximum_score = 200;
    final int duration;
    final BooleanProperty onGrass;
    int age = 0;
    final Cabbage cabbage;

    final SimpleObjectProperty<GrowingState> growingState = new SimpleObjectProperty<>();

    CabbageStates(int duration, boolean onGrass, Cabbage cabbage) {
        this.duration = duration;
        this.onGrass = new SimpleBooleanProperty(onGrass);
        this.cabbage = cabbage;
    }

    @Override
    public SimpleObjectProperty<GrowingState> getGrowingState() {
        return growingState;
    }
}

class CabbageState1 extends CabbageStates {

    private static final int[] durations = {5, 4};

    CabbageState1(boolean onGrass, Cabbage cabbage) {
        super(durations[onGrass ? 1 : 0], onGrass, cabbage);
        growingState.set(GrowingState.STATE_1);
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int reap() {
        return 0;
    }

    @Override
    public void fertilize() {

    }

    @Override
    public int grow() {
        ++age;
        if (age == super.duration)
            cabbage.setStateProp(new CabbageState2(onGrass.get(), cabbage));
        return 0;
    }
}

class CabbageState2 extends CabbageStates {

    private static final int[] duration = {4, 3};

    CabbageState2(boolean onGrass, Cabbage cabbage) {
        super(duration[onGrass ? 1 : 0], onGrass, cabbage);
        growingState.set(GrowingState.STATE_2);
    }
    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int reap() {
        return 0;
    }

    @Override
    public void fertilize() {

    }

    @Override
    public int grow() {
        ++age;
        if (age == super.duration)
            cabbage.setStateProp(new CabbageState3(onGrass.get(), cabbage));
        return 0;
    }
}

class CabbageState3 extends CabbageStates {

    private static final int[] duration = {3, 2};

    CabbageState3(boolean onGrass, Cabbage cabbage) {
        super(duration[onGrass ? 1 : 0], onGrass, cabbage);
        growingState.set(GrowingState.STATE_3);
    }

    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int reap() {
        return maximum_score * 3/4;
    }

    @Override
    public void fertilize() {

    }

    @Override
    public int grow() {
        ++age;
        if (age == super.duration)
            cabbage.setStateProp(new CabbageState4(onGrass.get(), cabbage));
        return 0;
    }
}

class CabbageState4 extends CabbageStates {

    private static final int[] duration = {2, 1};


    CabbageState4(boolean onGrass, Cabbage cabbage) {
        super(duration[onGrass ? 1 : 0], onGrass, cabbage);
        growingState.set(GrowingState.STATE_4);
    }
    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int reap() {
        return maximum_score;
    }

    @Override
    public void fertilize() {

    }

    @Override
    public int grow() {
        ++age;
        if (age == super.duration)
            cabbage.setStateProp(new RottenCabbageState(onGrass.get(), cabbage));
        return 0;
    }
}

class RottenCabbageState extends CabbageStates {

    private static final int [] duration = {10, 5};
    RottenCabbageState(Boolean onGrass, Cabbage cabbage) {
        super(duration[onGrass ? 1 : 0], onGrass, cabbage);
        growingState.set(GrowingState.ROTTEN);
    }
    @Override
    public int getAge() {
        return age;
    }

    @Override
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int grow() {
        int score = 0;

        ++age;
        if (age == super.duration) {
            score = reap();
            cabbage.stateProperty().set(null);
        }
        return score;
    }

    @Override
    public int reap() {
        return - (maximum_score * age) / 10;
    }

    @Override
    public void fertilize() {

    }
}
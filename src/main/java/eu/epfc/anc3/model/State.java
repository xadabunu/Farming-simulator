package eu.epfc.anc3.model;

import javafx.beans.property.SimpleObjectProperty;

interface State {

    int reap();
    void fertilize();
    int grow();
    int getAge();
    void setAge(int age);
    SimpleObjectProperty<GrowingState> getGrowingState();
}

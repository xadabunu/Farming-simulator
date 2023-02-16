package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Land {


    private final ObjectProperty<LandContent> value = new SimpleObjectProperty<>(LandContent.DIRT);
    private final Position position;

    public Land(Position position) {
        this.position = position;
    }

    LandContent getValue() {
        return value.getValue();
    }

    void setValue(LandContent value) {
        this.value.setValue(value);
    }

    ReadOnlyObjectProperty<LandContent> valueProperty() {
        return value;

    }
}

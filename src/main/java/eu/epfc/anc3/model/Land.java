package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Land {


    private final ObjectProperty<LandContent> value = new SimpleObjectProperty<>(LandContent.DIRT);

    public Land() {
    }

    LandContent getValue() {
        return value.getValue();
    }

    boolean setValue(LandContent value) {
        if (this.value.get() != LandContent.DIRT) return false;
        this.value.setValue(value);
        return true;
    }

    ReadOnlyObjectProperty<LandContent> valueProperty() {
        return value;

    }

    @Override
    public String toString() {
        return "x";
    }
}

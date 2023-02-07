package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Land {


    private final ObjectProperty<LandContend> value = new SimpleObjectProperty<>(LandContend.DIRT);

    public Land() {
    }

    LandContend getValue() {
        return value.getValue();
    }

    boolean setValue(LandContend value) {
        if (this.value.get() != LandContend.DIRT) return false;
        this.value.setValue(value);
        return true;
    }

    ReadOnlyObjectProperty<LandContend> valueProperty() {
        return value;

    }

    @Override
    public String toString() {
        return "x";
    }
}

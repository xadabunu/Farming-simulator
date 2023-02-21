package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

class Land {

    private final ObjectProperty<LandContent> content = new SimpleObjectProperty<>(LandContent.DIRT);
    private final Position position;

    Land(Position position) {
        this.position = position;
    }


    void setContent(LandContent content) {
        this.content.setValue(content);
    }

    ReadOnlyObjectProperty<LandContent> contentProperty() {
        return content;
    }

}

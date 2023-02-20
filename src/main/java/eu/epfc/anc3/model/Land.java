package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Land {

    private final ObjectProperty<LandContent> content = new SimpleObjectProperty<>(LandContent.DIRT);
    private final Position position;

    public Land(Position position) {
        this.position = position;
    }

    LandContent getContent() {
        return content.getValue();
    }

    void setContent(LandContent content) {
        this.content.setValue(content);
    }

    ReadOnlyObjectProperty<LandContent> contentProperty() {
        return content;
    }

    @Override
    public String toString() {
        return position + " content : " + content;
    }

    public ObjectProperty<Position> getPosition() {
        return new SimpleObjectProperty<>(position);
    }
}

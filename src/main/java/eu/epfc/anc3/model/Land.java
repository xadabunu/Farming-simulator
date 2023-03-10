package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Land {

    private final int line, col;

    private final ObjectProperty<LandContent> content = new SimpleObjectProperty<>(LandContent.DIRT);
    private Growable growable;

    Land(int i, int j) {
        line = i;
        col = j;
    }

    public int getLine() {
        return line;
    }

    public int getCol() {
        return col;
    }

    void setContent(LandContent content) {
        this.content.setValue(content);
    }

    void grow() {
        if (growable != null) {
            growable.grow();
            System.out.println(growable.state);
        }
    }

    ReadOnlyObjectProperty<LandContent> contentProperty() {
        return content;
    }
}

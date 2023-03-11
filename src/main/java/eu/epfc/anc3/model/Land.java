package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Land {

    private final int line, col;

    private final ObjectProperty<LandContent> content = new SimpleObjectProperty<>(LandContent.DIRT);

    final ObjectProperty<LandGrowable> growableProp = new SimpleObjectProperty<>();
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


    void setGrowable(LandGrowable growable) {
        this.growableProp.setValue(growable);
    }

    public void removeGrowable() {
        this.growableProp.set(null);
    }


    int grow() {
        return growable == null ? 0 : growable.grow();

    }

    ReadOnlyObjectProperty<LandContent> contentProperty() {
        return content;
    }

    ReadOnlyObjectProperty<LandGrowable> growableProperty() {
        return growableProp;
    }
}

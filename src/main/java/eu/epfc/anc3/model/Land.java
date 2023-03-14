package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Land {

    private final int line, col;

    private final ObjectProperty<LandContent> content = new SimpleObjectProperty<>(LandContent.DIRT);

    final ObjectProperty<LandGrowable> growableProp = new SimpleObjectProperty<>();
    private Growable growable;

    private Grass grass;

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
        growableProp.setValue(growable);
    }

    public void removeGrowable() {
        growableProp.set(null);
    }

    public void removeGrass() {
        content.set(LandContent.DIRT);
    }


    int grow() {
        if(content.isEqualTo(LandContent.GRASS).get()) {
            grass.grow();
        }
        return growable == null ? 0 : growable.grow();

    }

    void plant(Growable g) {
        growable = g;
        g.stateProperty().addListener((obs, old, newVal) -> {
            if (newVal == null) {
                growable = null;
            }
        });
    }

    ReadOnlyObjectProperty<LandContent> contentProperty() {
        return content;
    }

    ReadOnlyObjectProperty<LandGrowable> growableProperty() {
        return growableProp;
    }

    void fertilize() {
        if (growable != null)
            growable.fertilize();
    }

    int reap() {
        return growable == null ? 0 : growable.reap();
    }
}

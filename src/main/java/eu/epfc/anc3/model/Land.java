package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;

public class Land {

    private final int line, col;

    private final ObjectProperty<LandContent> content = new SimpleObjectProperty<>(LandContent.DIRT);
    final ObjectProperty<LandGrowable> growableProp = new SimpleObjectProperty<>();

    private Growable growable;
    private final Grass grass = new Grass();

    Land(int i, int j) {
        line = i;
        col = j;

        isDead().addListener((obs, old, newVal) -> {
            if (isDead().get()) {
                content.set(LandContent.DIRT);
                grass.resetGrass();
            }
        });
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
                removeGrowable();
            }
        });
    }

    ReadOnlyObjectProperty<LandContent> contentProperty() {
        return content;
    }

    ReadOnlyObjectProperty<LandGrowable> growableProperty() {
        return growableProp;
    }

    public ReadOnlyObjectProperty<GrowingState> growableState() {
        return growable.stateProperty();
    }

    private ReadOnlyBooleanProperty isDead() {
        return grass.grassProperty();
    }
    void fertilize() {
        if (growable != null)
            growable.fertilize();
    }
    boolean hasGrowable() {
        return growableProperty().isEqualTo(LandGrowable.CABBAGE).get()
                || growableProperty().isEqualTo(LandGrowable.CARROT).get();
    }
    int reap() {
        int score = 0;
        if (growable != null) {
            score = growable.reap();
            growable = null;
        }
        return score;
    }
}

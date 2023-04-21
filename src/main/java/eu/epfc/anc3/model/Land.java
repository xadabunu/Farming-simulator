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
    private Grass grass = new Grass();

    Land(int i, int j) {
        line = i;
        col = j;

        isDead().addListener((obs, old, newVal) -> {
            if (isDead().get()) {
                this.content.set(LandContent.DIRT);
                this.grass.resetGrass();
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

    void removeGrowable() {
        this.growableProp.set(null);
    }

    int grow() {
        if (this.content.isEqualTo(LandContent.GRASS).get()) {
            grass.grow();
        }
        return hasGrowable() ? this.growable.grow() : 0;
    }

    void plant(Growable g) {
        this.growable = g;
        g.stateProperty().addListener((obs, old, newVal) -> {
            if (newVal == null) {
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
        return growable != null ? growable.stateProperty() : null;
    }


    private ReadOnlyBooleanProperty isDead() {
        return grass.grassProperty();
    }

    void fertilize() {
        if (growable != null)
            this.growable.fertilize();
    }

    boolean hasGrowable() {
        return growableProperty().isEqualTo(LandGrowable.CABBAGE).get()
                || growableProperty().isEqualTo(LandGrowable.CARROT).get();
    }

    boolean hasGrass() {
        return contentProperty().isEqualTo(LandContent.GRASS).get();
    }

    int reap() {
        int score = 0;
        if (growable != null) {
            score = this.growable.reap();
            removeGrowable();
        }
        return score;
    }

    Land(Land land) {
        line = land.line;
        col = land.col;
        content.set(land.content.get());
        growableProp.set(land.growableProp.get());
        grass = new Grass(land.grass.getAge());
        if (land.growable != null && land.growableProp.get() != null) {
            growable = switch (land.growableProp.get()) {
                case CARROT -> new Carrot(land.growable);
                case CABBAGE -> new Cabbage(land.growable);
                default -> null;
            };
        }
    }

    void restore(Land land) {
        this.removeGrowable();
        switch (land.content.get()) {
            case GRASS -> this.setContent(LandContent.GRASS);
            case DIRT -> this.setContent(LandContent.DIRT);
        }
        if (land.growable != null) {
            switch (land.growableProp.get()) {
                case CARROT -> this.growable = new Carrot(land.growable);
                case CABBAGE -> this.growable = new Cabbage(land.growable);
            }
            switch (land.growableProp.get()) {
                case CARROT -> this.setGrowable(LandGrowable.CARROT);
                case CABBAGE -> this.setGrowable(LandGrowable.CABBAGE);
            }
        }
        grass.restore(land.grass);
    }
}

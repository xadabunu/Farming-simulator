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
            this.grass.grow();
        }

        if (hasGrowable()) {
            System.out.println(this.growable.onGrass + "   " + this.growable.stateProp + "   " + this.growable.growingStateProperty);
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
        return growable.stateProperty();
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

    int reap() {
        int score = 0;
        if (growable != null) {
            score = this.growable.reap();
            removeGrowable();
        }
        return score;
    }

    Land(Land land) {
        this.line = land.line;
        this.col = land.col;
        this.content.set(land.content.get());
        this.growableProp.set(land.growableProp.get());
        this.grass = new Grass(land.grass.getAge());
        if (land.growable != null && land.growableProp.get() != null) {
            this.growable = switch (land.growableProp.get()) {
                case CARROT -> new Carrot(land.growable);
                case CABBAGE -> new Cabbage(land.growable);
                default -> null;
            };
        }
    }

    void restore(Land land) {
        if (land.growable != null) {
            this.growable.setStateProp(land.growable.stateProp.get());
            this.growable.onGrass = land.growable.onGrass;
            this.growable.growingStateProperty.set(land.growable.growingStateProperty.get());
        }
        else { this.growable = null; }
        this.grass.setAge(land.grass.getAge());
        this.grass.setIsDead(land.grass.grassProperty().get());
        this.setContent(land.content.get());
        this.setGrowable(land.growableProp.get());
    }

}

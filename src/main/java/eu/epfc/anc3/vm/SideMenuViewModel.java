package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.GameFacade;
import javafx.beans.property.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SideMenuViewModel {


    private final GameFacade game;
    SideMenuViewModel(GameFacade game) {
        this.game = game;
    }

    public void fertilize() {
        game.fertilize();
    }

    public void setStatusPlantGrass() {
        game.setStatusPlantGrass();
    }
    public void setStatusPlantCarrot() {
        game.setStatusPlantCarrot();
    }
    public void setStatusPlantCabbage() {
        game.setStatusPlantCabbage();
    }


    public void setStatusUnplant() {
        game.setStatusUnplant();
    }

    public ReadOnlyBooleanProperty isOnProperty() {
        return game.isOnProperty();
    }


    public ReadOnlyStringProperty grassLabelProperty() {
        return new SimpleStringProperty("Planter du gazon");
    }

    public ReadOnlyObjectProperty<ImageView> grassImageProperty() {
        return new SimpleObjectProperty<>(new ImageView(new Image("grass.png")));
    }

    public ReadOnlyStringProperty carrotLabelProperty() {
        return new SimpleStringProperty("Planter des carottes");
    }

    public ReadOnlyObjectProperty<ImageView> carrotImageProperty() {
        return new SimpleObjectProperty<>(new ImageView(new Image("carrot4.png")));
    }

    public ReadOnlyStringProperty cabbageLabelProperty() {
        return new SimpleStringProperty("Planter des choux");
    }

    public ReadOnlyObjectProperty<ImageView> cabbageImageProperty() {
        return new SimpleObjectProperty<>(new ImageView(new Image("cabbage4.png")));
    }

    public ReadOnlyStringProperty fertilizeLabelProperty() {
        return new SimpleStringProperty("Fertiliser");
    }

    public ReadOnlyObjectProperty<ImageView> fertilizeImageProperty() {
        return new SimpleObjectProperty<>(new ImageView(new Image("watering_can.png")));
    }

    public ReadOnlyStringProperty collectLabelProperty() {
        return new SimpleStringProperty("Récolter");
    }

    public ReadOnlyObjectProperty<ImageView> collectImageProperty() {
        return new SimpleObjectProperty<>(new ImageView(new Image("shovel.png")));
    }

}

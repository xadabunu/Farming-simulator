package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.GameFacade;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;

public class MenuViewModel {

    private final GameFacade game;

    MenuViewModel(GameFacade game) {
        this.game = game;
    }

    public void start() {
        game.start();
    }

    public void plant() {
        game.plant();
    }

    public void unPlant() {
        game.unPlant();
    }

    public ReadOnlyStringProperty startLabelProperty() {
        if(!game.isOnProperty().get()){
            return new SimpleStringProperty("Démarrer");
        }
        else {
            return new SimpleStringProperty("Arreter");
        }
    }

    public ReadOnlyStringProperty plantLabelProperty() {
        return new SimpleStringProperty("Planter du gazon");
    }

    public ReadOnlyStringProperty unplantLabelProperty() {
        return new SimpleStringProperty("Enlever du gazon");
    }

    public ReadOnlyBooleanProperty isOnProperty() {
        return game.isOnProperty();
    }

}

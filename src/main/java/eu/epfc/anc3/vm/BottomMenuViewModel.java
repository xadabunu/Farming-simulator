package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.GameFacade;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;

public class BottomMenuViewModel {

    private final GameFacade game;

    BottomMenuViewModel(GameFacade game) {
        this.game = game;
    }

    public void start() {
        game.start();
    }

    public void sleep() {
        game.sleep();
    }
    public void save() {
        game.save();
    }
    public void restore() {
        game.restore();
    }


    public ReadOnlyStringProperty startLabelProperty() {
        if(!game.isOnProperty().get()){
            return new SimpleStringProperty("Démarrer");
        }
        else {
            return new SimpleStringProperty("Arreter");
        }
    }

    public ReadOnlyStringProperty sleepLabelProperty() {
        return new SimpleStringProperty("Dormir");
    }
    public ReadOnlyStringProperty saveLabelProperty() {
        return new SimpleStringProperty("Sauvegarder");
    }
    public ReadOnlyStringProperty restoreLabelProperty() {
        return new SimpleStringProperty("Restaurer");
    }

    public ReadOnlyBooleanProperty isOnProperty() {
        return game.isOnProperty();
    }

    public ReadOnlyBooleanProperty hasSavedProperty() {
        return game.hasSavedProperty();
    }

}

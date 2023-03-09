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

    public ReadOnlyBooleanProperty isOnProperty() {
        return game.isOnProperty();
    }

}

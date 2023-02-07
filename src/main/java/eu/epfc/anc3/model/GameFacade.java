package eu.epfc.anc3.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class GameFacade {

    private final Game game = new Game();

    private final BooleanProperty isOn = new SimpleBooleanProperty(false);

    public ReadOnlyBooleanProperty isOnProperty() {return isOn;}



    public ReadOnlyObjectProperty<LandContend> contendProperty(int line, int col) {
        return game.contendProperty(line ,col);
    }


    public LandContend teleport(int line, int col) {
        return game.teleport(line, col);
    }
}

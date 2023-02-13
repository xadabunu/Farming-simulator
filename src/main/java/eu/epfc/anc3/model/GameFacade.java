package eu.epfc.anc3.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class GameFacade {

    private final Game game = new Game();

    private final BooleanProperty isOn = new SimpleBooleanProperty(false);

    public ReadOnlyBooleanProperty isOnProperty() {return isOn;}

    public ReadOnlyObjectProperty<LandContent> contentProperty(int line, int col) {
        return game.contentProperty(line ,col);
    }

    public ReadOnlyObjectProperty<GameStatus> gameStatusProperty() {
        return game.gameStatusProperty();
    }

    public static int fieldCol(){
        return Field.COLUMNS;
    }
    public static int fieldLines(){
        return Field.LINES;
    }

    public void start() {
        game.start();
    }

    public void teleport(int line, int col) {
        game.teleport(line, col);
    }
}

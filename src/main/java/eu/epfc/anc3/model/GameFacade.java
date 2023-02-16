package eu.epfc.anc3.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class GameFacade {

    private final Game game = new Game();

    private final BooleanProperty isOn = new SimpleBooleanProperty(false);
    private final BooleanProperty isPlant = new SimpleBooleanProperty(false);
    private final BooleanProperty isUnplant = new SimpleBooleanProperty(false);

    public ReadOnlyBooleanProperty isOnProperty() {
        return isOn;
    }
    public ReadOnlyBooleanProperty isPlantProperty() {
        return isPlant;
    }
    public ReadOnlyBooleanProperty isUnplantProperty() {
        return isUnplant;
    }

    public ReadOnlyObjectProperty<LandContent> contentProperty(int line, int col) {
        return game.contentProperty(line ,col);
    }

    public GameFacade() {
        isOn.bind(game.gameStatusProperty().isNotEqualTo(GameStatus.GAME_OFF));
        isPlant.bind(game.gameStatusProperty().isEqualTo(GameStatus.PLANT));
        isUnplant.bind(game.gameStatusProperty().isEqualTo(GameStatus.UNPLANT));

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

    public void plant() {
        game.plant();
    }

    public void unPlant() {
        game.unPlant();
    }

    public void stop() {
        game.stop();
    }

    public void setCharacterPosition(Character c, int line, int col) {
        game.setCharacterPosition(c, line, col);
    }

    public void teleport(int line, int col) {
        game.teleport(line, col);
    }
}

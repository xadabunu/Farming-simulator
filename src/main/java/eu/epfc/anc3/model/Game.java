package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Game {
    private Field field = new Field();
    private final ObjectProperty<GameStatus> gameStatus = new SimpleObjectProperty<>(GameStatus.GAME_OFF);
    private final Character farmer = new Farmer();
    private Land characterPosition = field.getLand(0, 0);

    void start() {
        if (gameStatus.isEqualTo(GameStatus.GAME_OFF).get()) {
            field = new Field();
            gameStatus.set(GameStatus.GAME_ON);
        }
        else {
            gameStatus.set(GameStatus.GAME_OFF);
        }
    }

    void plant() {
        gameStatus.set(gameStatus.isEqualTo(GameStatus.PLANT).get() ? GameStatus.GAME_ON : GameStatus.PLANT);
    }

    void unPlant() {
        gameStatus.set(gameStatus.isEqualTo(GameStatus.UNPLANT).get() ? GameStatus.GAME_ON : GameStatus.UNPLANT);
    }

    void setCharacterPosition(Character c, int line, int col) {
        //a toi olivier (ou xavier) !
    }

    void stop() {
        gameStatus.set(GameStatus.GAME_OFF);
    }

    private GameStatus status() {
        return this.gameStatus.get();
    }

    public void teleport(int line, int col) {
        characterPosition = field.getLand(line, col);
    }

    ReadOnlyObjectProperty<LandContent> contentProperty(int line, int col) {
        return field.contentProperty(line, col);
    }

    ReadOnlyObjectProperty<GameStatus> gameStatusProperty() {
        return gameStatus;
    }

}

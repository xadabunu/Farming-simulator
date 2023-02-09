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
        field = new Field();
        gameStatus.set(GameStatus.GAME_ON);
    }

    public void teleport(int line, int col) {
        characterPosition = field.getLand(line, col);
    }

    ReadOnlyObjectProperty<LandContent> contentProperty(int line, int col) {
        return field.contentProperty(line, col);
    }

}

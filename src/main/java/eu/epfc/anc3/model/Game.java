package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Game {


    private Field field;
    private final ObjectProperty<GameStatus> gameStatus = new SimpleObjectProperty<>(GameStatus.GAME_OFF);

    void start() {
        field = new Field();
        gameStatus.set(GameStatus.GAME_ON);
    }

    

    ReadOnlyObjectProperty<LandContend> contendProperty(int line, int col) {
        return field.contendProperty(line, col);
    }

}

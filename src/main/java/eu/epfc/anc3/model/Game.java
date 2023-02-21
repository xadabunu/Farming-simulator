package eu.epfc.anc3.model;

import javafx.beans.property.*;

class Game {
    private final Character character = new Farmer();
    private final ObjectProperty<GameStatus> gameStatus = new SimpleObjectProperty<>(GameStatus.GAME_OFF);
    public final IntegerProperty ctr = new SimpleIntegerProperty(0);
    private final Field field = new Field();

    void start() {
        if (gameStatus.isEqualTo(GameStatus.GAME_OFF).get()) {
            character.resetPosition();
            field.reset();
            ctr.set(0);
            gameStatus.set(GameStatus.GAME_ON);

        }
        else {
            gameStatus.set(GameStatus.GAME_OFF);
        }
    }

    void setStatusPlant() {
        gameStatus.set(gameStatus.isEqualTo(GameStatus.PLANT).get() ? GameStatus.GAME_ON : GameStatus.PLANT);
    }

    void setStatusUnplant() {
        gameStatus.set(gameStatus.isEqualTo(GameStatus.UNPLANT).get() ? GameStatus.GAME_ON : GameStatus.UNPLANT);
    }

    ReadOnlyObjectProperty<Position> characterPositionProperty() {
        return character.characterPositionProperty();
    }

    void teleport(int line, int col) {
        if (!gameStatus.isEqualTo(GameStatus.GAME_OFF).get())
            character.teleport(line, col);
    }

    ReadOnlyObjectProperty<LandContent> contentProperty(int line, int col) {
        return field.contentProperty(line, col);
    }

    ReadOnlyObjectProperty<GameStatus> gameStatusProperty() {
        return gameStatus;
    }

    private boolean plantUnplant() {
        if (gameStatus.isEqualTo(GameStatus.PLANT).get())
            return plantGrass();
        else if (gameStatus.isEqualTo(GameStatus.UNPLANT).get())
            return unplantGrass();
        return false;
    }

    private boolean plantGrass() {
        var pos = field.getLand(characterPositionProperty().getValue().getLine(), characterPositionProperty().get().getCol());
        if (pos.contentProperty().isEqualTo(LandContent.DIRT).get()) {
            pos.setContent(LandContent.GRASS);
            return true;
        }
        return false;
    }

    private boolean unplantGrass() {
        var pos = field.getLand(characterPositionProperty().getValue().getLine(), characterPositionProperty().get().getCol());
        if (!pos.contentProperty().isEqualTo(LandContent.DIRT).get()) {
            pos.setContent(LandContent.DIRT);
            return true;
        }
        return false;
    }

    boolean counterManager() {
        if (plantUnplant()) {
            if(gameStatusProperty().isEqualTo(GameStatus.PLANT).get())
                ctr.setValue(ctr.intValue() + 1);
            else
                ctr.setValue(ctr.intValue() - 1);
            return true;
        }
        return false;
    }

    void move(Direction d) {
        if (!gameStatus.isEqualTo(GameStatus.GAME_OFF).get()) {
            character.move(d);
        }
    }
}

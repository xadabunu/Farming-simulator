package eu.epfc.anc3.model;

import javafx.beans.property.*;

public class Game {
    private Field field = new Field();
    private final ObjectProperty<GameStatus> gameStatus = new SimpleObjectProperty<>(GameStatus.GAME_OFF);
    private ObjectProperty<Position> characterPosition = new SimpleObjectProperty<>(new Position(0, 0));
    public final IntegerProperty ctr = new SimpleIntegerProperty(0);

    public ReadOnlyObjectProperty<Field> fieldProperty() {
        return new SimpleObjectProperty<>(field);
    }
    void start() {
        if (gameStatus.isEqualTo(GameStatus.GAME_OFF).get()) {
            ctr.set(0);
            characterPosition.set(new Position(0, 0));
            gameStatus.set(GameStatus.GAME_ON);
        }
        else {
            gameStatus.set(GameStatus.GAME_OFF);
        }
    }

    void plant() {
        gameStatus.set(gameStatus.isEqualTo(GameStatus.PLANT).get() ? GameStatus.GAME_ON : GameStatus.PLANT);
    }

    void unplant() {
        gameStatus.set(gameStatus.isEqualTo(GameStatus.UNPLANT).get() ? GameStatus.GAME_ON : GameStatus.UNPLANT);
    }

    ReadOnlyObjectProperty<Position> characterPositionProperty() {
        return characterPosition;
    }
    
    private GameStatus status() {
        return this.gameStatus.get();
    }

    public void teleport(int line, int col) {
        if (!gameStatus.isEqualTo(GameStatus.GAME_OFF).get())
            characterPosition.set(new Position(line, col));
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
        var pos = field.getLand(characterPosition.getValue().getLine(), characterPosition.get().getCol());
        if (pos.contentProperty().isEqualTo(LandContent.DIRT).get()) {
            pos.setContent(LandContent.GRASS);
            return true;
        }
        return false;
    }

    private boolean unplantGrass() {
        var pos = field.getLand(characterPosition.getValue().getLine(), characterPosition.get().getCol());
        if (!pos.contentProperty().isEqualTo(LandContent.DIRT).get()) {
            pos.setContent(LandContent.DIRT);
            return true;
        }
        return false;
    }

    public boolean counterManager() {
        if (plantUnplant()) {
            if(gameStatusProperty().isEqualTo(GameStatus.PLANT).get())
                ctr.setValue(ctr.intValue() + 1);
            else
                ctr.setValue(ctr.intValue() - 1);
            return true;
        }
        return false;
    }

    public void move(Direction d) {
        var pos = characterPosition.get();
        switch (d) {
            case UP -> {
                if (pos.getLine() > 0)
                    teleport(pos.getLine() - 1, pos.getCol());
            }
            case DOWN -> {
                if (pos.getLine() < Field.LINES - 1)
                    teleport(pos.getLine() + 1, pos.getCol());
            }
            case RIGHT -> {
                if (pos.getCol() < Field.COLUMNS - 1)
                    teleport(pos.getLine(), pos.getCol() + 1);
            }
            case LEFT -> {
                if (pos.getCol() > 0)
                    teleport(pos.getLine(), pos.getCol() - 1);
            }
        }
    }
}

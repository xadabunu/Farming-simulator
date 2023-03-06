package eu.epfc.anc3.model;

import javafx.beans.property.*;

class Game {

    private final Farmer farmer = new Farmer();
    private final ObjectProperty<GameStatus> gameStatus = new SimpleObjectProperty<>(GameStatus.GAME_OFF);
    public final IntegerProperty ctrScore = new SimpleIntegerProperty(0);
    public final IntegerProperty ctrDays = new SimpleIntegerProperty(0);
    private final Field field = new Field();


//----------------- Methode Start/Reset + Methodes modifiant le status -----------------

    void start() {
        if (gameStatus.isEqualTo(GameStatus.GAME_OFF).get()) {
            farmer.resetPosition();
            field.reset();
            ctrScore.set(0);
            ctrDays.set(0);
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


//----------------- Gestion compteur + Planter/Déplanter -----------------

    private boolean plantUnplant() {
        if (gameStatus.isEqualTo(GameStatus.PLANT).get())
            return plantGrass();
        else if (gameStatus.isEqualTo(GameStatus.UNPLANT).get())
            return unplant();
        return false;
    }

    private boolean plantGrass() {
        var pos = field.getLand(characterPositionProperty().getValue().getLine(), characterPositionProperty().get().getCol());
        return farmer.plantGrass(pos);
    }

    private boolean unplant() {
        var pos = field.getLand(characterPositionProperty().getValue().getLine(), characterPositionProperty().get().getCol());
        return farmer.unplant(pos);
    }

    boolean counterManager() {
        if (plantUnplant()) {
            if(gameStatusProperty().isEqualTo(GameStatus.PLANT).get())
                ctrScore.setValue(ctrScore.intValue() + 1);
            else
                ctrScore.setValue(ctrScore.intValue() - 1);
            return true;
        }
        return false;
    }


//-------------------------- Mouvement -------------------------------

    void move(Direction d) {
        if (!gameStatus.isEqualTo(GameStatus.GAME_OFF).get()) {
            farmer.move(d);
        }
    }

    void teleport(int line, int col) {
        if (!gameStatus.isEqualTo(GameStatus.GAME_OFF).get())
            farmer.teleport(line, col);
    }


//----------------- ReadOnlyProperty -----------------

    ReadOnlyObjectProperty<Position> characterPositionProperty() {
        return farmer.characterPositionProperty();
    }

    ReadOnlyObjectProperty<LandContent> contentProperty(int line, int col) {
        return field.contentProperty(line, col);
    }

    ReadOnlyObjectProperty<GameStatus> gameStatusProperty() {
        return gameStatus;
    }

}

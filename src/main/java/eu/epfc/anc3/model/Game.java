package eu.epfc.anc3.model;

import javafx.beans.property.*;

class Game {

    private final ObjectProperty<GameStatus> gameStatus = new SimpleObjectProperty<>(GameStatus.GAME_OFF);
    public final IntegerProperty counter = new SimpleIntegerProperty(0);
    private final Field field = new Field();
    private final Farmer farmer = new Farmer(field);


    /* --------- Methode Start/Reset + Methodes modifiant le status --------- */

    void start() {
        if (gameStatus.isEqualTo(GameStatus.GAME_OFF).get()) {
            farmer.resetPosition();
            field.reset();
            counter.set(0);
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


/* ----------------- Gestion compteur + Planter/Déplanter ----------------- */

    private boolean plantUnplant() {
        if (gameStatus.isEqualTo(GameStatus.PLANT).get())
            return plantGrass();
        else if (gameStatus.isEqualTo(GameStatus.UNPLANT).get())
            return unplant();
        return false;
    }

    private boolean plantGrass() {
        return farmer.plantGrass();
    }

    private boolean unplant() {
        return farmer.unplant();
    }

    boolean counterManager() {
        if (plantUnplant()) {
            if(gameStatusProperty().isEqualTo(GameStatus.PLANT).get())
                counter.setValue(counter.intValue() + 1);
            else
                counter.setValue(counter.intValue() - 1);
            return true;
        }
        return false;
    }


/* ------------------------------- Mouvement ------------------------------- */

    void move(Direction d) {
        if (!gameStatus.isEqualTo(GameStatus.GAME_OFF).get()) {
            farmer.move(d);
        }
    }

    void teleport(int line, int col) {
        if (!gameStatus.isEqualTo(GameStatus.GAME_OFF).get())
            farmer.teleport(line, col);
    }


/* ---------------------------- ReadOnlyProperty -------------------------- */

    ReadOnlyObjectProperty<Land> characterPositionProperty() {
        return farmer.characterPositionProperty();
    }

    ReadOnlyObjectProperty<LandContent> contentProperty(int line, int col) {
        return field.contentProperty(line, col);
    }

    ReadOnlyObjectProperty<GameStatus> gameStatusProperty() {
        return gameStatus;
    }
}

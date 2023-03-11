package eu.epfc.anc3.model;

import javafx.beans.property.*;

class Game {

    private final ObjectProperty<GameStatus> gameStatus = new SimpleObjectProperty<>(GameStatus.GAME_OFF);
    private final ObjectProperty<Planting> planting = new SimpleObjectProperty<>();
    public final IntegerProperty ctrScore = new SimpleIntegerProperty(0);
    public final IntegerProperty ctrDays = new SimpleIntegerProperty(0);
    private final Field field = new Field();
    private final Farmer farmer = new Farmer(field);


/* --------- Methode Start/Reset + Methodes modifiant le status --------- */

    void start() {
        if (gameStatus.isEqualTo(GameStatus.GAME_OFF).get()) {
            farmer.resetPosition();
            field.reset();
            ctrScore.set(0);
            ctrDays.set(0);
            ctrDays.addListener((obs, oldValue, newValue) -> farmer.manageField());
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

    private void switchPlanting(Planting p) {
        if (!gameStatus.isEqualTo(GameStatus.PLANT).get()) {
            gameStatus.set(GameStatus.PLANT);
            planting.set(p);
        }
        else {
            if (planting.isEqualTo(p).get())
                gameStatus.set(GameStatus.GAME_ON);
            else
                planting.set(p);
        }
    }

    public void setStatusPlantGrass() {
        switchPlanting(Planting.GRASS);
    }

    public void setStatusPlantCarrot() {
        switchPlanting(Planting.CARROT);
    }

    public void setStatusPlantCabbage() {
        switchPlanting(Planting.CABBAGE);
    }


/* ----------------- Gestion compteur + Planter/Déplanter ----------------- */

    private boolean plantUnplant() {
        if (gameStatus.isEqualTo(GameStatus.PLANT).get())
            return switch (planting.get()) {
                case GRASS -> plantGrass();
                case CARROT -> plantCarrot();
                case CABBAGE -> plantCabbage();
            };
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

    private boolean plantCarrot() {
        return farmer.plantCarrot();
    }

    private boolean plantCabbage() {
        return farmer.plantCabbage();
    }

    boolean counterManager() {
        if (plantUnplant()) {
            if (gameStatusProperty().isEqualTo(GameStatus.PLANT).get())
                ctrScore.setValue(ctrScore.intValue() + 1);
            else
                ctrScore.setValue(ctrScore.intValue() - 1);
            return true;
        }
        return false;
    }

    public void fertilize() {
//        farmer.fertilize();
    }

    public void sleep(){
        ctrDays.setValue(ctrDays.get()+1);
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

    ReadOnlyObjectProperty<LandGrowable> growableProperty(int line, int col) {
        return field.growableProperty(line, col);
    }

    ReadOnlyObjectProperty<GameStatus> gameStatusProperty() {
        return gameStatus;
    }
}

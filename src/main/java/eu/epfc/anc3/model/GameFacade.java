package eu.epfc.anc3.model;

import javafx.beans.property.*;

public class GameFacade {

    private final Game game = new Game();
    private final BooleanProperty isOn = new SimpleBooleanProperty(false);

    private final BooleanProperty hasSaved = new SimpleBooleanProperty(false);

    public GameFacade() {
        isOn.bind(game.gameStatusProperty().isNotEqualTo(GameStatus.GAME_OFF));
    }

    public static int fieldCol() {
        return Field.COLUMNS;
    }

    public static int fieldLines() {
        return Field.LINES;
    }


/* ---------- Methode Start/Reset + Methodes modifiant le status ---------- */

    public void start() {
        game.start();
    }

    public void sleep () {
        game.sleep();
    }
    public void save () {
        game.save();
    }
    public void restore () {
        game.restore();
    }

    public void setStatusPlantGrass() {
        game.setStatusPlantGrass();
    }

    public void setStatusPlantCarrot() {
        game.setStatusPlantCarrot();
    }

    public void setStatusPlantCabbage() {
        game.setStatusPlantCabbage();
    }

    public void setStatusUnplant() {
        game.setStatusUnplant();
    }


/* ----------------- Planter/Déplanter + Retour counter ----------------- */

    public boolean plantUnplant() {
        return game.counterManager();
    }

    public void fertilize() {
        game.setStatusFertilize();
    }

    public IntegerProperty ctrScoreProperty() {
        return game.ctrScore;
    }

    public IntegerProperty ctrDaysProperty() {
        return game.ctrDays;
    }


/* -------------------------- Mouvement ------------------------------- */

    public void teleport(int line, int col) {
        game.teleport(line, col);
    }

    public void move(Direction d) {
        game.move(d);
    }


/* ------------------------- ReadOnlyProperty ------------------------- */

    public ReadOnlyBooleanProperty isOnProperty() {
        return isOn;
    }

    public ReadOnlyBooleanProperty hasSavedProperty(){
        return game.hasSavedProperty();
    }

    public ReadOnlyObjectProperty<LandContent> contentProperty(int line, int col) {
        return game.contentProperty(line, col);
    }

    public ReadOnlyObjectProperty<LandGrowable> growableProperty(int line, int col) {
        return game.growableProperty(line, col);
    }
    public ReadOnlyObjectProperty<GrowingState> growableState(int line, int col) {
        return game.growableState(line, col);
    }

    public ReadOnlyObjectProperty<Land> characterPositionProperty() {
        return game.characterPositionProperty();
    }
}

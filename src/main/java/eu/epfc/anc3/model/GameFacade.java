package eu.epfc.anc3.model;

import javafx.beans.property.*;

public class GameFacade {

    private final Game game = new Game();
    private final BooleanProperty isOn = new SimpleBooleanProperty(false);

    public ReadOnlyBooleanProperty isOnProperty() {
        return isOn;
    }

    public ReadOnlyObjectProperty<LandContent> contentProperty(int line, int col) {
        return game.contentProperty(line ,col);
    }

    public IntegerProperty ctrScoreProperty() {
        return game.ctrScore;
    }
    public IntegerProperty ctrDaysProperty() {
        return game.ctrDays;
    }

    public ReadOnlyObjectProperty<Position> characterPositionProperty() {
        return game.characterPositionProperty();
    }
    public GameFacade() {
        isOn.bind(game.gameStatusProperty().isNotEqualTo(GameStatus.GAME_OFF));
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

    public void setStatusPlant() {
        game.setStatusPlant();
    }

    public void setStatusUnplant() {
        game.setStatusUnplant();
    }

    public void teleport(int line, int col) {
        game.teleport(line, col);
    }

    public boolean plantUnplant(){
        return game.counterManager();
    }

    public void move(Direction d) {
        game.move(d);
    }
}

package eu.epfc.anc3.model;

import javafx.beans.property.*;

public class Game {
    private Field field = new Field();
    private final ObjectProperty<GameStatus> gameStatus = new SimpleObjectProperty<>(GameStatus.GAME_OFF);
    private final Character farmer = new Farmer();
    private Land characterPosition = field.getLand(0, 0);

    public final IntegerProperty ctr = new SimpleIntegerProperty(5);

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


    public boolean plantUnplant() {
        if(gameStatus.isEqualTo(GameStatus.PLANT).get()) {
            return plantGrass();
        }
        else if (gameStatus.isEqualTo(GameStatus.UNPLANT).get()){
            return unplantGrass();
        }
        return false;
    }

    private boolean plantGrass() {
        if (characterPosition.getValue() == LandContent.DIRT) {
            characterPosition.setValue(LandContent.GRASS);
//            updateCtr();
            return true;
        }
        return false;
    }

    private boolean unplantGrass() {
        if (characterPosition.getValue() != LandContent.DIRT) {
            characterPosition.setValue(LandContent.DIRT);
//            updateCtr();
            return true;
        }
        return false;
    }

    public boolean counterManager() {
        if (plantUnplant()){
            if(gameStatusProperty().isEqualTo(GameStatus.PLANT).get()){
                ctr.setValue(ctr.intValue()+1);
            }
            else {
                ctr.setValue(ctr.intValue()-1);
            }
//            System.out.println(ctr);
            return true;
        }
        return false;
    }

//    void updateCtr() {
//        ctr.set(ctr.get() + (gameStatusProperty().isEqualTo(GameStatus.PLANT).get() ? 1 : -1 ));
//    }
}

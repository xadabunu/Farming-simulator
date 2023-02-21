package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

abstract class Character {
    private final ObjectProperty<Position> characterPosition = new SimpleObjectProperty<>(new Position(0, 0));
    Character(Game game) {
        characterPosition.bindBidirectional(game.characterPositionProperty());
    }
    void move(Direction d) {
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
    void teleport(int line, int col) {
        characterPosition.set(new Position(line, col));
    }
}

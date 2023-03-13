package eu.epfc.anc3.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

abstract class Character {

    protected final Field field;
    protected final ObjectProperty<Land> position;

    Character(Field field) {
        this.field = field;
        position = new SimpleObjectProperty<>(field.getLand(0, 0));
    }

    void move(Direction d) {
        switch (d) {
            case UP -> {
                if (position.get().getLine() > 0)
                    teleport(position.get().getLine() - 1, position.get().getCol());
            }
            case DOWN -> {
                if (position.get().getLine() < Field.LINES - 1)
                    teleport(position.get().getLine() + 1, position.get().getCol());
            }
            case RIGHT -> {
                if (position.get().getCol() < Field.COLUMNS - 1)
                    teleport(position.get().getLine(), position.get().getCol() + 1);
            }
            case LEFT -> {
                if (position.get().getCol() > 0)
                    teleport(position.get().getLine(), position.get().getCol() - 1);
            }
        }
    }

    void teleport(int line, int col) {
        position.set(field.getLand(line, col));
    }

    void resetPosition() {
        position.set(field.getLand(0, 0));
    }

    ReadOnlyObjectProperty<Land> characterPositionProperty() {
        return position;
    }
}

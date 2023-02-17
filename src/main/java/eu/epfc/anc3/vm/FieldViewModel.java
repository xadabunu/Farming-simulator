package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.Character;
import eu.epfc.anc3.model.GameFacade;
import eu.epfc.anc3.model.Land;
import eu.epfc.anc3.model.Position;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

public class FieldViewModel {

    private final GameFacade game;
    public FieldViewModel(GameFacade game) {
        this.game = game;
    }

    public LandViewModel getLandViewModel(int line, int col) {
        return new LandViewModel(line, col, game);
    }

    public void setCharacterPosition(Character c, int line, int col) {
        game.setCharacterPosition(c, line, col);
    }

    public ReadOnlyObjectProperty<Position> characterPositionProperty() {
        //var pos = game.characterPositionProperty().get().getPosition();
        //return new SimpleObjectProperty<>(new Position(pos.getLine(), pos.getCol()));
        return game.characterPositionProperty();
    }
}

package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.GameFacade;
import eu.epfc.anc3.model.Land;
import javafx.beans.property.ReadOnlyObjectProperty;

public class FieldViewModel {

    private final GameFacade game;

    FieldViewModel(GameFacade game) {
        this.game = game;
    }

    public LandViewModel getLandViewModel(int line, int col) {
        return new LandViewModel(line, col, game);
    }

    public ReadOnlyObjectProperty<Land> characterPositionProperty() {
        return game.characterPositionProperty();
    }
}

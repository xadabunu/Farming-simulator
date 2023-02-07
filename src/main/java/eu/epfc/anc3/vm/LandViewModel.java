package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.GameFacade;
import eu.epfc.anc3.model.LandContent;
import javafx.beans.property.ReadOnlyObjectProperty;

public class LandViewModel {

    private final int line, col;
    private final GameFacade game;

    public LandViewModel(int line, int col, GameFacade game) {
        this.line = line;
        this.col = col;
        this.game = game;
    }

    public void teleport() {
        game.teleport(line, col);
    }

    public ReadOnlyObjectProperty<LandContent> valueProperty() {
        return game.valueProperty(line, col);
    }

    //public ReadOnlyObjectProperty<LandContent>
}

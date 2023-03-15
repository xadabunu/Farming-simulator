package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.GameFacade;
import eu.epfc.anc3.model.GrowingState;
import eu.epfc.anc3.model.LandContent;
import eu.epfc.anc3.model.LandGrowable;
import javafx.beans.property.ReadOnlyObjectProperty;

public class LandViewModel {

    private final int line, col;
    private final GameFacade game;

    LandViewModel(int line, int col, GameFacade game) {
        this.line = line;
        this.col = col;
        this.game = game;
    }

    public void teleport() {
        game.teleport(line, col);
    }

    public ReadOnlyObjectProperty<LandContent> contentProperty() {
        return game.contentProperty(line, col);
    }

    public ReadOnlyObjectProperty<LandGrowable> growableProperty() {
        return game.growableProperty(line, col);
    }
    public ReadOnlyObjectProperty<GrowingState> growableState() {
        return game.growableState(line, col);
    }

}

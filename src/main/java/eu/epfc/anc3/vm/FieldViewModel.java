package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.GameFacade;

public class FieldViewModel {

    private final GameFacade game;
    public FieldViewModel(GameFacade game) {
        this.game = game;
    }

    public LandViewModel getLandViewModel(int line, int col) {
        return new LandViewModel(line, col, game);
    }

}

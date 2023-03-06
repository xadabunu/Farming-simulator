package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.GameFacade;
import javafx.beans.property.*;

public class CounterViewModel {

    private final GameFacade game;

    public CounterViewModel(GameFacade game) {
        this.game = game;
    }

    public ReadOnlyStringProperty labelScoreProperty() {
        return new SimpleStringProperty("Score :");
    }
    public ReadOnlyStringProperty labelDaysProperty() {
        return new SimpleStringProperty("Jour :");
    }
    public IntegerProperty counterScoreProperty() {
        return game.ctrScoreProperty();
    }
    public IntegerProperty counterDaysProperty() {
        return game.ctrDaysProperty();
    }
}

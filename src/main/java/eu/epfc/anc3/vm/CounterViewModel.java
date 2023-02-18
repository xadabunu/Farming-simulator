package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.GameFacade;
import javafx.beans.property.*;

public class CounterViewModel {

    private final GameFacade game;

    public CounterViewModel(GameFacade game) {
        this.game = game;
    }

    public ReadOnlyStringProperty counterLabelProperty() {
        return new SimpleStringProperty("Nombre de parcelles de gazons");
    }

    public IntegerProperty counterProperty() {
        return game.ctrProperty();

    }
}
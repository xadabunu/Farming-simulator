package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.Direction;
import eu.epfc.anc3.model.GameFacade;
import javafx.beans.property.*;

public class AppViewModel {

    private final MenuViewModel menuViewModel;
    private final FieldViewModel fieldViewModel;
    private final CounterViewModel counterViewModel;
    private final GameFacade game = new GameFacade();

    public FieldViewModel getFieldViewModel() {
        return fieldViewModel;
    }
    public MenuViewModel getMenuViewModel() {
        return menuViewModel;
    }

    public CounterViewModel getCounterViewModel() {
        return counterViewModel;
    }

    public AppViewModel() {
        menuViewModel = new MenuViewModel(game);
        fieldViewModel = new FieldViewModel(game);
        counterViewModel = new CounterViewModel(game);
    }

    public ReadOnlyStringProperty titleProperty() {
        return new SimpleStringProperty("ANC3 - Farming Simulator");
    }

    public void plantUnplant() {
        game.plantUnplant();
    }

    public void move(Direction d) {
        game.move(d);
    }
}

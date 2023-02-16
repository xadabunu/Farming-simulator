package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.GameFacade;
import eu.epfc.anc3.model.GameStatus;
import eu.epfc.anc3.view.CounterView;
import javafx.beans.property.*;
import javafx.scene.layout.VBox;

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
        return new SimpleStringProperty("OUR FARM");
    }

    public ReadOnlyBooleanProperty isOnProperty() {
        return game.isOnProperty();
    }


    public void plantUnplant() {
        game.plantUnplant();
    }
}

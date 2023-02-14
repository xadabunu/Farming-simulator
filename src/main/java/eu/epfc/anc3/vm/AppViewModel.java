package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.GameFacade;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.layout.VBox;

public class AppViewModel {

    private final MenuViewModel menuViewModel;
    private final FieldViewModel fieldViewModel;
    private final GameFacade game = new GameFacade();

    public FieldViewModel getFieldViewModel() {
        return fieldViewModel;
    }
    public MenuViewModel getMenuViewModel() {
        return menuViewModel;
    }

    public AppViewModel() {
        menuViewModel = new MenuViewModel(game);
        fieldViewModel = new FieldViewModel(game);
    }

    public ReadOnlyStringProperty titleProperty() {
        return new SimpleStringProperty("OUR FARM");
    }

    public ReadOnlyBooleanProperty isOnProperty() {
        return game.isOnProperty();
    }
}

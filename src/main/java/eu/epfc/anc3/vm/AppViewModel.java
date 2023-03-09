package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.Direction;
import eu.epfc.anc3.model.GameFacade;
import javafx.beans.property.*;

public class AppViewModel {

    private final BottomMenuViewModel bottomMenuViewModel;
    private final SideMenuViewModel sideMenuViewModel;
    private final FieldViewModel fieldViewModel;
    private final CounterViewModel counterViewModel;
    private final GameFacade game = new GameFacade();

    public AppViewModel() {
        bottomMenuViewModel = new BottomMenuViewModel(game);
        sideMenuViewModel = new SideMenuViewModel(game);
        fieldViewModel = new FieldViewModel(game);
        counterViewModel = new CounterViewModel(game);
    }

    public void plantUnplant() {
        game.plantUnplant();
    }

    public void move(Direction d) {
        game.move(d);
    }

    public FieldViewModel getFieldViewModel() {
        return fieldViewModel;
    }

    public BottomMenuViewModel getBottomMenuViewModel() {
        return bottomMenuViewModel;
    }
    public SideMenuViewModel getSideMenuViewModel() {
        return sideMenuViewModel;
    }
    public CounterViewModel getCounterViewModel() {
        return counterViewModel;
    }

    public ReadOnlyStringProperty titleProperty() {
        return new SimpleStringProperty("ANC3 - Farming Simulator");
    }
}

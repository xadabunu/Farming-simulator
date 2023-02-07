package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.MenuViewModel;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MenuView extends BorderPane {

    private final MenuViewModel menuViewModel;

    public MenuView(MenuViewModel menuViewModel) {
        this.menuViewModel = menuViewModel;
    }

}

package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.MenuViewModel;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MenuView extends HBox {

    private final MenuViewModel menuViewModel;

    public MenuView(MenuViewModel menuViewModel) {
        this.menuViewModel = menuViewModel;
    }

}

package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.MenuViewModel;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MenuView extends HBox {

    private final MenuViewModel menuViewModel;

    public MenuView(MenuViewModel menuViewModel) {
        this.menuViewModel = menuViewModel;

        configButtons();
        setAlignment(Pos.CENTER);
        setSpacing(30);
    }

    private void configButtons() {
        var btnSwitch = new Button("Démarrer");
        var btnPlant = new Button("Planter du gazon");
        var btnUnplant = new Button("Enlever du gazon");

        getChildren().addAll(btnSwitch, btnPlant, btnUnplant);
    }

}

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
    private final Button btnSwitch = new Button();
    private final Button btnPlant = new Button();
    private final Button btnUnplant = new Button();

    public MenuView(MenuViewModel menuViewModel) {
        this.menuViewModel = menuViewModel;
        configButtons();
        manageBtnSwitch();

    }

    private void configButtons() {
        getChildren().addAll(btnSwitch, btnPlant, btnUnplant);
        configLabel();
        setAlignment(Pos.CENTER);
        setSpacing(30);
        btnPlant.setDisable(true);
        btnUnplant.setDisable(true);
    }

    private void configLabel() {
        btnSwitch.textProperty().bind(menuViewModel.startLabelProperty());
        btnPlant.textProperty().bind(menuViewModel.plantLabelProperty());
        btnUnplant.textProperty().bind(menuViewModel.unplantLabelProperty());
    }

    private void manageBtnSwitch(){
        btnSwitch.setOnAction(e -> menuViewModel.start());
    }

}

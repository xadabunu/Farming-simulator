package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.MenuViewModel;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;


class MenuView extends HBox {

    private final MenuViewModel menuViewModel;
    private final Button btnSwitch = new Button();
    private final ToggleButton btnPlant = new ToggleButton();
    private final ToggleButton btnUnplant = new ToggleButton();



    MenuView(MenuViewModel menuViewModel) {
        this.menuViewModel = menuViewModel;
        configButtons();
        manageBtn();
        configLogicBinding();
    }

    private void configButtons() {
        getChildren().addAll(btnSwitch, btnPlant, btnUnplant);
        configLabel();
        setAlignment(Pos.CENTER);
        setSpacing(30);
        ToggleGroup group = new ToggleGroup();
        btnPlant.setToggleGroup(group);
        btnUnplant.setToggleGroup(group);
    }

    private void configLabel() {
        btnSwitch.textProperty().bind(menuViewModel.startLabelProperty());
        btnPlant.textProperty().bind(menuViewModel.plantLabelProperty());
        btnUnplant.textProperty().bind(menuViewModel.unplantLabelProperty());
    }

    private void manageBtn(){
        btnSwitch.setOnAction(e -> menuViewModel.start());
        btnPlant.setOnAction(e -> menuViewModel.setStatusPlant());
        btnUnplant.setOnAction(e -> menuViewModel.setStatusUnplant());
    }

    private void configLogicBinding() {
        btnPlant.disableProperty().bind(menuViewModel.isOnProperty().not());
        btnUnplant.disableProperty().bind(menuViewModel.isOnProperty().not());
        menuViewModel.isOnProperty().addListener((obs, oldval, newval) -> {
            btnSwitch.textProperty().bind(menuViewModel.startLabelProperty());
        });
        menuViewModel.isOnProperty().addListener((obs, old, newVal) -> {
            btnPlant.setSelected(false);
            btnUnplant.setSelected(false);
        });

    }
}

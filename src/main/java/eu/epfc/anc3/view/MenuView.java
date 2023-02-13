package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.MenuViewModel;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.EventListener;

public class MenuView extends HBox {

    private final MenuViewModel menuViewModel;
    private final Button btnSwitch = new Button();
    private final Button btnPlant = new Button();
    private final Button btnUnplant = new Button();

    public MenuView(MenuViewModel menuViewModel) {
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
    }

    private void configLabel() {
        btnSwitch.textProperty().bind(menuViewModel.startLabelProperty());
        btnPlant.textProperty().bind(menuViewModel.plantLabelProperty());
        btnUnplant.textProperty().bind(menuViewModel.unplantLabelProperty());
    }

    private void manageBtn(){
        btnSwitch.setOnAction(e -> menuViewModel.start());
        btnPlant.setOnAction(e -> menuViewModel.plant());
        btnUnplant.setOnAction(e -> menuViewModel.unPlant());
    }


    private void configLogicBinding() {
        btnPlant.disableProperty().bind(menuViewModel.isOnProperty().not());
        btnUnplant.disableProperty().bind(menuViewModel.isOnProperty().not());
        btnPlant.underlineProperty().bind(menuViewModel.isPlantproperty());
        btnUnplant.underlineProperty().bind(menuViewModel.isUnplantproperty());
        menuViewModel.isOnProperty().addListener((obs, oldval,newval) -> {
            btnSwitch.textProperty().bind(menuViewModel.startLabelProperty());
        });

    }


}

package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.BottomMenuViewModel;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;

class BottomMenuView extends HBox {

    private final FieldView fieldView;
    private final BottomMenuViewModel bottomMenuViewModel;
    private final Button btnSwitch = new Button();
    private final ToggleButton btnPlant = new ToggleButton();
    private final ToggleButton btnUnplant = new ToggleButton();

    BottomMenuView(BottomMenuViewModel bottomMenuViewModel, FieldView fieldView) {
        this.bottomMenuViewModel = bottomMenuViewModel;
        this.fieldView = fieldView;
        this.setPrefHeight(60);
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
        btnSwitch.textProperty().bind(bottomMenuViewModel.startLabelProperty());
        btnPlant.textProperty().bind(bottomMenuViewModel.plantLabelProperty());
        btnUnplant.textProperty().bind(bottomMenuViewModel.unplantLabelProperty());
    }

    private void manageBtn() {
        btnSwitch.setOnAction(e -> {
            bottomMenuViewModel.start();
            fieldView.requestFocus();
        });
        btnPlant.setOnAction(e -> {
            bottomMenuViewModel.setStatusPlant();
            fieldView.requestFocus();
        });
        btnUnplant.setOnAction(e -> {
            bottomMenuViewModel.setStatusUnplant();
            fieldView.requestFocus();
        });


    }

    private void configLogicBinding() {
        btnPlant.disableProperty().bind(bottomMenuViewModel.isOnProperty().not());
        btnUnplant.disableProperty().bind(bottomMenuViewModel.isOnProperty().not());
        bottomMenuViewModel.isOnProperty().addListener((obs, oldval, newval) -> {
            btnSwitch.textProperty().bind(bottomMenuViewModel.startLabelProperty());
        });
        bottomMenuViewModel.isOnProperty().addListener((obs, old, newVal) -> {
            btnPlant.setSelected(false);
            btnUnplant.setSelected(false);
        });

    }
}

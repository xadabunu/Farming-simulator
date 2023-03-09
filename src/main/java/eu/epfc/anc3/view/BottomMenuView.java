package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.BottomMenuViewModel;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

class BottomMenuView extends HBox {

    private final FieldView fieldView;
    private final BottomMenuViewModel bottomMenuViewModel;
    private final Button btnSwitch = new Button();
    private final Button btnSleep = new Button();

    BottomMenuView(BottomMenuViewModel bottomMenuViewModel, FieldView fieldView) {
        this.bottomMenuViewModel = bottomMenuViewModel;
        this.fieldView = fieldView;
        this.setPrefHeight(60);
        configButtons();
        manageBtn();
        configLogicBinding();
    }

    private void configButtons() {
        getChildren().addAll(btnSwitch, btnSleep);
        configLabel();
        setAlignment(Pos.CENTER);
        setSpacing(30);
    }

    private void configLabel() {
        btnSwitch.textProperty().bind(bottomMenuViewModel.startLabelProperty());
        btnSleep.textProperty().bind(bottomMenuViewModel.sleepLabelProperty());
    }

    private void manageBtn() {
        btnSwitch.setOnAction(e -> {
            bottomMenuViewModel.start();
            fieldView.requestFocus();
        });
        btnSleep.setOnAction(e -> {
            bottomMenuViewModel.sleep();
            fieldView.requestFocus();
        });
    }

    private void configLogicBinding() {
        bottomMenuViewModel.isOnProperty().addListener((obs, oldval, newval) -> {
            btnSwitch.textProperty().bind(bottomMenuViewModel.startLabelProperty());
        });
        btnSleep.disableProperty().bind(bottomMenuViewModel.isOnProperty().not());
    }
}

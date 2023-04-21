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
    private final Button btnRestore = new Button();
    private final Button btnSave = new Button();

    BottomMenuView(BottomMenuViewModel bottomMenuViewModel, FieldView fieldView) {
        this.bottomMenuViewModel = bottomMenuViewModel;
        this.fieldView = fieldView;
        this.setPrefHeight(60);
        configButtons();
        manageBtn();
        configLogicBinding();
    }

    private void configButtons() {
        getChildren().addAll(btnSwitch, btnSleep, btnSave, btnRestore);
        configLabel();
        setAlignment(Pos.CENTER);
        setSpacing(30);
    }

    private void configLabel() {
        btnSwitch.textProperty().bind(bottomMenuViewModel.startLabelProperty());
        btnSleep.textProperty().bind(bottomMenuViewModel.sleepLabelProperty());
        btnRestore.textProperty().bind(bottomMenuViewModel.restoreLabelProperty());
        btnSave.textProperty().bind(bottomMenuViewModel.saveLabelProperty());
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
        btnSave.setOnAction(e -> {
            bottomMenuViewModel.save();
            fieldView.requestFocus();
        });
        btnRestore.setOnAction(e -> {
            bottomMenuViewModel.restore();
            fieldView.requestFocus();
        });
    }

    private void configLogicBinding() {
        bottomMenuViewModel.isOnProperty().addListener((obs, oldval, newval) -> {
            btnSwitch.textProperty().bind(bottomMenuViewModel.startLabelProperty());
        });
        btnSleep.disableProperty().bind(bottomMenuViewModel.isOnProperty().not());
        btnSave.disableProperty().bind(bottomMenuViewModel.isOnProperty().not());
        btnRestore.disableProperty().bind(bottomMenuViewModel.hasSavedProperty().not().or(bottomMenuViewModel.isOnProperty().not()));
    }
}

package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.SideMenuViewModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;

public class SideMenuView extends VBox {
    private final FieldView fieldView;
    private final SideMenuViewModel sideMenuViewModel;
    private final ToggleButton btnGrass = new ToggleButton();
    private final ToggleButton btnCarrot = new ToggleButton();
    private final ToggleButton btnCabbage = new ToggleButton();
    private final ToggleButton btnFertilize = new ToggleButton();
    private final ToggleButton btnCollect = new ToggleButton();

    SideMenuView(SideMenuViewModel sideMenuViewModel, FieldView fieldView) {
        this.sideMenuViewModel = sideMenuViewModel;
        this.fieldView = fieldView;
        configButtons();
        manageBtn();
        configLogicBinding();
    }
    private void configButtons(){
        getChildren().addAll(btnGrass, btnCarrot, btnCabbage, btnFertilize, btnCollect);
        configToggle();
        configLabel();
        configSize();
        configMargins();
        setAlignment(Pos.CENTER);
        setSpacing(5);
    }
    private void configMargins(){
        setMargin(btnGrass, new Insets(0,0,0,16));
        setMargin(btnCarrot, new Insets(0,0,0,16));
        setMargin(btnCabbage, new Insets(0,0,0,16));
        setMargin(btnFertilize, new Insets(0,0,0,16));
        setMargin(btnCollect, new Insets(0,0,0,16));
    }
    private void configSize(){
        btnGrass.setPrefSize(150, 25);
        btnCarrot.setPrefSize(150,25);
        btnCabbage.setPrefSize(150,25);
        btnFertilize.setPrefSize(150,25);
        btnCollect.setPrefSize(150,25);
    }
    private void configLabel(){
        btnGrass.textProperty().bind(sideMenuViewModel.grassLabelProperty());
        btnCabbage.textProperty().bind(sideMenuViewModel.cabbageLabelProperty());
        btnCarrot.textProperty().bind(sideMenuViewModel.carrotLabelProperty());
        btnFertilize.textProperty().bind(sideMenuViewModel.fertilizeLabelProperty());
        btnCollect.textProperty().bind(sideMenuViewModel.collectLabelProperty());

        btnGrass.graphicProperty().bind(sideMenuViewModel.grassImageProperty());
        btnCabbage.graphicProperty().bind(sideMenuViewModel.cabbageImageProperty());
        btnCarrot.graphicProperty().bind(sideMenuViewModel.carrotImageProperty());
        btnFertilize.graphicProperty().bind(sideMenuViewModel.fertilizeImageProperty());
        btnCollect.graphicProperty().bind(sideMenuViewModel.collectImageProperty());
    }

    private void configToggle() {
        ToggleGroup group = new ToggleGroup();
        btnGrass.setToggleGroup(group);
        btnCollect.setToggleGroup(group);
        btnCarrot.setToggleGroup(group);
        btnCabbage.setToggleGroup(group);
        btnFertilize.setToggleGroup(group);
    }

    private void manageBtn(){
        btnGrass.setOnAction(e -> {
            sideMenuViewModel.setStatusPlantGrass();
            fieldView.requestFocus();
        });
        btnCarrot.setOnAction(e ->  {
            sideMenuViewModel.setStatusPlantCarrot();
            fieldView.requestFocus();
        });
        btnCabbage.setOnAction(e -> {
            sideMenuViewModel.setStatusPlantCabbage();
            fieldView.requestFocus();
        });
        btnFertilize.setOnAction(e -> {
            sideMenuViewModel.fertilize();
            fieldView.requestFocus();
        });
        btnCollect.setOnAction(e -> {
            sideMenuViewModel.setStatusUnplant();
            fieldView.requestFocus();
        });
    }

    private void configLogicBinding() {
        ToggleButton[] buttons = {btnGrass, btnCollect, btnCabbage, btnFertilize, btnCarrot};
        for (ToggleButton button : buttons) {
            button.disableProperty().bind(sideMenuViewModel.isOnProperty().not());
        }
    }
}

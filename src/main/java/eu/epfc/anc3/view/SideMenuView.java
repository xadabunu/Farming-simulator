package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.SideMenuViewModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class SideMenuView extends VBox {
    private final FieldView fieldView;
    private final SideMenuViewModel sideMenuViewModel;
    private final Button btnGazon = new Button();
    private final Button btnCarotte = new Button();
    private final Button btnChou = new Button();
    private final Button btnFertiliser = new Button();
    private final Button btnRecolter = new Button();
    private static final Image grassImage = new Image("grass.png");
    private static final Image carotteImage = new Image("carrot4.png");
    private static final Image chouImage = new Image("cabbage4.png");
    private static final Image fertiliserImage = new Image("watering_can.png");
    private static final Image recolterImage = new Image("shovel.png");
    private final ImageView grassImageView = new ImageView(grassImage);
    private final ImageView carotteImageView = new ImageView(carotteImage);
    private final ImageView cabbageImageView = new ImageView(chouImage);
    private final ImageView fertiliserImageView = new ImageView(fertiliserImage);
    private final ImageView recolterImageView = new ImageView(recolterImage);


    SideMenuView(SideMenuViewModel sideMenuViewModel, FieldView fieldView) {
        this.sideMenuViewModel = sideMenuViewModel;
        this.fieldView = fieldView;
        configButtons();
        manageBtn();
        configLogicBinding();
    }
    private void configButtons(){
        getChildren().addAll(btnGazon ,btnCarotte, btnChou, btnFertiliser, btnRecolter);
        configLabel();
        configSize();
        configMargins();
        setAlignment(Pos.CENTER);
        setSpacing(5);
    }
    private void configMargins(){
        setMargin(btnGazon, new Insets(0,0,0,16));
        setMargin(btnCarotte, new Insets(0,0,0,16));
        setMargin(btnChou, new Insets(0,0,0,16));
        setMargin(btnFertiliser, new Insets(0,0,0,16));
        setMargin(btnRecolter, new Insets(0,0,0,16));
    }
    private void configSize(){
        btnGazon.setPrefSize(150, 25);
        btnCarotte.setPrefSize(150,25);
        btnChou.setPrefSize(150,25);
        btnFertiliser.setPrefSize(150,25);
        btnRecolter.setPrefSize(150,25);
    }
    private void configLabel(){
        btnGazon.setGraphic(grassImageView);
        btnCarotte.setGraphic(carotteImageView);
        btnChou.setGraphic(cabbageImageView);
        btnFertiliser.setGraphic(fertiliserImageView);
        btnRecolter.setGraphic(recolterImageView);

        btnGazon.setText("Planter du gazon");
        btnCarotte.setText("Planter des carottes");
        btnChou.setText("Planter des choux");
        btnFertiliser.setText("Fertiliser");
        btnRecolter.setText("Récolter");
    }
    private void manageBtn(){

    }

    private void configLogicBinding(){

    }

}

package eu.epfc.anc3.view;

import eu.epfc.anc3.model.GameFacade;
import eu.epfc.anc3.vm.AppViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.concurrent.LinkedBlockingQueue;

public class AppView extends VBox {

    private final AppViewModel appViewModel = new AppViewModel();

    static final int MENU_HEIGHT = 30;
    static final int PADDING = 20;

    private static final int SCENE_MIN_WIDTH = 1000, SCENE_MIN_HEIGHT = 600;

    static final int FIELD_WIDTH = GameFacade.fieldCol();
    static final int FIELD_HEIGHT = GameFacade.fieldLines();
    private int grassCtr = 0;

    private final DoubleProperty fieldWidthProperty = new SimpleDoubleProperty(875);
    private final DoubleProperty fieldhHeightProperty = new SimpleDoubleProperty(525);

    private MenuView menuView;
    private FieldView fieldView;
    private CharacterView characterView;

    public AppView(Stage stage) {
        start(stage);
    }

    public void start(Stage stage) {
        configMainComponents(stage);
        Scene scene = new Scene(this, SCENE_MIN_WIDTH, SCENE_MIN_HEIGHT);
        stage.setScene(scene);
        stage.show();
        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
    }

    private void configMainComponents(Stage stage) {
        stage.titleProperty().bind(appViewModel.titleProperty());
        configCounter();
        configFieldView();
        configMenu();
        initiateCharacter();
    }

    private void configMenu() {
        menuView = new MenuView(appViewModel.getMenuViewModel());
        this.getChildren().add(menuView);
    }

    private void configCounter() {
        var pane = new HBox();
        Label labelCtr = new Label("Nombre de parcelles de gazon: ");
        TextField ctrTxt = new TextField();
        ctrTxt.setMaxWidth(30);
        ctrTxt.setDisable(true);
        ctrTxt.setText("0");
        pane.getChildren().addAll(labelCtr, ctrTxt);
        this.getChildren().add(pane);
        pane.setAlignment(Pos.CENTER);
    }

    private void configFieldView() {
        fieldView = new FieldView(appViewModel.getFieldViewModel(), fieldWidthProperty, fieldhHeightProperty);
        fieldView.minHeightProperty().bind(fieldhHeightProperty);
        fieldView.minWidthProperty().bind(fieldWidthProperty);
        fieldView.maxHeightProperty().bind(fieldhHeightProperty);
        fieldView.maxWidthProperty().bind(fieldWidthProperty);
        fieldWidthProperty.bind(widthProperty().subtract(PADDING*2));
        fieldhHeightProperty.bind(heightProperty().subtract(PADDING*2).subtract(MENU_HEIGHT));
        this.getChildren().add(fieldView);
        setAlignment(Pos.CENTER);
    }

    private void initiateCharacter() {
        characterView = new CharacterView("farmer.png");

        fieldView.add(new ImageView(characterView.getImage()), 0,0);
        }
}

package eu.epfc.anc3.view;

import eu.epfc.anc3.model.GameFacade;
import eu.epfc.anc3.vm.AppViewModel;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppView extends VBox {

    private AppViewModel appViewModel = new AppViewModel();

    static final int MENU_HEIGHT = 30;
    static final int MENU_WIDTH = 160;
    static final int PADDING = 20;

    private static final int SCENE_MIN_WIDTH = 1500, SCENE_MIN_HEIGHT = 1000;

    static final int FIELD_WIDTH = GameFacade.fieldCol();
    static final int FIELD_HEIGHT = GameFacade.fieldLines();
    private int grassCtr = 0;

    private final DoubleProperty fieldWidthProperty = new SimpleDoubleProperty(0);
    //private final DoubleProperty fieldWidthProperty = new SimpleDoubleProperty(150);


    private MenuView menuView;
    private FieldView fieldView;

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
    }

    private void configMenu() {
        menuView = new MenuView(appViewModel.getMenuViewModel());
        this.getChildren().add(menuView);
        setAlignment(Pos.BOTTOM_CENTER);
        getChildren().add(menuView);
    }

    private void configCounter() {
        var pane = new HBox();
        Label labelCtr = new Label("Nombre de parcelles de gazon: ");
        TextField ctrTxt = new TextField();
        ctrTxt.setMaxWidth(30);
        ctrTxt.setDisable(true);
        ctrTxt.setText("0");
        pane.setAlignment(Pos.CENTER);
        pane.getChildren().addAll(labelCtr, ctrTxt);
        getChildren().addAll(pane);

    }

    private void configFieldView() {
        fieldView = new FieldView(appViewModel.getFieldViewModel(), fieldWidthProperty);
        fieldView.minHeightProperty().bind(fieldWidthProperty);
        fieldView.minWidthProperty().bind(fieldWidthProperty);
        fieldView.maxHeightProperty().bind(fieldWidthProperty);
        fieldView.maxWidthProperty().bind(fieldWidthProperty);
        fieldWidthProperty.bind(Bindings.min(widthProperty().subtract(2 * PADDING), heightProperty().subtract(MENU_HEIGHT + 2 * PADDING)));

        this.getChildren().add(fieldView);
        setAlignment(Pos.CENTER);

    }
}

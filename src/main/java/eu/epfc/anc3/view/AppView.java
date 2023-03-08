package eu.epfc.anc3.view;

import eu.epfc.anc3.model.Direction;
import eu.epfc.anc3.model.GameFacade;
import eu.epfc.anc3.vm.AppViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppView extends VBox {

    private final AppViewModel appViewModel = new AppViewModel();
    private static final int MENU_HEIGHT = 80;
    private static final int SIDE_MENU_WIDTH = 145;
    static final int PADDING = 20;
    private static final int SCENE_MIN_WIDTH = 1400, SCENE_MIN_HEIGHT = 800;
    static final int FIELD_WIDTH = GameFacade.fieldCol();
    static final int FIELD_HEIGHT = GameFacade.fieldLines();
    private final DoubleProperty fieldWidthProperty = new SimpleDoubleProperty(500);
    private final DoubleProperty fieldhHeightProperty = new SimpleDoubleProperty(300);
    private FieldView fieldView;
    private HBox fieldAndMenus;
    private SideMenuView sideMenuView;
    private boolean spacePressed = false;

    public AppView(Stage stage) {
        start(stage);
        keyboardManager();
    }

    private void start(Stage stage) {
        configMainComponents(stage);
        Scene scene = new Scene(this, SCENE_MIN_WIDTH, SCENE_MIN_HEIGHT);
        stage.setScene(scene);
        stage.show();
        stage.setMinHeight(stage.getHeight());
        stage.setMinWidth(stage.getWidth());
        stage.setMaxHeight(SCENE_MIN_HEIGHT);
        stage.setMaxWidth(SCENE_MIN_WIDTH);
    }

    private void configMainComponents(Stage stage) {
        stage.titleProperty().bind(appViewModel.titleProperty());
        fieldAndMenus = new HBox();
        configCounter();
        configFieldView();
        configSideMenu();
        configBottomMenu();
    }
    private void configSideMenu(){
        sideMenuView = new SideMenuView(appViewModel.getSideMenuViewModel(), fieldView);
        fieldAndMenus.getChildren().add(sideMenuView);
    }
    private void configBottomMenu() {
        BottomMenuView bottomMenuView = new BottomMenuView(appViewModel.getBottomMenuViewModel(), fieldView);
        this.getChildren().add(bottomMenuView);
    }

    private void configCounter() {
        CounterView counterView = new CounterView(appViewModel.getCounterViewModel());
        this.getChildren().add(counterView);
    }

    private void configFieldView() {
        fieldView = new FieldView(appViewModel.getFieldViewModel(), fieldWidthProperty);
        fieldView.minHeightProperty().bind(fieldhHeightProperty);
        fieldView.minWidthProperty().bind(fieldWidthProperty);
        fieldView.maxHeightProperty().bind(fieldhHeightProperty);
        fieldView.maxWidthProperty().bind(fieldWidthProperty);
        fieldWidthProperty.bind(widthProperty().subtract(PADDING*2).subtract(SIDE_MENU_WIDTH));
        fieldhHeightProperty.bind(heightProperty().subtract(PADDING*2).subtract(MENU_HEIGHT));
        fieldAndMenus.getChildren().add(fieldView);
        this.getChildren().add(fieldAndMenus);
        setAlignment(Pos.CENTER);
    }
    private void keyboardManager() {
        setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case SPACE -> {
                    appViewModel.plantUnplant();
                    spacePressed = true;
                }
                case A, Q, LEFT -> {
                    appViewModel.move(Direction.LEFT);
                    if (spacePressed) appViewModel.plantUnplant();
                }
                case S, DOWN -> {
                    appViewModel.move(Direction.DOWN);
                    if (spacePressed) appViewModel.plantUnplant();
                }
                case D, RIGHT -> {
                    appViewModel.move(Direction.RIGHT);
                    if (spacePressed) appViewModel.plantUnplant();
                }
                case W, Z, UP -> {
                    appViewModel.move(Direction.UP);
                    if (spacePressed) appViewModel.plantUnplant();
                }
            }
            e.consume();
        });
        setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.SPACE)
                spacePressed = false;
        });
    }
}

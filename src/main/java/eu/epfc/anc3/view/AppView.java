package eu.epfc.anc3.view;

import eu.epfc.anc3.model.GameFacade;
import eu.epfc.anc3.vm.AppViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AppView extends BorderPane {

    private final AppViewModel appViewModel = new AppViewModel();

    static final int MENU_HEIGHT = 30;
    static final int PADDING = 20;
    private static final int SCENE_MIN_WIDTH = 600, SCENE_MIN_HEIGHT = 400;
    static final int FIELD_WIDTH = GameFacade.fieldCol();
    static final int FIELD_HEIGHT = GameFacade.fieldLines();

    private final DoubleProperty fieldColProperty = new SimpleDoubleProperty(250);
    private final DoubleProperty fieldLinesProperty = new SimpleDoubleProperty(150);

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

        configMenu();
        configFieldView();
    }

    private void configMenu() {
        menuView = new MenuView(appViewModel.getMenuViewModel());
        setBottom(menuView);
    }

    private void configFieldView() {
        fieldView = new FieldView(appViewModel.getFieldViewModel(), fieldLinesProperty, fieldColProperty);

        fieldView.minHeightProperty().bind(fieldLinesProperty);
        fieldView.minWidthProperty().bind(fieldColProperty);
        fieldView.maxHeightProperty().bind(fieldLinesProperty);
        fieldView.maxWidthProperty().bind(fieldColProperty);
        setTop(fieldView);
    }
}

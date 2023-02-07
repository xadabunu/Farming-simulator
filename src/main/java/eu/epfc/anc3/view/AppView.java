package eu.epfc.anc3.view;

import eu.epfc.anc3.model.Field;
import eu.epfc.anc3.vm.AppViewModel;
import javafx.stage.Stage;

public class AppView {

    private final AppViewModel appViewModel = new AppViewModel();

    static final int MENU_HEIGHT = 20;
    private static final int SCENE_WIDTH = 600, SCENE_HEIGHT = 400;

    private MenuView menuView;

    public AppView(Stage stage) {
        start(stage);
    }

    public void start(Stage stage) {
        configMainComponents(stage);
    }

    private void configMainComponents(Stage stage) {
        stage.titleProperty().bind(appViewModel.titleProperty());

        configMenu();
    }

    private void configMenu() {
        menuView = new MenuView(appViewModel.getMenuViewModel());
//        setBottom(menuView);
    }
}

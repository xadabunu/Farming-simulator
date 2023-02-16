//ANC3 2223 a05
package eu.epfc.anc3.app;

import eu.epfc.anc3.view.AppView;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application  {

    @Override
    public void start(Stage stage) throws Exception {
        new AppView(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}

package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.CounterViewModel;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

import java.text.NumberFormat;

class CounterView extends HBox {

    private final CounterViewModel counterViewModel;
    private final Label labelScore = new Label();
    private final TextField textScore = new TextField();
    private final Label labelDays = new Label();
    private final TextField textDays = new TextField();

    CounterView(CounterViewModel counterViewModel) {
        this.counterViewModel = counterViewModel;
        configCounter();
    }

    private void configCounter() {
        HBox scoreBox = new HBox();
        HBox daysBox = new HBox();

        setHgrow(scoreBox, Priority.ALWAYS);
        setHgrow(daysBox, Priority.ALWAYS);

        setMargin(scoreBox, new Insets(0, 0, 0, 500));
        setMargin(daysBox, new Insets(0, 0, 0, 400));

        setMargin(labelScore, new Insets(3,5,0,0));
        setMargin(labelDays, new Insets(3,5,0,0));

        scoreBox.getChildren().addAll(labelScore, textScore);
        daysBox.getChildren().addAll(labelDays, textDays);

        getChildren().addAll(scoreBox,daysBox);

        configLabel();

        textScore.setPrefWidth(50);
        textScore.setMaxWidth(50);
        textScore.setEditable(false);
        textScore.setDisable(true);

        textDays.setPrefWidth(50);
        textDays.setMaxWidth(50);
        textDays.setEditable(false);
        textDays.setDisable(true);
    }

    private void configLabel() {
        labelScore.textProperty().bind(counterViewModel.labelScoreProperty());
        textScore.textProperty().bindBidirectional(counterViewModel.counterScoreProperty(), NumberFormat.getIntegerInstance());

        labelDays.textProperty().bind(counterViewModel.labelDaysProperty());
        textDays.textProperty().bindBidirectional(counterViewModel.counterDaysProperty(), NumberFormat.getIntegerInstance());
    }
}

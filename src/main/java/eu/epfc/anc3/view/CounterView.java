package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.CounterViewModel;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

public class CounterView extends HBox {

    private final CounterViewModel counterViewModel;
    private final Label labelCtr = new Label();
    private final TextField ctrTxt = new TextField();


    public CounterView(CounterViewModel counterViewModel) {
        this.counterViewModel = counterViewModel;
        configCounter();

    }

    private void configCounter() {
        getChildren().addAll(labelCtr, ctrTxt);
        configLabel();
        ctrTxt.setMaxWidth(30);
        ctrTxt.setDisable(true);
        ctrTxt.setText("0");
    }

    private void configLabel() {
        labelCtr.textProperty().bind(counterViewModel.counterLabelProperty());
    }
}

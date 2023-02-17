package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.AppViewModel;
import eu.epfc.anc3.vm.CounterViewModel;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

import java.text.NumberFormat;

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
        ctrTxt.setEditable(false);
    }

    private void configLabel() {
        labelCtr.textProperty().bind(counterViewModel.counterLabelProperty());
        ctrTxt.textProperty().bindBidirectional(counterViewModel.counterProperty(), NumberFormat.getIntegerInstance());
//        counterViewModel.counterProperty().addListener((obs, oldval, newval)-> {
//            ctrTxt.textProperty().bindBidirectional(counterViewModel.counterProperty(), NumberFormat.getIntegerInstance());
//        });
    }



}

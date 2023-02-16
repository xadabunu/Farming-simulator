package eu.epfc.anc3.vm;

import eu.epfc.anc3.model.GameFacade;
import eu.epfc.anc3.model.GameStatus;
import javafx.beans.property.*;

public class CounterViewModel {

    private final GameFacade game;

    public CounterViewModel(GameFacade game) {
        this.game = game;
    }

    public ReadOnlyStringProperty counterLabelProperty() {
        return new SimpleStringProperty("Nombre de parcelles de gazons");
    }

    public StringProperty counterProperty() {
        return new SimpleStringProperty(String.valueOf(game.ctrProperty()));
    }

//    public void counterManager() {
//        if (game.plantUnplant()){
//            if(game.gameStatusProperty().isEqualTo(GameStatus.PLANT).get()){
//                ctr.add(1);
//            }
//            else {
//                ctr.subtract(1);
//            }
//        }
//    }


}

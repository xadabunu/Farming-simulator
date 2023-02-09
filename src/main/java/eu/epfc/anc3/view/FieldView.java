package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.FieldViewModel;
import javafx.beans.property.DoubleProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class FieldView extends GridPane {

    private static final Image dirtImage = new Image("dirt.png");
    private static final Image grassImage = new Image("grass.png");

    private final ImageView imageView = new ImageView();

    public FieldView(FieldViewModel fieldViewModel, DoubleProperty fieldLinesProperty, DoubleProperty fieldColProperty) {
        imageView.setPreserveRatio(true);
        //imageView.fitWidthProperty().bind();
    }

}

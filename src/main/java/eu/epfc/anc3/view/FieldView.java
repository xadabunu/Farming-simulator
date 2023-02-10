package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.FieldViewModel;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import static eu.epfc.anc3.view.AppView.*;

public class FieldView extends GridPane {

    public FieldView(FieldViewModel fieldViewModel, DoubleProperty fieldWidthProperty) {
        //setGridLinesVisible(true);
        setPadding(new Insets(PADDING));
//        RowConstraints rowConstraints = new RowConstraints();
//        rowConstraints.setPercentHeight(100.0 / FIELD_HEIGHT);
//        ColumnConstraints columnConstraints = new ColumnConstraints();
//        columnConstraints.setPercentWidth(100.0 / FIELD_WIDTH);
        DoubleBinding landWidthProperty = fieldWidthProperty.subtract(PADDING * 2).divide(FIELD_HEIGHT);
        //DoubleBinding landColProperty = fieldColProperty.subtract(PADDING *2).divide(FIELD_WIDTH);

//        for (int i = 0; i < FIELD_WIDTH; ++i) {
//            getColumnConstraints().add(columnConstraints);
//        }
//        for(int i = 0; i < FIELD_HEIGHT; ++i) {
//            getRowConstraints().add(rowConstraints);
//        }

        // Remplissage de la grille
        for (int i = 0; i < FIELD_WIDTH; ++i) {
            for (int j = 0; j < FIELD_HEIGHT; ++j) {
                LandView caseView = new LandView(fieldViewModel.getLandViewModel(j, i), landWidthProperty);
                add(caseView, i, j); // lignes/colonnes inversées dans gridpane
            }
        }

        //imageView.fitWidthProperty().bind();
    }

}

package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.FieldViewModel;
import eu.epfc.anc3.vm.LandViewModel;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import static eu.epfc.anc3.view.AppView.*;

public class FieldView extends GridPane {

    private final FieldViewModel fieldViewModel;

    public FieldView(FieldViewModel fieldViewModel, DoubleProperty fieldWidthProperty, DoubleProperty fieldHeightProperty) {

        this.fieldViewModel = fieldViewModel;

        setPadding(new Insets(PADDING));
//        this.prefWidthProperty().bind(fieldWidthProperty);
//        this.prefHeightProperty().bind(fieldHeightProperty);
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(100.0 / FIELD_HEIGHT);
        ColumnConstraints columnConstraints = new ColumnConstraints();
        columnConstraints.setPercentWidth(100.0 / FIELD_WIDTH);
        DoubleBinding landWidthProperty = fieldWidthProperty.divide(FIELD_WIDTH);

        for(int i = 0; i < FIELD_WIDTH; ++i) {
            getColumnConstraints().add(columnConstraints);
        }

        for(int i = 0; i < FIELD_HEIGHT; ++i) {
            getRowConstraints().add(rowConstraints);
        }

        // Remplissage de la grille
        for (int i = 0; i < FIELD_HEIGHT; ++i) {
            for (int j = 0; j < FIELD_WIDTH; ++j) {
                LandView caseView = new LandView(fieldViewModel.getLandViewModel(i,j), landWidthProperty);
                add(caseView, j, i); // lignes/colonnes inversées dans gridpane
            }
        }
    }

    public LandViewModel getLandViewModel(int line, int col) {
        return fieldViewModel.getLandViewModel(line, col);
    }

}

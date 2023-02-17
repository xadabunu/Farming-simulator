package eu.epfc.anc3.view;

import eu.epfc.anc3.model.Land;
import eu.epfc.anc3.model.Position;
import eu.epfc.anc3.vm.FieldViewModel;
import eu.epfc.anc3.vm.LandViewModel;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

import static eu.epfc.anc3.view.AppView.*;

public class FieldView extends GridPane {

    private final FieldViewModel fieldViewModel;
    private final ObjectProperty<Position> characterPosition = new SimpleObjectProperty<>(new Position(0, 0));
    //private final ObjectProperty<Land> charPos;

    public FieldView(FieldViewModel fieldViewModel, DoubleProperty fieldWidthProperty) {

        this.fieldViewModel = fieldViewModel;

        setPadding(new Insets(PADDING));
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
        initiateCharacter();
    }

    public LandViewModel getLandViewModel(int line, int col) {
        return fieldViewModel.getLandViewModel(line, col);
    }

    private void initiateCharacter() {
        ImageView imageView = new ImageView(new CharacterView("farmer.png").getImage());
        add(imageView, characterPosition.get().getCol(),characterPosition.get().getLine());
        characterPosition.bind(fieldViewModel.characterPositionProperty());
        fieldViewModel.characterPositionProperty().addListener((obs, oldVal, newVal) -> {
            getChildren().remove(imageView);
            add(imageView, newVal.getCol(), newVal.getLine());
        });

    }
}

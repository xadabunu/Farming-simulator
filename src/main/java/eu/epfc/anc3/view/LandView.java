package eu.epfc.anc3.view;

import eu.epfc.anc3.model.LandContent;
import eu.epfc.anc3.vm.LandViewModel;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import static eu.epfc.anc3.view.AppView.PADDING;

public class LandView extends StackPane {

    private static final Image grassImage = new Image("grass.png");
    private static final Image dirtImage = new Image("dirt.png");

    private final ImageView imageView = new ImageView();

    public LandView(LandViewModel landViewModel, DoubleBinding landWidthProperty) {
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(landWidthProperty);
        getChildren().add(imageView);
        ReadOnlyObjectProperty<LandContent> valueProp = landViewModel.valueProperty();
        valueProp.addListener((obs, old, newVal) -> setLandImage(imageView, newVal));
        this.setOnMouseClicked(e -> landViewModel.teleport());
        setLandImage(imageView, LandContent.DIRT);
    }

    private void setLandImage(ImageView imageView, LandContent landContent) {
        imageView.setImage(landContent == LandContent.GRASS ? grassImage : dirtImage);
    }



}

package eu.epfc.anc3.view;

import eu.epfc.anc3.model.LandContent;
import eu.epfc.anc3.vm.LandViewModel;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


public class LandView extends StackPane {

    private static final Image grassImage = new Image("grass.png");
    private static final Image dirtImage = new Image("dirt.png");
    private static final Image carrot1Image = new Image("carrot1.png");
    private static final Image cabbage1Image = new Image("cabbage1.png");
    private final ImageView imageView = new ImageView();
    ReadOnlyObjectProperty<LandContent> landContentProperty = new SimpleObjectProperty<>(LandContent.DIRT);

    LandView(LandViewModel landViewModel, DoubleBinding landWidthProperty) {
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(landWidthProperty);
        getChildren().add(imageView);
        setLandImage(imageView, landContentProperty.get());
        ReadOnlyObjectProperty<LandContent> contentProperty = landViewModel.contentProperty();
        contentProperty.addListener((obs, old, newVal) -> {
            setLandImage(imageView, landViewModel.contentProperty().get());
        });
        setOnMouseClicked(e -> landViewModel.teleport());
    }

    private void setLandImage(ImageView imageView, LandContent landContent) {
        switch (landContent) {
            case GRASS -> imageView.setImage(grassImage);
            case DIRT -> imageView.setImage(dirtImage);
            case CARROT -> imageView.setImage(carrot1Image);
            case CABBAGE -> imageView.setImage(cabbage1Image);
        }
    }
}

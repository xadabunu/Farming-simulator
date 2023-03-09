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
    private static final Image carrot1Image = new Image("carrot.png");
    private static final Image gabbage1Image = new Image("gabbage.png");
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
        setOnMouseClicked(e -> {
            landViewModel.teleport();
        });
    }

    private void setLandImage(ImageView imageView, LandContent landContent) {
        if(landContent == LandContent.GRASS) {
            imageView.setImage(grassImage);
        }
        else if(landContent == LandContent.DIRT) {
            imageView.setImage((dirtImage));
        }
        else if(landContent == LandContent.CARROT) {
            imageView.setImage((carrot1Image));
        }
        else {
            imageView.setImage(gabbage1Image);
        }
    }

    private void setLandImage(ImageView imageView, LandContent landContent) {
        switch (landContent) {
            case GRASS:
                imageView.setImage(grassImage);
                break;
            case DIRT:
                imageView.setImage(dirtImage);
                break;
            case CARROT:
                imageView.setImage(carrot1Image);
                break;
            case GABBAGE :
                imageView.setImage(gabbage1Image);
                break;
        }
    }

}

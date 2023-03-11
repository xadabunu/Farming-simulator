package eu.epfc.anc3.view;

import eu.epfc.anc3.model.LandContent;
import eu.epfc.anc3.model.LandGrowable;
import eu.epfc.anc3.vm.LandViewModel;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ObjectProperty;
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
    private ImageView growableImageView = new ImageView();
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
        ReadOnlyObjectProperty<LandGrowable> growableProperty = landViewModel.growableProperty();
        growableProperty.addListener((obs, old, newVal) -> {
            getChildren().remove(growableImageView);
            setGrowableImage(landViewModel.growableProperty().get(), landWidthProperty.get());
        });
        setOnMouseClicked(e -> landViewModel.teleport());
    }

    private void setLandImage(ImageView imageView, LandContent landContent) {
        switch (landContent) {
            case GRASS -> imageView.setImage(grassImage);
            case DIRT -> imageView.setImage(dirtImage);
        }
    }

    private void setGrowableImage(LandGrowable landGrowable, double landWidth) {
        if (landGrowable != null) {
            growableImageView = switch (landGrowable) {
                case CABBAGE -> new ImageView(cabbage1Image);
                case CARROT -> new ImageView(carrot1Image);
            };

            growableImageView = scaleImage(growableImageView.getImage(), landWidth);
            getChildren().add(growableImageView);
        }
    }


    private ImageView scaleImage(Image image, double landWidth) {
        double imageWidth = image.getWidth();
        double imageHeight = image.getHeight();

        double scaleFactor = landWidth / imageWidth;
        double scaledHeight = imageHeight * scaleFactor;

        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(landWidth/1.5);
        imageView.setFitHeight(scaledHeight/1.5);
        imageView.setPreserveRatio(true);

        return imageView;
    }

}

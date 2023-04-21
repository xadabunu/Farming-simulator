package eu.epfc.anc3.view;

import eu.epfc.anc3.model.GrowingState;
import eu.epfc.anc3.model.LandContent;
import eu.epfc.anc3.model.LandGrowable;
import eu.epfc.anc3.vm.LandViewModel;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;


public class LandView extends StackPane {
    
    private static final Image grassImage = new Image("grass.png");
    private static final Image dirtImage = new Image("dirt.png");
    private static final Image carrot1Image = new Image("carrot1.png");
    private static final Image carrot2Image = new Image("carrot2.png");
    private static final Image carrot3Image = new Image("carrot3.png");
    private static final Image carrot4Image = new Image("carrot4.png");
    private static final Image carrot5Image = new Image("rotten_carrot.png");

    private static final Image cabbage1Image = new Image("cabbage1.png");
    private static final Image cabbage2Image = new Image("cabbage2.png");
    private static final Image cabbage3Image = new Image("cabbage3.png");
    private static final Image cabbage4Image = new Image("cabbage4.png");
    private static final Image cabbage5Image = new Image("rotten_cabbage.png");

    private final ImageView imageView = new ImageView();
    private ImageView growableImageView = new ImageView();
    ReadOnlyObjectProperty<LandContent> landContentProperty = new SimpleObjectProperty<>(LandContent.DIRT);

    LandView(LandViewModel landViewModel, DoubleBinding landWidthProperty) {
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(landWidthProperty);
        getChildren().add(imageView);
        setLandImage(imageView, landContentProperty.get());
        landViewModel.contentProperty().addListener((obs, old, newVal) -> setLandImage(imageView, landViewModel.contentProperty().get()));
        landViewModel.growableProperty().addListener((obs, old, newVal) -> {
            getChildren().remove(growableImageView);
            setGrowableImage(landViewModel.growableProperty().get(), landWidthProperty.get(), landViewModel, landWidthProperty);
        });
        setOnMouseClicked(e -> landViewModel.teleport());
    }

    private void setLandImage(ImageView imageView, LandContent landContent) {
        switch (landContent) {
            case GRASS -> imageView.setImage(grassImage);
            case DIRT -> imageView.setImage(dirtImage);
        }
    }

    private void setGrowableStateImage(LandGrowable landGrowable, GrowingState growablestate, double landWidth){
        if (growablestate != null && landGrowable !=null) {
            switch (landGrowable){
                case CABBAGE -> {
                    growableImageView = switch (growablestate) {
                        case STATE_1 -> new ImageView(cabbage1Image);
                        case STATE_2 -> new ImageView(cabbage2Image);
                        case STATE_3 -> new ImageView(cabbage3Image);
                        case STATE_4 -> new ImageView(cabbage4Image);
                        case ROTTEN -> new ImageView(cabbage5Image);
                    };
                }
                case CARROT -> {
                    growableImageView = switch (growablestate) {
                        case STATE_1 -> new ImageView(carrot1Image);
                        case STATE_2 -> new ImageView(carrot2Image);
                        case STATE_3 -> new ImageView(carrot3Image);
                        case STATE_4 -> new ImageView(carrot4Image);
                        case ROTTEN -> new ImageView(carrot5Image);
                    };
                }
            }
            growableImageView = scaleImage(growableImageView.getImage(), landWidth);
            getChildren().add(growableImageView);
        }
    }



    private void setGrowableImage(LandGrowable landGrowable, double landWidth, LandViewModel landViewModel, DoubleBinding landWidthProperty) {
        ReadOnlyObjectProperty<GrowingState> growableState = landViewModel.growableState();
        if (landGrowable != null) {
            growableImageView = switch (landGrowable) {
                case CABBAGE -> new ImageView(cabbage1Image);
                case CARROT -> new ImageView(carrot1Image);
            };



            if (growableState != null) {
                switch (landGrowable) {
                    case CABBAGE -> {
                        growableImageView = switch (growableState.get()) {
                            case STATE_1 -> new ImageView(cabbage1Image);
                            case STATE_2 -> new ImageView(cabbage2Image);
                            case STATE_3 -> new ImageView(cabbage3Image);
                            case STATE_4 -> new ImageView(cabbage4Image);
                            case ROTTEN -> new ImageView(cabbage5Image);
                        };
                    }
                    case CARROT -> {
                        growableImageView = switch (growableState.get()) {
                            case STATE_1 -> new ImageView(carrot1Image);
                            case STATE_2 -> new ImageView(carrot2Image);
                            case STATE_3 -> new ImageView(carrot3Image);
                            case STATE_4 -> new ImageView(carrot4Image);
                            case ROTTEN -> new ImageView(carrot5Image);
                        };
                    }
                }
                growableImageView = scaleImage(growableImageView.getImage(), landWidth);
                getChildren().add(growableImageView);
                growableImageView.setTranslateY(-8);

                growableState.addListener((obs, old, newVal) -> {
                    getChildren().remove(growableImageView);
                    setGrowableStateImage(landGrowable, newVal, landWidthProperty.get());
                });
            }
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

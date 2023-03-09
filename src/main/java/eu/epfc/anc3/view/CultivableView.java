package eu.epfc.anc3.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public abstract class CultivableView {

    private  Image image;

    public CultivableView (String imagePath) {
        image = new Image(imagePath);
        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
    }

    Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}

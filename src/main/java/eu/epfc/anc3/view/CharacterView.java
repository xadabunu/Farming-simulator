package eu.epfc.anc3.view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CharacterView {

    private final Image image;

    public CharacterView (String imagePath) {
        image = new Image(imagePath);
        ImageView imageView = new ImageView();
        imageView.setPreserveRatio(true);
    }

    Image getImage() {
        return image;
    }

}

package eu.epfc.anc3.view;

import eu.epfc.anc3.vm.CharacterViewModel;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CharacterView {

    private static Image image;
    private final ImageView imageView = new ImageView();
    private final CharacterViewModel characterViewModel = new CharacterViewModel();

    public CharacterView (String imagePath) {
        image = new Image(imagePath);
        imageView.setPreserveRatio(true);
        //imageView.fitWidthProperty().bind();
    }

    public Image getImage() {
        return image;
    }

}

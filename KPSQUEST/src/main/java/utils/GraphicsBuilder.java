package utils;

import javafx.scene.image.ImageView;

public class GraphicsBuilder {
    public static void shrinkByAmount(ImageView image, double d) {
        image.setScaleX(d);
        image.setScaleY(d);
    }
}

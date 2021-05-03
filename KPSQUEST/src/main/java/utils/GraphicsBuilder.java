package utils;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class GraphicsBuilder {
    public static void shrinkByAmount(ImageView image, double d) {
        image.setScaleX(d);
        image.setScaleY(d);
    }
    
    public static WritableImage getImageWithoutBlue(Image image) {
        WritableImage wi = new WritableImage((int)image.getWidth(), (int)image.getHeight());
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = wi.getPixelWriter();
        
        //System.out.println(Color.BLUE.toString() + "   " + reader.getColor(0, 0).toString());
        
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if (!(reader.getColor(x, y).toString().equals(Color.BLUE.toString()))) {
                    writer.setColor(x, y, reader.getColor(x, y));
                }
            }
        }
        return wi;
    }
    
    public static ImageView getGraphicsObject(String filename, double translateX, double translateY, double sizemod, boolean bluscreen) {
        Image image = new Image(utils.Utils.getFilePath(filename));
        ImageView graphics;
        
        if (bluscreen) {
             graphics = new ImageView(getImageWithoutBlue(image));
        } else {
            graphics = new ImageView(image);
        }
        
        graphics.setTranslateX(translateX);
        graphics.setTranslateY(translateY);
        
        shrinkByAmount(graphics, sizemod);
        
        return graphics;
    }
}



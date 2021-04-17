package UserUIs;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class OverworldUI {
    
    public static void setOverWorldScene(Stage s, String name) {
        Pane pane2 = new Pane();
        pane2.setMinSize(500, 300);
        pane2.setMaxSize(700, 400);
        
        Image bg = new Image("file:tausta_test.jpg");
        ImageView background = new ImageView(bg);
        //background.setScaleX(0.5);
        //background.setScaleX(0.5);
        background.setTranslateX(-900);
        
        Image tr = new Image("file:kuusi_bluscreen.png");
        ImageView tree = new ImageView(getImageWithoutBlue(tr));
        tree.setScaleX(0.5);
        tree.setScaleY(0.5);
        
        pane2.getChildren().add(background);
        pane2.getChildren().add(tree);
        pane2.getChildren().get(1).toFront();
        
        
        
        Scene ovscene = new Scene(pane2);
        
        ovscene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) {
                        tree.setTranslateX(tree.getTranslateX() - 5);
                    }
                
                if (event.getCode() == KeyCode.RIGHT) {
                    tree.setTranslateX(tree.getTranslateX() + 5);
                }
                
                if (event.getCode() == KeyCode.DOWN) {
                        tree.setTranslateY(tree.getTranslateY() + 5);
                    }
                
                if (event.getCode() == KeyCode.UP) {
                    tree.setTranslateY(tree.getTranslateY() - 5);
                }
            }
        });
        s.setScene(ovscene);
    }
    
    public static WritableImage getImageWithoutBlue(Image image) {
        WritableImage wi = new WritableImage((int)image.getWidth(), (int)image.getHeight());
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = wi.getPixelWriter();
        
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if (!(reader.getColor(x, y).toString().equals(Color.BLUE.toString()))) {
                    writer.setColor(x, y, reader.getColor(x, y));
                }
            }
        }
        return wi;
    }
}

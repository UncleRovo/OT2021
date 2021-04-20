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
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class OverworldUI {
    
    public static void setOverWorldScene(Stage s, String name) {
        Pane pane2 = new Pane();
        pane2.setMinSize(500, 300);
        pane2.setMaxSize(700, 400);
        
        Image bg = new Image(Utils.Utils.getFilePath("file:tausta_test.jpg"));
        ImageView background = new ImageView(bg);
        //background.setScaleX(0.5);
        //background.setScaleX(0.5);
        background.setTranslateX(-900);
        
        Image pl = new Image(Utils.Utils.getFilePath("file:protagonist.png"));
        ImageView player = new ImageView(getImageWithoutBlue(pl));
        
        Image h_b = new Image(Utils.Utils.getFilePath("file:talo_ala.png"));
        ImageView house1_bottom = new ImageView(getImageWithoutBlue(h_b));
        house1_bottom.setTranslateY(700);
        house1_bottom.setTranslateX(250);
        
        Image h_t = new Image(Utils.Utils.getFilePath("file:talo_yla.png"));
        ImageView house1_top = new ImageView(getImageWithoutBlue(h_t));
        house1_top.setTranslateX(house1_bottom.getTranslateX() - 9);
        house1_top.setTranslateY(house1_bottom.getTranslateY() - 122);
        
        Polygon hitbox = new Polygon(0, 0, 255, 0, 255, 20, 0, 20);
        hitbox.setVisible(false);
        hitbox.setTranslateX(house1_bottom.getTranslateX() + 140);
        hitbox.setTranslateY(house1_bottom.getTranslateY() + 34);
        
        shrinkByAmount(player, 0.5);
        shrinkByAmount(house1_bottom, 0.5);
        shrinkByAmount(house1_top, 0.5);
        
        pane2.getChildren().add(background);
        
        pane2.getChildren().add(house1_bottom);
        pane2.getChildren().add(player);
        pane2.getChildren().add(house1_top);
        pane2.getChildren().add(hitbox);
        
        Scene ovscene = new Scene(pane2);
        
        ovscene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            KeyCode latest = null;
            @Override
            public void handle(KeyEvent event) {
                if (!(player.getBoundsInParent().intersects(hitbox.getBoundsInParent()))) {
                    if (event.getCode() == KeyCode.LEFT) {
                        player.setTranslateX(player.getTranslateX() - 10);
                        latest = KeyCode.LEFT;
                    }
                
                    if (event.getCode() == KeyCode.RIGHT) {
                        player.setTranslateX(player.getTranslateX() + 10);
                        latest = KeyCode.RIGHT;
                    }
                
                    if (event.getCode() == KeyCode.DOWN) {
                        player.setTranslateY(player.getTranslateY() + 10);
                        latest = KeyCode.DOWN;
                    }
                
                    if (event.getCode() == KeyCode.UP) {
                        player.setTranslateY(player.getTranslateY() - 10);
                        latest = KeyCode.UP;
                    }
                } else {
                    if (latest == KeyCode.LEFT) {
                        player.setTranslateX(player.getTranslateX() + 1);
                    }
                
                    if (latest == KeyCode.RIGHT) {
                        player.setTranslateX(player.getTranslateX() - 1);
                    }
                
                    if (latest == KeyCode.DOWN) {
                        player.setTranslateY(player.getTranslateY() - 1);
                    }
                
                    if (latest == KeyCode.UP) {
                        player.setTranslateY(player.getTranslateY() + 1);
                    }
                }
            }
        });
        s.setScene(ovscene);
    }
    
    public static boolean collision(ImageView player, Polygon hitbox) {
        
        return !(player.getTranslateX() == hitbox.getTranslateX());
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

    private static void shrinkByAmount(ImageView image, double d) {
        image.setScaleX(d);
        image.setScaleY(d);
   }
}

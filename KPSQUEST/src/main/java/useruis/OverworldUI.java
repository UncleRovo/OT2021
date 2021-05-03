package useruis;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import utils.GraphicsBuilder;

public class OverworldUI {
    
    public static void setOverWorldScene(Stage s, String name) {
        Pane pane2 = new Pane();
        pane2.setMinSize(500, 300);
        pane2.setMaxSize(700, 400);
        
        Image bg = new Image(utils.Utils.getFilePath("file:tausta_test.jpg"));
        ImageView background = new ImageView(bg);
        background.setTranslateX(-900);
        
        Image pl = new Image(utils.Utils.getFilePath("file:protagonist.png"));
        ImageView player = new ImageView(GraphicsBuilder.getImageWithoutBlue(pl));
        
        ImageView house1Bottom = GraphicsBuilder.getGraphicsObject("file:talo_ala.png", 250, 700, 0.5, true);
        
        
        Image ht = new Image(utils.Utils.getFilePath("file:talo_yla.png"));
        ImageView house1Top = new ImageView(GraphicsBuilder.getImageWithoutBlue(ht));
        house1Top.setTranslateX(house1Bottom.getTranslateX() - 9);
        house1Top.setTranslateY(house1Bottom.getTranslateY() - 122);
        
        Polygon hitbox = new Polygon(0, 0, 255, 0, 255, 20, 0, 20);
        hitbox.setVisible(false);
        hitbox.setTranslateX(house1Bottom.getTranslateX() + 140);
        hitbox.setTranslateY(house1Bottom.getTranslateY() + 34);
        
        GraphicsBuilder.shrinkByAmount(player, 0.5);
        //GraphicsBuilder.shrinkByAmount(house1Bottom, 0.5);
        GraphicsBuilder.shrinkByAmount(house1Top, 0.5);
        
        pane2.getChildren().add(background);
        
        pane2.getChildren().add(house1Bottom);
        pane2.getChildren().add(player);
        pane2.getChildren().add(house1Top);
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
        //s.setMaximized(true);
        s.setHeight(background.getImage().getHeight());
        s.setWidth(background.getImage().getWidth() * 0.7);
        s.setMaxHeight(background.getImage().getHeight());
        s.setMaxWidth(background.getImage().getWidth() * 0.7);
    }
    
    public static boolean collision(ImageView object, Polygon hitbox) {
        return !(object.getTranslateX() == hitbox.getTranslateX());
    }
}

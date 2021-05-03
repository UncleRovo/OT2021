package useruis;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import utils.GraphicsBuilder;

public class OverworldUI {
    
    public static void setOverWorldScene(Stage s, String name) {
        Pane pane2 = new Pane();
        pane2.setMinSize(500, 300);
        pane2.setMaxSize(700, 400);
        
        ImageView background = GraphicsBuilder.getGraphicsObject("file:tausta_test.jpg", -900, 0, 1, false);
        ImageView player = GraphicsBuilder.getGraphicsObject("file:protagonist.png", 0, 0, 0.5, true);
        ImageView house1Bottom = GraphicsBuilder.getGraphicsObject("file:talo_ala.png", 250, 700, 0.5, true);
        ImageView house1Top = GraphicsBuilder.getGraphicsObject("file:talo_yla.png", 241, 578, 0.5, true);
        Polygon houseHitbox = GraphicsBuilder.getHitbox(0.0, 0.0, 255.0, 0.0, 255.0, 20.0, 0.0, 20.0, false, 390, 734);
        
        pane2.getChildren().addAll(background, house1Bottom, player, house1Top, houseHitbox);
        
        Scene ovscene = new Scene(pane2);
        
        ovscene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            KeyCode latest = null;
            @Override
            public void handle(KeyEvent event) {
                if (!(player.getBoundsInParent().intersects(houseHitbox.getBoundsInParent()))) {
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

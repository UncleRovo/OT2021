package useruis;

import java.util.HashSet;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class OverworldUI {
    
    public static void setOverWorldScene(Stage s, String name) {
        HashSet<Polygon> hitboxes = new HashSet<>();
        Pane pane2 = new Pane();
        pane2.setMinSize(500, 300);
        pane2.setMaxSize(700, 400);
        
        ImageView background = GraphicsBuilder.getGraphicsObject("file:tausta_test.jpg", -900, 0, 1, false);
        ImageView player = GraphicsBuilder.getGraphicsObject("file:protagonist.png", 0, 0, 0.5, true);
        ImageView house1Bottom = GraphicsBuilder.getGraphicsObject("file:talo_ala.png", 250, 700, 0.5, true);
        ImageView house1Top = GraphicsBuilder.getGraphicsObject("file:talo_yla.png", 241, 578, 0.5, true);
        ImageView rival = GraphicsBuilder.getGraphicsObject("file:rival.png", 1250, 0, 0.5, true);
        
        Polygon houseHitbox = GraphicsBuilder.getHitbox(0, 0, 255, 0, 255, 20, 0, 20, false, 390, 734);
        Polygon rivalHitbox = GraphicsBuilder.getHitbox(0, 0, 40, 0, 40, 140, 0, 140, false, 1320, 70);
        
        hitboxes.add(houseHitbox);
        hitboxes.add(rivalHitbox);
        
        pane2.getChildren().addAll(background, house1Bottom, rival, player, house1Top);
        pane2.getChildren().addAll(houseHitbox, rivalHitbox);
        
        Scene ovscene = new Scene(pane2);
        
        ovscene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            KeyCode latest = null;
            @Override
            public void handle(KeyEvent event) {
                if (!(collision(hitboxes, player))) {
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
    
    public static boolean collision(HashSet<Polygon> hitboxes, ImageView object) {
        return hitboxes.stream().anyMatch((hitbox) -> (object.getBoundsInParent().intersects(hitbox.getBoundsInParent())));
    }
}

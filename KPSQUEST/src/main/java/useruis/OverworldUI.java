package useruis;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class OverworldUI {
    
    public static void setOverWorldScene(Stage s, String name) {
        List<Node> elements = new ArrayList<>();
        HashSet<Polygon> hitboxes = new HashSet<>();
        
        Pane pane2 = new Pane();
        pane2.setMinSize(500, 300);
        pane2.setMaxSize(700, 400);
        
        buildGraphics(elements);
        
        
        
        Polygon houseHitbox = GraphicsBuilder.getPolygon(0, 0, 255, 0, 255, 20, 0, 20, false, 390, 734);
        Polygon rivalHitbox = GraphicsBuilder.getPolygon(0, 0, 40, 0, 40, 140, 0, 140, false, 1320, 70);
        Polygon rivalTalkbox = GraphicsBuilder.getPolygon(0, 0, 150, 0, 150, 60, 0, 60, true, 1260, 130);
        
        
        
        hitboxes.add(houseHitbox);
        hitboxes.add(rivalHitbox);
        
        pane2.getChildren().addAll(elements);
        pane2.getChildren().addAll(houseHitbox, rivalHitbox, rivalTalkbox);
        
        DialogueHandler convo = new DialogueHandler();
        pane2.getChildren().addAll(convo.dialogueBox, convo.text);
        
        Scene ovscene = new Scene(pane2);
        
        ovscene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            DialogueHandler diaHandler = convo;
            Node player = elements.get(3);
            KeyCode latest = null;
            @Override
            public void handle(KeyEvent event) {
                if (!(collision(hitboxes, player)) && diaHandler.isDialogue == false) {
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
                } else if (diaHandler.isDialogue == false){
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
                
                if (event.getCode() == KeyCode.X && player.getBoundsInParent().intersects(rivalTalkbox.getBoundsInParent())) {
                    diaHandler.talk();
                }
            }
        });
        s.setScene(ovscene);
        s.setMaximized(false);
        s.setHeight(1200);
        s.setWidth(2000);
        s.setMaxHeight(1500);
        s.setMaxWidth(2400);
    }
    
    public static boolean collision(HashSet<Polygon> hitboxes, Node object) {
        return hitboxes.stream().anyMatch((hitbox) -> (object.getBoundsInParent().intersects(hitbox.getBoundsInParent())));
    }

    private static void buildGraphics(List<Node> elements) {
        ImageView background = GraphicsBuilder.getGraphicsObject("file:tausta_test.jpg", -900, 0, 1, false);
        ImageView player = GraphicsBuilder.getGraphicsObject("file:protagonist.png", 0, 0, 0.5, true);
        ImageView house1Bottom = GraphicsBuilder.getGraphicsObject("file:talo_ala.png", 250, 700, 0.5, true);
        ImageView house1Top = GraphicsBuilder.getGraphicsObject("file:talo_yla.png", 241, 578, 0.5, true);
        ImageView rival = GraphicsBuilder.getGraphicsObject("file:rival.png", 1250, 0, 0.5, true);
        elements.add(background);
        elements.add(house1Bottom);
        elements.add(rival);
        elements.add(player);
        elements.add(house1Top);
    }
}

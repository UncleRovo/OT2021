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
        buildHitboxes(elements, hitboxes);
        
        pane2.getChildren().addAll(elements);
        
        DialogueHandler convo = new DialogueHandler();
        pane2.getChildren().addAll(convo.dialogueBox, convo.text);
        
        Scene ovscene = new Scene(pane2);
        setKeyDetection(ovscene, elements, hitboxes, convo);
        
        s.setScene(ovscene);
        s.setHeight(1200);
        s.setWidth(2000);
        s.setMaxHeight(1500);
        s.setMaxWidth(2400);
    }
    
    /**
    * Metodi tarkastelee napinpainalluksia, ja suorittaa tarvittavat toiminnot
    * oikean napin ollessa painettuna.
    */
    public static void setKeyDetection(Scene ovscene, List<Node> elements, HashSet<Polygon> hitboxes, DialogueHandler convo) {
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
                
                if (event.getCode() == KeyCode.X && player.getBoundsInParent().intersects(elements.get(7).getBoundsInParent())) {
                    diaHandler.talk();
                }
            }
        });
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

    private static void buildHitboxes(List<Node> elements, HashSet<Polygon> hitboxes) {
        Polygon houseHitbox = GraphicsBuilder.getPolygon(255, 255, 20, 20, false, 390, 734);
        Polygon rivalHitbox = GraphicsBuilder.getPolygon(40, 40, 140, 140, false, 1320, 70);
        Polygon rivalTalkbox = GraphicsBuilder.getPolygon(150, 150, 60, 60, false, 1260, 130);
        
        elements.add(houseHitbox);
        elements.add(rivalHitbox);
        elements.add(rivalTalkbox);
        
        hitboxes.add(houseHitbox);
        hitboxes.add(rivalHitbox);
    }
}

package useruis;

import data.Savefile;
import data.Character;
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
    
    public static void setOverWorldScene(Stage s, Savefile file) {
        List<Node> elements = new ArrayList<>();
        HashSet<Polygon> hitboxes = new HashSet<>();
        
        Pane pane2 = new Pane();
        pane2.setMinSize(500, 300);
        pane2.setMaxSize(700, 400);
        
        buildOverWorldScene(1, elements, hitboxes);
        
        pane2.getChildren().addAll(elements);
        
        DialogueHandler convo = new DialogueHandler(pane2, elements);
        pane2.getChildren().addAll(convo.dialogueBox, convo.text);
        
        Scene ovscene = new Scene(pane2);
        setKeyDetection(ovscene, elements, hitboxes, convo, pane2, file);
        
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
    public static void setKeyDetection(Scene ovscene, List<Node> elements, HashSet<Polygon> hitboxes, DialogueHandler convo, Pane pane, Savefile file) {
        ovscene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            Character rival = new Character("Rival", 6, 5, 500);
            DialogueHandler diaHandler = convo;
            Node player = elements.get(7);
            KeyCode latest = null;
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.A) {
                    System.out.println(file.name + ", " + file.character.luck + ", " + file.character.charisma + ", " + file.character.money);
                }
                
                
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
                } else if (diaHandler.isDialogue == false) {
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
                
                if ((event.getCode() == KeyCode.X && player.getBoundsInParent().intersects(elements.get(elements.size() - 2).getBoundsInParent())) || (diaHandler.isChoice && (event.getCode() == KeyCode.C || event.getCode() == KeyCode.V))) {
                    if (diaHandler.isDialogue == false) {
                        diaHandler.setupTalk(Integer.valueOf(elements.get(elements.size() - 2).getId()));
                    } else if (diaHandler.isDialogue && diaHandler.isChoice == false) {
                        diaHandler.talk(file.character, rival);
                    } else if (diaHandler.isDialogue && event.getCode() == KeyCode.C && diaHandler.isChoice) {
                        diaHandler.selectYesOrNo(0);
                        diaHandler.talk(file.character, rival);
                        diaHandler.i++;
                    } else if (diaHandler.isDialogue && event.getCode() == KeyCode.V && diaHandler.isChoice) {
                        diaHandler.selectYesOrNo(1);
                        diaHandler.talk(file.character, rival);
                    }
                }
                
                
                
                if (player.getBoundsInParent().intersects(elements.get(elements.size() - 1).getBoundsInParent()) && elements.get(elements.size() - 1).getId().contains("mapborder")) {
                    int i = 1;
                    
                    if (elements.get(elements.size() - 1).getId().contains("2")) {
                        i = 2;
                    }
                    pane.getChildren().clear();
                    buildOverWorldScene(i, elements, hitboxes);
                    player = findPlayer(elements);
                    pane.getChildren().addAll(elements);
                    pane.getChildren().addAll(diaHandler.dialogueBox, diaHandler.text);
                }
                
                if (event.getCode() == KeyCode.P) {
                    System.out.println(player);
                    System.out.println(player.getBoundsInParent());
                    System.out.println(elements.get(elements.size() - 1));
                    System.out.println(elements.get(elements.size() - 1).getId());
                }
            }
        });
    }
    
    public static boolean collision(HashSet<Polygon> hitboxes, Node object) {
        return hitboxes.stream().anyMatch((hitbox) -> (object.getBoundsInParent().intersects(hitbox.getBoundsInParent())));
    }

    private static void buildGraphics(List<Node> elements, int i) {
        if (i == 1) {
            ImageView background = GraphicsBuilder.getGraphicsObject("file:tausta_test.jpg", -900, 0, 1, false);
            ImageView player = GraphicsBuilder.getGraphicsObject("file:protagonist.png", 800, -75, 0.5, true);
            ImageView house1Bottom = GraphicsBuilder.getGraphicsObject("file:talo_ala.png", 250, 700, 0.5, true);
            ImageView house1Top = GraphicsBuilder.getGraphicsObject("file:talo_yla.png", 241, 578, 0.5, true);
            ImageView rival = GraphicsBuilder.getGraphicsObject("file:rival.png", 1250, 0, 0.5, true);
            ImageView fence1 = GraphicsBuilder.getGraphicsObject("file:fence_short.png", 0, 0, 0.98, true);
            ImageView longfence = GraphicsBuilder.getGraphicsObject("file:fence_long.png", -15, 925, 0.98, true);
            ImageView fence2 = GraphicsBuilder.getGraphicsObject("file:fence_short.png", 950, 0, 0.98, true);
            ImageView leftfence = GraphicsBuilder.getGraphicsObject("file:fence_vertical_left.png", 0, -42, 0.98, true);
            ImageView rightfence = GraphicsBuilder.getGraphicsObject("file:fence_vertical_right.png", 1750, -42, 0.98, true);
        
            leftfence.setScaleY(0.92);
            rightfence.setScaleY(0.92);
            player.setId("player");
            elements.add(background);
            elements.add(house1Bottom);
            elements.add(fence1);
            elements.add(fence2);
            elements.add(rival);
            elements.add(leftfence);
            elements.add(rightfence);
            elements.add(player);
            elements.add(house1Top);
            elements.add(longfence);
        } else if (i == 2) {
            ImageView background = GraphicsBuilder.getGraphicsObject("file:tausta_test.jpg", -900, 0, 1, false);
            background.setId("background");
            ImageView player = GraphicsBuilder.getGraphicsObject("file:protagonist.png", 790, 900, 0.5, true);
            player.setId("player");
            elements.add(background);
            elements.add(player);
        }
    }

    private static void buildHitboxes(List<Node> elements, HashSet<Polygon> hitboxes, int i) {
        if (i == 1) {
            Polygon houseHitbox = GraphicsBuilder.getPolygon(255, 255, 20, 20, false, 390, 734);
            Polygon rivalHitbox = GraphicsBuilder.getPolygon(40, 40, 140, 140, false, 1320, 70);
            Polygon rivalTalkbox = GraphicsBuilder.getPolygon(150, 150, 60, 60, false, 1260, 130);
            Polygon lowfenceHitbox = GraphicsBuilder.getPolygon(1750, 1750, 60, 60, false, 50, 1010);
            Polygon shortfenceHitbox1 = GraphicsBuilder.getPolygon(765, 765, 60, 60, false, 50, -52);
            Polygon shortfenceHitbox2 = GraphicsBuilder.getPolygon(765, 765, 60, 60, false, 965, -52);
            Polygon leftfenceHitbox = GraphicsBuilder.getPolygon(60, 60, 1000, 1000, false, 28, 0);
            Polygon rightfenceHitbox = GraphicsBuilder.getPolygon(60, 60, 1000, 1000, false, 1760, 0);
            Polygon newAreaTrigger = GraphicsBuilder.getPolygon(140, 140, 60, 60, false, 820, -200);
            newAreaTrigger.setId("mapborder2");
        
        
            elements.add(houseHitbox);
            elements.add(rivalHitbox);
            elements.add(lowfenceHitbox);
            elements.add(shortfenceHitbox1);
            elements.add(shortfenceHitbox2);
            elements.add(leftfenceHitbox);
            elements.add(rightfenceHitbox);
            elements.add(rivalTalkbox);
            rivalTalkbox.setId("0");
            elements.add(newAreaTrigger);
        
            hitboxes.add(houseHitbox);
            hitboxes.add(rivalHitbox);
            hitboxes.add(shortfenceHitbox1);
            hitboxes.add(shortfenceHitbox2);
            hitboxes.add(lowfenceHitbox);
            hitboxes.add(leftfenceHitbox);
            hitboxes.add(rightfenceHitbox);
        } else if (i == 2) {
            Polygon newAreaTrigger = GraphicsBuilder.getPolygon(140, 140, 60, 60, true, 820, 1200);
            newAreaTrigger.setId("mapborder1");
            elements.add(newAreaTrigger);
            hitboxes.add(newAreaTrigger);
        }
    }

    private static void buildOverWorldScene(int i, List<Node> elements, HashSet<Polygon> hitboxes) {
        elements.clear();
        hitboxes.clear();
        buildGraphics(elements, i);
        buildHitboxes(elements, hitboxes, i);
    }
    
    private static Node findPlayer(List<Node> elements) {
        for (Node n: elements) {
            try {
                if (n.getId().equals("player")) {
                return n;
                }
            } catch (Exception e) {
                
            }
        }
        return null;
    }
}

package useruis;

import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Battlehandler {
    GridPane view = new GridPane();
    String player;
    String enemy;
    List<Node> elements;
    Pane pane;
    
    public Battlehandler(List<Node> elements, Pane pane) {
        player = "Player";
        enemy = "Rival";
        this.elements = elements;
        Label p = new Label(player);
        Label e = new Label(enemy);
        Button b = new Button("PAINA TÄSTÄ");
        b.setOnMouseClicked(c ->{
            this.endBattle();
        });
        view.add(p, 0, 0);
        view.add(e, 2, 0);
        view.add(b, 1, 1);
        this.pane = pane;
    }
    
    public void startBattle() {
        System.out.println("Taistelu alkoi");
        pane.getChildren().addAll(view);
    }
    
    public void endBattle() {
        pane.getChildren().removeAll(view);
    }
}

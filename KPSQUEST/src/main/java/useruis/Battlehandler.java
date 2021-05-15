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
    Label p;
    Label e;
    Button b;
    Polygon poly;
    
    public Battlehandler(List<Node> elements, Pane pane) {
        player = "Player";
        enemy = "Rival";
        this.elements = elements;
        p = new Label(player);
        e = new Label(enemy);
        b = new Button("PAINA TÄSTÄ");
        b.setOnMouseClicked(c ->{
            this.endBattle();
        });
        poly = GraphicsBuilder.getPolygon(700, 700, 390, 390, true, 0, 0);
        poly.setFill(Color.RED);
        move(poly, 500, 100);
        move(p, 500, 100);
        move(e, 900, 100);
        move(b, 700, 300);
        this.pane = pane;
    }
    
    public void startBattle() {
        System.out.println("Taistelu alkoi");
        pane.getChildren().addAll(poly,p, e, b);
    }
    
    public void endBattle() {
        pane.getChildren().removeAll(poly, p, e, b);
    }
    
    public void move(Node n, double x, double y) {
        n.setTranslateX(x);
        n.setTranslateY(y);
    }
}

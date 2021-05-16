package useruis;

import data.Battle;
import java.util.List;
import data.Character;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Battlehandler {
    GridPane view = new GridPane();
    Character player;
    Character enemy;
    List<Node> elements;
    Pane pane;
    Polygon poly;
    Label stake = new Label("0");
    Battle battle;
    
    public Battlehandler(List<Node> elements, Pane pane) {
        this.elements = elements;
       
        poly = GraphicsBuilder.getPolygon(700, 700, 390, 390, true, 0, 0);
        poly.setFill(Color.RED);
        this.pane = pane;
    }
    
    public void startBattle(Character player, Character rival) {
        this.player = player;
        enemy = rival;
        
        GridPane stakes = new GridPane();
        Button incbet = new Button(">");
        Button decbet = new Button("<");
        stake.setText(this.getStake(player));
        
        incbet.setOnAction(p ->{
            if (Integer.valueOf(stake.getText()) < player.money) {
                stake.setText(Integer.toString(Integer.valueOf(stake.getText()) + 1));
            }
        });
        decbet.setOnAction(p ->{
            if (Integer.valueOf(stake.getText()) > 1) {
                stake.setText(Integer.toString(Integer.valueOf(stake.getText()) - 1));
            }
        });
        
        Button ready = new Button("I'm ready");
        
        stakes.add(new Label("Paljonko lyöt vetoa?"), 0, 0);
        stakes.add(decbet, 1, 1);
        stakes.add(stake, 2, 1);
        stakes.add(incbet, 3, 1);
        stakes.add(ready, 2, 2);
        
        
        ready.setOnAction(p ->{
            this.chooseWeapon(stakes, Integer.valueOf(stake.getText()));
        });
        
        
        Label p = new Label(this.player.name);
        Label e = new Label(enemy.name);
        pane.getChildren().addAll(poly, stakes);
    }
    
    public void endBattle() {
        pane.getChildren().removeAll(poly);
    }
    
    public void move(Node n, double x, double y) {
        n.setTranslateX(x);
        n.setTranslateY(y);
    }

    private String getStake(Character player) {
        if (player.money == 1) {
            return "1";
        } else {
            return Integer.toString(player.money / 2);
        }
    }

    private void chooseWeapon(GridPane select, int stake) {
        
        pane.getChildren().remove(select);
        select = new GridPane();
        
        
        
        String[] options = new String[]{"Rock", "Paper", "Scissors"};
        
        select.add(new Label("Which weapon do you choose?"), 1, 0);
        
        for (int i = 0; i < 3; i++) {
            Button b = new Button(options[i]);
            b.setOnAction((ActionEvent p) -> {
                Battlehandler.this.finalResult(b.getText(), stake);
            });
            select.add(b, i, 1);
        }
        pane.getChildren().add(select);

        
    }

    private void finalResult(String option, int stake) {
        battle = new Battle(player, enemy, stake);
        battle.option = option;
        
        String result = battle.fight();
        
        Button b = new Button("Next");
        
        b.setOnAction(p ->{
            pane.getChildren().remove(pane.getChildren().size() - 1);
            pane.getChildren().remove(poly);
            pane.getChildren().remove(b);
            if (result.contains("great")) {
                startBattle(player, enemy);
            } else {
                this.endBattle();
            }
            
        });
        
        pane.getChildren().remove(pane.getChildren().size() - 1);
        
        pane.getChildren().addAll(b, new Label(result));
    }
}

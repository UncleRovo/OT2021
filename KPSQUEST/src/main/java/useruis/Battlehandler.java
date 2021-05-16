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
import javafx.scene.text.Font;

public class Battlehandler {
    GridPane view = new GridPane();
    Character player;
    Character enemy;
    List<Node> elements;
    Pane pane;
    Polygon poly;
    Label stake = new Label("0");
    Battle battle;
    DialogueHandler diahand;
    
    public Battlehandler(List<Node> elements, Pane pane) {
        this.elements = elements;
        stake.setFont(new Font(30));
        poly = GraphicsBuilder.getPolygon(700, 700, 390, 390, true, 0, 0);
        poly.setFill(Color.RED);
        this.move(poly, 600, 120);
        this.pane = pane;
    }
    
    public void startBattle(Character player, Character rival, DialogueHandler diahand) {
        this.diahand = diahand;
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
        Label howmuch = new Label("How much will you bet?");
        howmuch.setFont(new Font(30));
        stakes.add(howmuch, 0, 0);
        stakes.add(decbet, 1, 1);
        stakes.add(stake, 2, 1);
        stakes.add(incbet, 3, 1);
        stakes.add(ready, 2, 2);
        
        
        ready.setOnAction(p ->{
            this.chooseWeapon(stakes, Integer.valueOf(stake.getText()));
        });
        this.move(stakes, 600, 120);
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
        Label wep = new Label("Which weapon do you choose?");
        wep.setFont(new Font(30));
        select.add(wep, 0, 0);
        
        for (int i = 1; i < 4; i++) {
            Button b = new Button(options[i - 1]);
            b.setOnAction((ActionEvent p) -> {
                Battlehandler.this.finalResult(b.getText(), stake);
            });
            select.add(b, i, 1);
        }
        move(select, 700, 300);
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
                startBattle(player, enemy, diahand);
            } else {
                diahand.isBattle = false;
                this.endBattle();
            }
            
        });
        
        move(b, 700, 400);
        
        pane.getChildren().remove(pane.getChildren().size() - 1);
        Label res = new Label(result);
        move(res, 600, 200);
        res.setFont(new Font(30));
        pane.getChildren().addAll(b, res);
    }
}

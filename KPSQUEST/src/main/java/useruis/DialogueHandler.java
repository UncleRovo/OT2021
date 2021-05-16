package useruis;

import data.Dialogue;
import data.Character;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;

public class DialogueHandler {
    
    
    String[] options;
    boolean isDialogue;
    Polygon dialogueBox;
    Label text = new Label("_");
    int i = -1;
    Dialogue d = new Dialogue();
    boolean isChoice;
    Battlehandler battle;
    boolean isBattle;
    List<Node> elements;
    Polygon battleBox;
    
    
    public DialogueHandler(Pane p, List<Node> elements) {
        dialogueBox = GraphicsBuilder.getPolygon(1750, 1750, 400, 400, false, 50, 600);
        dialogueBox.setFill(Color.LIGHTGRAY);
        battleBox = GraphicsBuilder.getPolygon(1750, 1750, 400, 400, false, 50, 200);
        battleBox.setFill(Color.RED);
        isDialogue = false;
        isChoice = false;
        isBattle = false;
        text.setVisible(false);
        text.setFont(new Font(95));
        text.setTranslateX(dialogueBox.getTranslateX() + 40);
        text.setTranslateY(dialogueBox.getTranslateY() + 20);
        battle = new Battlehandler(elements, p);
        this.elements = elements;
    }
    
    /**
    * Metodi suorittaa puhekuplan ja tekstin piirtämisen ruudulle.
     * @param player
     * @param rival
    */
    public void talk(Character player, Character rival) {
        
        if (options[i].contains("_END_")) {
            endConvo();
            return;
        } else if (options[i].contains("_CHOICE_")) {
            isChoice = true;
            i++;
        } else if (options[i].contains("_BATTLE_")) {
            isChoice = false;
            isBattle = true;
            text.setText("GAME ON!");
            battle.startBattle(player, rival);
            i++;
            return;
        }
        
        text.setText(options[i]);
        i++;
    }
    
    public void endConvo() {
        isDialogue = false;
        text.setVisible(false);
        dialogueBox.setVisible(false);
        text.setText("_");
        i = -1;
    }
    
    public void setupTalk(int i, int talkstart) {
        options = d.lines[i];
        this.i = talkstart;
        this.isDialogue = true;
        this.isChoice = false;
        text.setVisible(true);
        dialogueBox.setVisible(true);
        isBattle = false;
        this.talk(null, null);
    }

    void selectYesOrNo(int i) {
        this.i += i;
        this.isChoice = false;
    }
}

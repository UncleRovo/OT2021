package useruis;

import data.Dialogue;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;

public class DialogueHandler {
    
    /**
    * Väliaikaisratkaisuna hahmojen vuorosanoille toimii taulukko,
    * jossa on alkioina hahmojen vuorosanoja.
    */
    String[] options;
    boolean isDialogue;
    Polygon dialogueBox;
    Label text = new Label("_");
    int i = -1;
    Dialogue d = new Dialogue();
    boolean isChoice;
    
    public DialogueHandler() {
        dialogueBox = GraphicsBuilder.getPolygon(1750, 1750, 400, 400, false, 50, 600);
        dialogueBox.setFill(Color.LIGHTGRAY);
        isDialogue = false;
        isChoice = false;
        text.setVisible(false);
        text.setFont(new Font(95));
        text.setTranslateX(dialogueBox.getTranslateX() + 40);
        text.setTranslateY(dialogueBox.getTranslateY() + 20);
    }
    
    /**
    * Metodi suorittaa puhekuplan ja tekstin piirtämisen ruudulle.
    */
    public void talk() {
        
        if (options[i].contains("_END_")) {
            endConvo();
            return;
        } else if (options[i].contains("_CHOICE_")) {
            isChoice = true;
            i++;
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
    
    public void setupTalk(int i) {
        options = d.lines[i];
        this.i = 0;
        this.isDialogue = true;
        this.isChoice = false;
        text.setVisible(true);
        dialogueBox.setVisible(true);
        this.talk();
    }

    void selectYesOrNo(int i) {
        this.i += i;
        this.isChoice = false;
    }
}

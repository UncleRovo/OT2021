package useruis;

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
    
    public DialogueHandler() {
        options = new String[3];
        options[0] = "Hello. Have you ever wondered why...";
        options[1] = "Actually nevermind.";
        options[2] = "The dev hasn't implemented fighting\n mechanics yet";
        dialogueBox = GraphicsBuilder.getPolygon(1750, 1750, 400, 400, false, 50, 600);
        dialogueBox.setFill(Color.LIGHTGRAY);
        isDialogue = false;
        text.setVisible(false);
        text.setFont(new Font(95));
        text.setTranslateX(dialogueBox.getTranslateX() + 40);
        text.setTranslateY(dialogueBox.getTranslateY() + 20);
    }
    
    /**
    * Metodi suorittaa puhekuplan ja tekstin piirtämisen ruudulle.
    */
    public void talk() {
        isDialogue = true;
        text.setVisible(true);
        dialogueBox.setVisible(true);
        i++;
        if (i == options.length) {
            endConvo();
            return;
        }
        
        text.setText(options[i]);
    }
    
    public void endConvo() {
        isDialogue = false;
        text.setVisible(false);
        dialogueBox.setVisible(false);
        text.setText("_");
        i = -1;
    }
}
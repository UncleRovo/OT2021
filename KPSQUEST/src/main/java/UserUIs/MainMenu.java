package UserUIs;

import Utils.Savefile;
import java.io.File;
import java.util.Scanner;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {
    private Savefile[] files = new Savefile[] {new Savefile("New File 1", false), new Savefile("New File 2", false), new Savefile("New File 3", false)};
    
    public void setMainMenuScene(Stage s) {
        BorderPane pane = new BorderPane();
        VBox buttons = new VBox();
        pane.setMinSize(200, 200);
        
        for (Savefile file: files) {
            Button b = new Button(file.name);
            
            b.setOnAction(p ->{
                if (file.started) {
                    OverworldUI.setOverWorldScene(s, file.name);
                } else {
                    this.createNewSave(s, file);
                }
            });
            
            buttons.getChildren().add(b);
        }
        pane.setCenter(buttons);
        Scene mainmenu = new Scene(pane);
        s.setScene(mainmenu);
    }
    
    public void initFiles() {
        try {
            Scanner t = new Scanner(new File("GameData/SaveFiles/saves"));
            int i = 0;
            while (t.hasNextLine()) {
                String s = t.nextLine();
                if (s.length() == 0) {
                    //Tyhjä rivi
                } else {
                    files[i] = new Savefile(s, true);
                }
                i++;
            }
        } catch (Exception e) {
            
        }
    }

    private void createNewSave(Stage s, Savefile file) {
        BorderPane pane = new BorderPane();
        Label text = new Label("Please insert your name");
        TextField field = new TextField();
        Button b = new Button("This is my name");
        
        
        b.setOnAction(p ->{
            if (field.getText().length() > 0) {
                file.name = field.getText();
                file.started = true;
                this.setMainMenuScene(s);
            }
        });
        
        pane.setMinSize(200, 200);
        pane.setTop(text);
        pane.setCenter(field);
        pane.setBottom(b);
        Scene n = new Scene(pane);
        s.setScene(n);
    }
    
}

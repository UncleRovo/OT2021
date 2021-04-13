package UserUIs;

import java.io.File;
import java.util.Scanner;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu {
    private String[] files = new String[]{"File 1", "File 2", "File 3"};
    
    public void setMainMenuScene(Stage s) {
        BorderPane pane = new BorderPane();
        VBox buttons = new VBox();
        pane.setMinSize(200, 200);
        
        for (String filename: files) {
            Button b = new Button(filename);
            
            b.setOnAction(p ->{
                OverworldUI.setOverWorldScene(s, filename);
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
                    files[i] = s;
                }
                i++;
            }
        } catch (Exception e) {
            
        }
    }
    
}

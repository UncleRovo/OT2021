package useruis;

import utils.Savefile;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.Savefiles;

public class MainMenu {
    public Savefiles ss;
    
    public void setMainMenuScene(Stage s) {
        BorderPane pane = new BorderPane();
        VBox buttons = new VBox();
        pane.setMinSize(200, 200);
        Label welcome = new Label("Please select your game file:\n");
        
        Button clearFiles = new Button("Delete all save data");
        clearFiles.setOnAction(k ->{
            ss.deleteData();
            setMainMenuScene(s);
        });
        
        for (Savefile file: ss.files) {
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
        pane.setBottom(clearFiles);
        pane.setTop(welcome);
        Scene mainmenu = new Scene(pane);
        s.setScene(mainmenu);
    }

    private void createNewSave(Stage s, Savefile file) {
        BorderPane pane = new BorderPane();
        Label text = new Label("Please insert your name");
        TextField field = new TextField();
        Button b = new Button("This is my name");
        
        int i = 1;
        
        if (file.name.contains("2")) {
            i = 2;
        } else if (file.name.contains("3")) {
            i = 3;
        }
        
        int k = i;
        
        b.setOnAction(p ->{
            if (field.getText().length() > 0) {
                file.name = field.getText();
                file.started = true;
                this.setMainMenuScene(s);
                ss.createNew(file, k);
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

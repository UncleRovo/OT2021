package UserUIs;

import Utils.Savefile;
import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
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
        Label welcome = new Label("Please select your game file:\n");
        
        Button clearFiles = new Button("Delete all save data");
        clearFiles.setOnAction(k ->{
            try{
                PrintWriter writer = new PrintWriter(new File("GameData/SaveFiles/saves"));
                for (int i = 1; i < 4; i++) {
                    writer.print("save_" + i + "_0");
                    if (i < 3) {
                        writer.print("\n");
                    }
                }
                writer.close();
                files = new Savefile[] {new Savefile("New File 1", false), new Savefile("New File 2", false), new Savefile("New File 3", false)};
                setMainMenuScene(s);
            } catch (Exception e) {
                
            }
        });
        
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
        pane.setBottom(clearFiles);
        pane.setTop(welcome);
        Scene mainmenu = new Scene(pane);
        s.setScene(mainmenu);
    }
    
    public void initFiles() {
        try {
            Scanner t = new Scanner(new File("GameData/SaveFiles/saves"));
            while (t.hasNextLine()) {
                String s = t.nextLine();
                String[] savedata = s.split("_");
                if (savedata[2].equals("0")) {
                    files[Integer.valueOf(savedata[1]) - 1] = new Savefile("New Save " + savedata[1], false);
                } else {
                    files[Integer.valueOf(savedata[1]) - 1] = new Savefile(savedata[2], true);
                }
            }
        } catch (Exception e) {
            
        }
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
                
                try {
                    List<String> info = Files.readAllLines(Paths.get("GameData/SaveFiles/saves"));
                    PrintWriter writer = new PrintWriter(new File("GameData/SaveFiles/saves"));
                    
                    for (int c = 0; c < 3; c++) {
                        if (info.get(c).contains("save_" + k)) {
                            writer.print("save_" + k + "_" + file.name);
                        } else {
                            writer.print(info.get(c));
                        }
                        if (c < 2) {
                            writer.print("\n");
                        }
                    }
                    writer.close();
                    
                } catch(Exception e) {
                    
                }
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

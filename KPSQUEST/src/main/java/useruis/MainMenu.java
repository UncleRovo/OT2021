package useruis;

import data.Savefile;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import data.Savefiles;
import javafx.scene.layout.GridPane;

public class MainMenu {
    public Savefiles savefilemanager = new Savefiles();
    int count = 13;
        Label howto = new Label("Please set your Luck and Charisma:");
        Label points = new Label("13");
        Label lck = new Label("1");
        Label cha = new Label("1");
        Button lminus = new Button("<");
        Button cminus = new Button("<");
        Button lplus = new Button(">");
        Button cplus = new Button(">");
    
    public void setMainMenuScene(Stage s) {
        BorderPane pane = new BorderPane();
        VBox buttons = new VBox();
        pane.setMinSize(200, 200);
        Label welcome = new Label("Please select your game file:\n");
        
        Button clearFiles = new Button("Delete all save data");
        clearFiles.setOnAction(k ->{
            savefilemanager.deleteData();
            setMainMenuScene(s);
        });
        
        for (Savefile file: savefilemanager.files) {
            Button startButton = new Button(file.name);
            
            startButton.setOnAction(p ->{
                if (file.started) {
                    OverworldUI.setOverWorldScene(s, file);
                } else {
                    this.createNewSave(s, file);
                }
            });
            
            buttons.getChildren().add(startButton);
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
        Button b = new Button("I have created my character");
        
        int i = 1;
        
        if (file.name.contains("2")) {
            i = 2;
        } else if (file.name.contains("3")) {
            i = 3;
        }
        
        int number = i;
        
        GridPane initstats = new GridPane();
        
        
        lplus.setOnAction(p ->{
            if (count != 0 && !(this.lck.getText().equals("10"))) {
                this.lck.setText(Integer.toString(Integer.valueOf(lck.getText()) + 1));
                this.count--;
                this.points.setText(Integer.toString(this.count));
            }
        });
        
        cplus.setOnAction(p ->{
            if (count != 0 && !(this.cha.getText().equals("10"))) {
                this.cha.setText(Integer.toString(Integer.valueOf(cha.getText()) + 1));
                this.count--;
                this.points.setText(Integer.toString(this.count));
            }
        });
        
        lminus.setOnAction(p ->{
            if (!(lck.getText().equals("1"))) {
                this.lck.setText(Integer.toString(Integer.valueOf(lck.getText()) - 1));
                this.count++;
                this.points.setText(Integer.toString(this.count));
            }
        });
        
        cminus.setOnAction(p ->{
            if (!(cha.getText().equals("1"))) {
                this.cha.setText(Integer.toString(Integer.valueOf(cha.getText()) - 1));
                this.count++;
                this.points.setText(Integer.toString(this.count));
            }
        });
        
        b.setOnAction(p ->{
            if (field.getText().length() > 0) {
                file.name = field.getText();
                file.started = true;
                file.character.name = file.name;
                file.character.luck = Integer.valueOf(lck.getText());
                file.character.charisma = Integer.valueOf(cha.getText());
                file.character.money = 500;
                this.setMainMenuScene(s);
                savefilemanager.createNew(file, number);
            }
        });
        
        initstats.add(howto, 1, 0);
        initstats.add(new Label("Available points: "), 0, 1);
        initstats.add(points, 1, 1);
        initstats.add(lck, 2, 2);
        initstats.add(cha, 2, 3);
        initstats.add(lplus, 3, 2);
        initstats.add(new Label("Luck:     "), 1, 2);
        initstats.add(cplus, 3, 3);
        initstats.add(new Label("Charisma: "), 1, 3);
        initstats.add(lminus, 0, 2);
        initstats.add(cminus, 0, 3);
        initstats.add(b, 1, 5);
        
        pane.setMinSize(200, 200);
        pane.setTop(text);
        pane.setCenter(field);
        //pane.setBottom(b);
        pane.setBottom(initstats);
        Scene n = new Scene(pane);
        s.setScene(n);
    }
    
}

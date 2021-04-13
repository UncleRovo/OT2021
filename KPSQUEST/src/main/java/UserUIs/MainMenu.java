package UserUIs;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainMenu {
    
    public static void setMainMenuScene(Stage s) {
        BorderPane pane = new BorderPane();
        Button b = new Button("Heipä hei");
        pane.setCenter(b);
        pane.setMinSize(200, 200);
        
        Scene mainmenu = new Scene(pane);
        b.setOnAction(p ->{
            OverworldUI.setOverWorldScene(s);
        });
        
        s.setScene(mainmenu);
    }
    
}

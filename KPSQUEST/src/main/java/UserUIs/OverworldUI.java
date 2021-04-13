package UserUIs;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class OverworldUI {
    
    public static void setOverWorldScene(Stage s, String name) {
        BorderPane pane2 = new BorderPane();
        pane2.setMinSize(500, 300);
        pane2.setCenter(new Label("Tervetuloa peliin " + name +"!"));
        
        Scene ovscene = new Scene(pane2);
        s.setScene(ovscene);
    }
}

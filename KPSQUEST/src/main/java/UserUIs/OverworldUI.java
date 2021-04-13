package UserUIs;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class OverworldUI {
    
    public static void setOverWorldScene(Stage s) {
        BorderPane pane2 = new BorderPane();
        pane2.setMinSize(500, 300);
        pane2.setCenter(new Label("Hurraa!"));
        
        Scene ovscene = new Scene(pane2);
        s.setScene(ovscene);
    }
}

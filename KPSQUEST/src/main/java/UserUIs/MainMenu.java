package UserUIs;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class MainMenu {
    private OverworldUI u = new OverworldUI();

    public Scene testScene() {
        BorderPane pane = new BorderPane();
        Button b = new Button("Heipä hei");
        pane.setCenter(b);
        
        pane.setMinSize(200, 200);
        
        
        Scene scene = new Scene(pane);
        b.setOnAction(k ->{
           
        });
        return scene;
    }
    
}

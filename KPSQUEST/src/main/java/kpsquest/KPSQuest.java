package kpsquest;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class KPSQuest extends Application {
    
    public static void main(String[] args) {
        launch(KPSQuest.class);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        setMainMenuScene(arg0);
        arg0.show();
    }
    
    public void setMainMenuScene(Stage s) {
        BorderPane pane = new BorderPane();
        Button b = new Button("Heipä hei");
        pane.setCenter(b);
        pane.setMinSize(200, 200);
        
        Scene mainmenu = new Scene(pane);
        b.setOnAction(p ->{
            setOverWorldScene(s);
        });
        
        s.setScene(mainmenu);
    }
    
    public void setOverWorldScene(Stage s) {
        BorderPane pane2 = new BorderPane();
        pane2.setMinSize(500, 300);
        pane2.setCenter(new Label("Hurraa!"));
        
        Scene ovscene = new Scene(pane2);
        s.setScene(ovscene);
    }
    
}



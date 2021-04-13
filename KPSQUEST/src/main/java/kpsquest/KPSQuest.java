package kpsquest;

import UserUIs.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class KPSQuest extends Application {
    
    public static void main(String[] args) {
        launch(KPSQuest.class);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        MainMenu.setMainMenuScene(arg0);
        arg0.show();
    }
}
package kpsquest;

import UserUIs.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;

public class KPSQuest extends Application {
    MainMenu m = new MainMenu();
    
    @Override
    public void init() {
        m.initFiles();
    }
    
    public static void main(String[] args) {
        launch(KPSQuest.class);
    }

    @Override
    public void start(Stage arg0) throws Exception {
        m.setMainMenuScene(arg0);
        arg0.show();
    }
}
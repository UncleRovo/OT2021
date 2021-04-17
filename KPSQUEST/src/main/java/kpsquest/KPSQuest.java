package kpsquest;

import UserUIs.MainMenu;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
        arg0.setMaxHeight(400);
        arg0.setMaxWidth(700);
        //arg0.initStyle(StageStyle.TRANSPARENT);
        arg0.show();
    }
}
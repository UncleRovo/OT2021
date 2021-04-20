package UserUIs;

import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class OverworldUI {
    
    public static void setOverWorldScene(Stage s, String name) {
        Pane pane2 = new Pane();
        pane2.setMinSize(500, 300);
        pane2.setMaxSize(700, 400);
        
        Image bg = new Image("file:tausta_test.jpg");
        ImageView background = new ImageView(bg);
        //background.setScaleX(0.5);
        //background.setScaleX(0.5);
        background.setTranslateX(-900);
        
        Image pl = new Image("file:protagonist.png");
        ImageView player = new ImageView(getImageWithoutBlue(pl));
        
        Image h_b = new Image("file:talo_ala.png");
        ImageView house1_bottom = new ImageView(getImageWithoutBlue(h_b));
        house1_bottom.setTranslateY(700);
        house1_bottom.setTranslateX(250);
        
        Image h_t = new Image("file:talo_yla.png");
        ImageView house1_top = new ImageView(getImageWithoutBlue(h_t));
        house1_top.setTranslateX(house1_bottom.getTranslateX() - 9);
        house1_top.setTranslateY(house1_bottom.getTranslateY() - 122);
        
        shrinkByAmount(player, 0.5);
        shrinkByAmount(house1_bottom, 0.5);
        shrinkByAmount(house1_top, 0.5);
        
        pane2.getChildren().add(background);
        
        pane2.getChildren().add(house1_bottom);
        pane2.getChildren().add(player);
        pane2.getChildren().add(house1_top);
        //pane2.getChildren().get(1).toFront();
        //pane2.getChildren().get(3).toFront();
        
        
        
        Scene ovscene = new Scene(pane2);
        
        ovscene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.LEFT) {
                        player.setTranslateX(player.getTranslateX() - 5);
                    }
                
                if (event.getCode() == KeyCode.RIGHT) {
                    player.setTranslateX(player.getTranslateX() + 5);
                }
                
                if (event.getCode() == KeyCode.DOWN) {
                        player.setTranslateY(player.getTranslateY() + 5);
                    }
                
                if (event.getCode() == KeyCode.UP) {
                    player.setTranslateY(player.getTranslateY() - 5);
                }
            }
        });
        s.setScene(ovscene);
    }
    
    public static WritableImage getImageWithoutBlue(Image image) {
        WritableImage wi = new WritableImage((int)image.getWidth(), (int)image.getHeight());
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = wi.getPixelWriter();
        
        System.out.println(Color.BLUE.toString() + "   " + reader.getColor(0, 0).toString());
        
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if (!(reader.getColor(x, y).toString().equals(Color.BLUE.toString()))) {
                    writer.setColor(x, y, reader.getColor(x, y));
                }
            }
        }
        return wi;
    }

    private static void shrinkByAmount(ImageView image, double d) {
        image.setScaleX(d);
        image.setScaleY(d);
   }
}

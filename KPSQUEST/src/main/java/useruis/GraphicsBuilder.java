package useruis;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class GraphicsBuilder {

    /**
     * Metodilla voi pienentää (taikka suurentaa) ImageView-oliota tarvittaessa,
     * hyödyllinen mikäli piirretty grafiikka osoittautuu väärän kokoiseksi.
     *
     * @param image Pienennettävä kuva
     * @param d Kuvan pienennysaste
     */
    public static void shrinkByAmount(ImageView image, double d) {
        image.setScaleX(d);
        image.setScaleY(d);
    }

    /**
     * Metodi ottaa Image-oliosta pois sinisen värin ja muuttaa sen
     * WritableImage-olioksi, poistettava sävy on: 0000ff
     *
     * @param image Grafiikka, kuten pelihahmo
     *
     * @return Grafiikka ilman sinistä taustaa.
     */
    public static WritableImage getImageWithoutBlue(Image image) {
        WritableImage wi = new WritableImage((int) image.getWidth(), (int) image.getHeight());
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = wi.getPixelWriter();

        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if (!(reader.getColor(x, y).toString().equals(Color.BLUE.toString()))) {
                    writer.setColor(x, y, reader.getColor(x, y));
                }
            }
        }
        return wi;
    }

    /**
     * Metodin avulla luodaan kuvatiedostosta ImageView-olio, jota voidaan
     * käyttää grafiikkana pelissä.
     *
     * @param filename Tiedostonimi muodossa "filename:kuva.png"
     * @param translateX Grafiikan lopullinen sijainti peliruudun X-akselilla
     * @param translateY Grafiikan lopullinen sijainti peliruudun Y-akselilla
     * @param sizemod Kuvan suurennos- tai pienennysaste
     * @param bluscreen Tarvitaanko kuvankäsittelyssä bluescreen-tekniikkaa
     * @return Käyttövalmis graafinen objekti
     */
    public static ImageView getGraphicsObject(String filename, double translateX, double translateY, double sizemod, boolean bluscreen) {
        Image image = new Image(data.Utils.getFilePath(filename));
        ImageView graphics;

        if (bluscreen) {
            graphics = new ImageView(getImageWithoutBlue(image));
        } else {
            graphics = new ImageView(image);
        }

        graphics.setTranslateX(translateX);
        graphics.setTranslateY(translateY);

        shrinkByAmount(graphics, sizemod);

        return graphics;
    }

    /**
     * Metodilla voi tehdä Polygon-olion, joka toimii esimerkiksi törmäysten
     * tarkastelussa tai puhekuplana.
     *
     * @param d1 Leveyttä säätelevä muuttuja
     * @param d3 Leveyttä säätelevä muuttuja
     * @param d4 Pituutta säätelevä muuttuja
     * @param d6 Pituutta säätelevä muuttuja
     * @param visible Onko polygoni näkyvissä
     * @param x Polygonin sijainti peliruudun x-koordinaatilla
     * @param y Polygonin sijainti peliruudun y-koordinaatilla
     * @return Näkyvä tai näkymätön Polygoni
     */
    public static Polygon getPolygon(double d1, double d3, double d4, double d6, boolean visible, double x, double y) {
        Polygon hitbox = new Polygon(0, 0, d1, 0, d3, d4, 0, d6);
        hitbox.setVisible(visible);
        hitbox.setTranslateX(x);
        hitbox.setTranslateY(y);

        return hitbox;
    }
}



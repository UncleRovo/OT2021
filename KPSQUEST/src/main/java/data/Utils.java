package data;

import java.io.InputStream;

public class Utils {
    
    /**
    * Metodin avulla löydetään oikea polku kuvatiedostoihin.
    * 
    * @param path Tiedoston nimi muodossa "file:kuva.png"
    * 
    * @return Oikea polku tiedostoon
    */
    public static String getFilePath(String path) {
        
        String[] name = path.split(":");
        path = "file:src/main/resources/Gamedata/Graphics/" + name[1];
        
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl == null) {
            cl = Class.class.getClassLoader();
        }
        cl.getResourceAsStream("Gamedata/Graphics/"+name[1]);
        
        //System.out.println("Haetaan resurssia: "+cl.getResource("Gamedata/Graphics/"+name[1]));
        
        if (cl.getResource("Gamedata/Graphics/"+name[1]) != null) {
            path = cl.getResource("Gamedata/Graphics/"+name[1]).toString();
        }
        
        //System.out.println("POLKU ON: " + path);
        return path;
    }
    
    public static String getSavePath(String path) {
        
        return path;
    }
}

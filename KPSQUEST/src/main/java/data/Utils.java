package data;

import java.io.InputStream;

public class Utils {
    public static String getFilePath(String path) {
        
        String[] name = path.split(":");
        path = "GameData/Graphics/" + name[1];
        
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl == null) {
            cl = Class.class.getClassLoader();
        }
        cl.getResourceAsStream(name[1]);
        
        //System.out.println(cl.getResource(name[1]));
        
        if (cl.getResource(name[1]) != null) {
            path = cl.getResource(name[1]).toString();
        }
        return path;
    }
    
    public static String getSavePath(String path) {
        
        return path;
    }
}

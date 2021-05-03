package utils;

import java.io.File;
import java.util.Scanner;

public class Savefiles {
    public Savefile[] files = new Savefile[3];
    
    public Savefiles() {
    }
    
    public void initFiles() {
        try {
            Scanner t = new Scanner(new File("saves"));
            while (t.hasNextLine()) {
                String s = t.nextLine();
                String[] savedata = s.split("_");
                if (savedata[2].equals("0")) {
                    files[Integer.valueOf(savedata[1]) - 1] = new Savefile("New Save " + savedata[1], false);
                } else {
                    files[Integer.valueOf(savedata[1]) - 1] = new Savefile(savedata[2], true);
                }
            }
        } catch (Exception e) {
            System.out.println("VIRHE");
        }
    }
}

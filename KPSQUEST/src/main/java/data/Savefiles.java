package data;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
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
    
    public void deleteData() {
        try{
                PrintWriter writer = new PrintWriter(new File("saves"));
                for (int i = 1; i < 4; i++) {
                    writer.print("save_" + i + "_0");
                    if (i < 3) {
                        writer.print("\n");
                    }
                }
                writer.close();
                files = new Savefile[] {new Savefile("New File 1", false), new Savefile("New File 2", false), new Savefile("New File 3", false)};
                
            } catch (Exception e) {
                
            }
    }
    
    public void createNew(Savefile file, int k) {
        
                try {
                    List<String> info = Files.readAllLines(Paths.get("saves"));
                    PrintWriter writer = new PrintWriter(new File("saves"));
                    
                    for (int c = 0; c < 3; c++) {
                        if (info.get(c).contains("save_" + k)) {
                            writer.print("save_" + k + "_" + file.name);
                        } else {
                            writer.print(info.get(c));
                        }
                        if (c < 2) {
                            writer.print("\n");
                        }
                    }
                    writer.close();
                    
                } catch(Exception e) {
                    
                }
    }
}

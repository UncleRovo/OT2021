package data;

import java.io.File;
import java.io.InputStream;
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
            File save = new File(data.Utils.getSavePath("saves.txt"));
            Scanner t = new Scanner(save);
            while (t.hasNextLine()) {
                String s = t.nextLine();
                String[] savedata = s.split("_");
                if (savedata[2].equals("0")) {
                    files[Integer.valueOf(savedata[1]) - 1] = new Savefile("New Save " + savedata[1], false);
                } else {
                    files[Integer.valueOf(savedata[1]) - 1] = new Savefile(savedata[2], true);
                    files[Integer.valueOf(savedata[1]) - 1].character.luck = Integer.valueOf(savedata[3]);
                    files[Integer.valueOf(savedata[1]) - 1].character.charisma = Integer.valueOf(savedata[4]);
                    files[Integer.valueOf(savedata[1]) - 1].character.money = Integer.valueOf(savedata[5]);
                }
            }
        } catch (Exception e) {
            System.out.println("Mentiin virheeseen: " + e);
            try {
                InputStream is = this.getClass().getClassLoader().getResourceAsStream("Gamedata/SaveFiles/saves.txt");
                Scanner t = new Scanner(is);
                while (t.hasNextLine()) {
                    String s = t.nextLine();
                    String[] savedata = s.split("_");
                    if (savedata[2].equals("0")) {
                        files[Integer.valueOf(savedata[1]) - 1] = new Savefile("New Save " + savedata[1], false);
                    } else {
                        files[Integer.valueOf(savedata[1]) - 1] = new Savefile(savedata[2], true);
                    }
                }
            } catch (Exception ee) {
                System.out.println(ee);
            }
        }
    }

    public void deleteData() {
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("Gamedata/SaveFiles/saves.txt");
            PrintWriter writer = new PrintWriter(data.Utils.getSavePath("saves.txt"));
            for (int i = 0; i < 3; i++) {
                writer.print("save_" + Integer.toString(i + 1) + "_0_0_0_0");
                if (i < 2) {
                    writer.print("\n");
                }
            }
            writer.close();
            files = new Savefile[3];
            files[0] = new Savefile("New File 1", false);
            files[1] = new Savefile("New File 2", false);
            files[2] = new Savefile("New File 3", false);
        } catch (Exception e) {

        }
    }

    public void createNew(Savefile file, int k) {
        try {
            List<String> info = Files.readAllLines(Paths.get(data.Utils.getSavePath("saves.txt")));
            PrintWriter writer = new PrintWriter(data.Utils.getSavePath("saves.txt"));

            for (int c = 0; c < 3; c++) {
                if (info.get(c).contains("save_" + k)) {
                    writer.print("save_" + k + "_" + file.name + "_" + file.character.luck + "_" + file.character.charisma + "_50");
                } else {
                    writer.print(info.get(c));
                }
                if (c < 2) {
                    writer.print("\n");
                }
            }
            writer.close();
        } catch (Exception e) {
        }
    }
}

package utils;

public class Savefile {
    public String name;
    public boolean started;
    
    public Savefile(String name, boolean started) {
        this.name = name;
        this.started = started;
    }
    
    public void changeName(String name) {
        this.name = name;
    }
    
    public void start() {
        this.started = true;
    }
}

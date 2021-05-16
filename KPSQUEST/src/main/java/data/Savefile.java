package data;

public class Savefile {

    public String name;
    public boolean started;
    public Character character = new Character("pl");

    public Savefile(String name, boolean started) {
        this.name = name;
        this.started = started;
    }

    /**
     * Metodi asettaa tallennustiedostolle nimen.
     *
     * @param name Tiedostolle annettava nimi
     */
    public void changeName(String name) {
        this.name = name;
    }

    public void start() {
        this.started = true;
    }
}

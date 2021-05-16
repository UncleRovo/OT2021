package LogicTests;

import data.Character;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CharacterTest {

    Character c;

    public CharacterTest() {

    }

    @Before
    public void setUp() {
        c = new Character("Hero", 10, 5, 50);
    }

    @Test
    public void takingmoneyworks1() {
        int amount = c.takeMoney(40);

        assertTrue(amount == 40);
        assertTrue(c.money == 10);
    }

    @Test
    public void takingmoneyworks2() {
        int amount = c.takeMoney(60);

        assertTrue(amount == 50);
        assertTrue(c.money == 0);
    }

    @Test
    public void addmoneyworks1() {
        c.addMoney(50);

        assertTrue(c.money == 100);
    }

    @Test
    public void addmoneyworks2() {
        c.addMoney(99999);

        assertTrue(c.money == 5000);
    }

}

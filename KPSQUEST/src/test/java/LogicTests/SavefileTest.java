package LogicTests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import data.Savefile;

public class SavefileTest {

    Savefile s;

    @Before
    public void setUp() {
        s = new Savefile("Jyrmy", false);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void savefileCreationWorks() {
        assertEquals("Jyrmy", s.name);
        assertEquals(false, s.started);
    }

    @Test
    public void nameChangesCorrectly() {
        s.changeName("Joonatan");
        assertEquals("Joonatan", s.name);
    }

    @Test
    public void startGameWorks() {
        s.start();
        assertTrue(s.started);
    }
}

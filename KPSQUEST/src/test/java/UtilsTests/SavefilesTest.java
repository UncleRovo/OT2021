package UtilsTests;

import data.Savefiles;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SavefilesTest {

    Savefiles s;

    @Before
    public void setUp() {
        s = new Savefiles();
        s.initFiles();
    }

    @Test
    public void deletionWorks() {
        s.deleteData();
        assertEquals(false, s.files[0].started);
        assertEquals(false, s.files[1].started);
        assertEquals(false, s.files[2].started);
    }

    @Test
    public void startSaveWorks() {
        s.deleteData();
        s.files[0].name = "jyrmy";
        s.createNew(s.files[0], 1);
        assertEquals("jyrmy", s.files[0].name);
    }
}

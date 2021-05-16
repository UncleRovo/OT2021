package LogicTests;

import data.Dialogue;
import org.junit.Test;
import static org.junit.Assert.*;

public class DialogueTest {

    Dialogue d;

    public DialogueTest() {
        d = new Dialogue();
    }

    @Test
    public void systemPicksRightLine() {
        assertEquals("_END_", d.lines[0][10]);
    }
}

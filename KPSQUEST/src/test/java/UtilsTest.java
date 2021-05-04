import static org.junit.Assert.assertEquals;
import org.junit.Test;
import data.Utils;

public class UtilsTest {
    
    @Test
    public void utlisReturnsCorrectPath() {
        String test = Utils.getFilePath("file:monalisa.jpg");
        assertEquals("file:monalisa.jpg", test);
    }
    
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void parameterMustContainSemicolon() {
        Utils.getFilePath("filemonalisa.jpg");
    }
}

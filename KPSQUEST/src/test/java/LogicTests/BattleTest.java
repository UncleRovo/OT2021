package LogicTests;

import data.Battle;
import data.Character;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mikkel
 */
public class BattleTest {

    Battle battle;

    public BattleTest() {
    }

    @Before
    public void setUp() {
        Character c1 = new Character("Hero", 10, 5, 50);
        Character c2 = new Character("Villain", 6, 5, 50);
        battle = new Battle(c1, c2, 50);
    }

    @Test
    public void getWinOptionReturnsCorrectChoice1() {
        String option = "rock";
        assertEquals("scissors", battle.getwinOption(option));
    }

    @Test
    public void getWinOptionReturnsCorrectChoice2() {
        String option = "scissor";
        assertEquals("paper", battle.getwinOption(option));
    }

    @Test
    public void getWinOptionReturnsCorrectChoice3() {
        String option = "paper";
        assertEquals("rock", battle.getwinOption(option));
    }

    @Test
    public void getloseOptionReturnsCorrectChoice1() {
        String option = "rock";
        assertEquals("paper", battle.getloseOption(option));
    }

    @Test
    public void getloseOptionReturnsCorrectChoice2() {
        String option = "paper";
        assertEquals("scissors", battle.getloseOption(option));
    }

    @Test
    public void getloseOptionReturnsCorrectChoice3() {
        String option = "scissor";
        assertEquals("rock", battle.getloseOption(option));
    }

    @Test
    public void getProbabilityReturnsTheRightValue() {
        int test = battle.probability();
        assertTrue(test == 71);
    }

    @Test
    public void charismaChanceReturnsTheRightValue() {
        int test = battle.charismaChance();
        assertTrue(test == 7);
    }
}

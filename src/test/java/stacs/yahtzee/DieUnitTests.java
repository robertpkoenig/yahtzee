package stacs.yahtzee;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;

public class DieUnitTests {

    @Test
    public void testDieInitialNegativeOne() {
        IDie die = new Die();
        assertEquals(-1, die.getCurrentFace());
    } 

    @Test
    public void testDieNotNegativeOneAfterRoll() {
        IDie die = new Die();
        die.roll();
        assertNotEquals(-1, die.getCurrentFace());
    }
}

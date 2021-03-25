package stacs.yahtzee;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.Test;

import stacs.yahtzee.implementation.Die;

public class DieUnitTests {

    @Test
    public void testDieInitialNegativeOne() {
        IDie die = new Die();
        // intentionally making this test fail for the purposes of testing the CI?CD workflow on github
        assertEquals(-1, die.getCurrentFace());
    } 

    @Test
    public void testDieNotNegativeOneAfterRoll() {
        IDie die = new Die();
        die.roll();
        assertNotEquals(-1, die.getCurrentFace());
    }
}

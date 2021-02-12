package stacs.yahtzee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;

import stacs.yahtzee.implementation.scoringoptions.*;

public class UpperScoringOptionUnitTests {
    
    IScoringOption ones;
    IScoringOption twos;
    IScoringOption threes;
    IScoringOption fours;
    IScoringOption fives;
    IScoringOption sixes;

    List<IDie> dice;

    @BeforeEach
    void setup() {
        ones = new OnesScoringOption();
        twos = new TwosScoringOption();
        threes = new ThreesScoringOption();
        fours = new FoursScoringOption();
        fives = new FivesScoringOption();
        sixes = new SixesScoringOption();

        dice = new ArrayList<>();
        for (int i = 0 ; i < 6 ; i++) {
            // add two die of each value to the die list to test
            for (int j = 0 ; j < 2 ; j++) {
               IDie newDie = Mockito.mock(IDie.class);
               Mockito.when(newDie.getCurrentFace()).thenReturn(i + 1);
               dice.add(newDie);
            }
       }
    }

    @Test
    void testOnes() {
        ones.recordScoreForThisOption(dice);
        assertEquals(2, ones.getScoreRecordedForThisOption());
    } 

    @Test
    void testTwos() {
        twos.recordScoreForThisOption(dice);
        assertEquals(4, twos.getScoreRecordedForThisOption());
    } 
 
    @Test
    void testThrees() {
        threes.recordScoreForThisOption(dice);
        assertEquals(6, threes.getScoreRecordedForThisOption());
    } 
  
    @Test
    void testFours() {
        fours.recordScoreForThisOption(dice);
        assertEquals(8, fours.getScoreRecordedForThisOption());
    } 
   
    @Test
    void testFives() {
        fives.recordScoreForThisOption(dice);
        assertEquals(10, fives.getScoreRecordedForThisOption());
    } 
    
    @Test
    void testSixes() {
        sixes.recordScoreForThisOption(dice);
        assertEquals(12, sixes.getScoreRecordedForThisOption());
    } 
          
    @Test
    void testExceptionWhenUsingSameOptionTwice() {
        sixes.recordScoreForThisOption(dice);
        assertThrows(IllegalStateException.class, () -> {
            sixes.recordScoreForThisOption(dice);
        });
    }
}

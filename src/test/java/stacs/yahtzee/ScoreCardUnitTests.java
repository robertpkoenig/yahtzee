package stacs.yahtzee;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;

public class ScoreCardUnitTests {

    IScoreCard scoreCard;
    List<IScoringOption> upperOptions;
    List<IScoringOption> lowerOptions;

    @BeforeEach
    void setup() {
        // create some mock scoring options
        // these should each return some score
        // then for each one I can perform the tests
        for (int i = 0 ; i < 6 ; i++) {
            IScoringOption newUpperOption = Mockito.mock(IScoringOption.class);
            Mockito.when(newUpperOption.getScoreRecordedForThisOption()).thenReturn(10);
            // if it's even it's labelled as 'used'
            Mockito.when(newUpperOption.hasBeenUsed()).thenReturn((i % 2 == 0));
            // if it's a multiple of four it's labelled as being satisfied by current dice
            Mockito.when(newUpperOption.currentDiceSatisfyThisScoringOption(new ArrayList<IDie>())).thenReturn((i % 4 == 0));
            upperOptions.add(newUpperOption); 
        }
        for (int i = 0 ; i < 7 ; i++) {
            IScoringOption newLowerOption = Mockito.mock(IScoringOption.class);
            Mockito.when(newLowerOption.getScoreRecordedForThisOption()).thenReturn(20);
            // if it's even it's labelled as 'used'
            Mockito.when(newLowerOption.hasBeenUsed()).thenReturn((i % 2 == 0));
            // if it's a multiple of four it's labelled as being satisfied by current dice
            Mockito.when(newLowerOption.currentDiceSatisfyThisScoringOption(new ArrayList<IDie>())).thenReturn((i % 4 == 0));
            upperOptions.add(newLowerOption); 
        }
        scoreCard = new ScoreCard();
        scoreCard.setLowerScoringOptions(lowerOptions);
        scoreCard.setUppperScoringOptions(upperOptions);
    }

    @Test
    void testTotalScore() {
        assertEquals(200, scoreCard.getTotalScore());
    }

    @Test
    void testGetUnusedScoringOptionsSatisfiedByCurrentDice() {
        assertEquals(2, scoreCard.getUnusedScoringOptionsSatisfiedByCurrentDice().size());
    }

    @Test
    void testGetHighestScoringOption() {
        assertEquals(upperOptions.get(4), scoreCard.getHighestScoringOption());
    }

    @Test
    void testGetLowerScore() {
        assertEquals(140, scoreCard.getLowerScore());
    }

    @Test
    void testGetUpperScoreWithoutBonus() {
        assertEquals(60, scoreCard.getUpperScoreWithoutBonus());
    }

    @Test
    void testGetUpperScoreWithBonusOne() {
        assertEquals(60, scoreCard.getUpperScoreWithBonus());
    } 

    @Test
    void testGetUpperScoreWithBonusTwo() {
        Mockito.when(upperOptions.get(0).getScoreRecordedForThisOption()).thenReturn(13);
        assertEquals(96, scoreCard.getUpperScoreWithBonus());
    }

}

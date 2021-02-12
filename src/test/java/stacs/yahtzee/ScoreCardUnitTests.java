package stacs.yahtzee;

import stacs.yahtzee.implementation.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;

public class ScoreCardUnitTests {

    ScoreCard scoreCard;
    List<IDie> dice;
    IScoringOption ones;
    IScoringOption twos;
    IScoringOption threes;
    IScoringOption fours;
    IScoringOption fives;
    IScoringOption sixes;
    IScoringOption threeOfKind;
    IScoringOption fourOfKind;
    IScoringOption fullHouse;
    IScoringOption smallStraight;
    IScoringOption largeStraight;
    IScoringOption chance;
    IScoringOption yahtzee;

    @BeforeEach
    void setup() {
        // create some mock scoring options
        // these should each return some score
        // then for each one I can perform the tests
        scoreCard = new ScoreCard();

        dice = new ArrayList<>();

        ones = Mockito.mock(IScoringOption.class);
        Mockito.when(ones.getScoreRecordedForThisOption()).thenReturn(1);
        scoreCard.setOnes(ones);

        twos = Mockito.mock(IScoringOption.class);
        Mockito.when(twos.getScoreRecordedForThisOption()).thenReturn(2);
        scoreCard.setTwos(twos);

        threes = Mockito.mock(IScoringOption.class);
        Mockito.when(threes.getScoreRecordedForThisOption()).thenReturn(-1);
        Mockito.when(threes.calculateScoreForThisOption(dice)).thenReturn(5);
        scoreCard.setThrees(threes);

        fours = Mockito.mock(IScoringOption.class);
        Mockito.when(fours.getScoreRecordedForThisOption()).thenReturn(3);
        scoreCard.setFours(fours);

        fives = Mockito.mock(IScoringOption.class);
        Mockito.when(fives.getScoreRecordedForThisOption()).thenReturn(-1);
        Mockito.when(fives.calculateScoreForThisOption(dice)).thenReturn(6);
        scoreCard.setFives(fives);

        sixes = Mockito.mock(IScoringOption.class);
        Mockito.when(sixes.getScoreRecordedForThisOption()).thenReturn(4);
        scoreCard.setSixes(sixes);

        threeOfKind = Mockito.mock(IScoringOption.class);
        Mockito.when(threeOfKind.getScoreRecordedForThisOption()).thenReturn(5);
        scoreCard.setThreeOfKind(threeOfKind);

        fourOfKind = Mockito.mock(IScoringOption.class);
        Mockito.when(fourOfKind.getScoreRecordedForThisOption()).thenReturn(6);
        scoreCard.setFourOfKind(fourOfKind);

        fullHouse = Mockito.mock(IScoringOption.class);
        Mockito.when(fullHouse.getScoreRecordedForThisOption()).thenReturn(7);
        scoreCard.setFullHouse(fullHouse);

        smallStraight = Mockito.mock(IScoringOption.class);
        Mockito.when(smallStraight.getScoreRecordedForThisOption()).thenReturn(8);
        scoreCard.setSmallStraight(smallStraight);

        largeStraight = Mockito.mock(IScoringOption.class);
        Mockito.when(largeStraight.getScoreRecordedForThisOption()).thenReturn(-1);
        Mockito.when(largeStraight.calculateScoreForThisOption(dice)).thenReturn(40);
        scoreCard.setLargeStraight(largeStraight);

        chance = Mockito.mock(IScoringOption.class);
        Mockito.when(chance.getScoreRecordedForThisOption()).thenReturn(9);
        scoreCard.setChance(chance);

        yahtzee = Mockito.mock(IScoringOption.class);
        Mockito.when(yahtzee.getScoreRecordedForThisOption()).thenReturn(-1);
        Mockito.when(yahtzee.calculateScoreForThisOption(dice)).thenReturn(0);
        scoreCard.setYahtzee(yahtzee);

    }

    @Test
    void testTotalScore() {
        // 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 + 9
        assertEquals(45, scoreCard.getTotalScore());
    }

    @Test
    void testGetUnusedScoringOptionsSatisfiedByCurrentDice() {
        assertEquals(3, scoreCard.getUnusedScoringOptionsSatisfiedByCurrentDice(dice).size());
    }

    @Test
    void testGetHighestScoringOption() {
        assertEquals(largeStraight, scoreCard.getHighestScoringOption(dice));
    }

    @Test
    void testGetLowerScore() {
        // 5 + 6 + 7 + 8 + 9
        assertEquals(35, scoreCard.getLowerScore());
    }

    @Test
    void testGetUpperScore() {
        // 1 + 2 + 3 + 4
        assertEquals(10, scoreCard.getUpperScoreWithoutBonus());
    } 

    @Test
    void testNoBonus() {
        assertEquals(0, scoreCard.getBonus());
    }

    @Test
    void testGetBonus() {
        Mockito.when(ones.getScoreRecordedForThisOption()).thenReturn(63);
        // bonus of 35 given if upper score is above 63
        // given the new mockito instruction above, score should be 10 * 5 + 13 = 63
        assertEquals(Constants.upperBonus, scoreCard.getBonus());
    }

}

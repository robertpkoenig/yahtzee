package stacs.yahtzee;

import stacs.yahtzee.implementation.Constants;
import stacs.yahtzee.implementation.ScoreCard;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;

public class ScoreCardUnitTests {

    IScoreCard scoreCard;
    IYahtzeeModel game;
    List<IDie> dice;
    IScoringOption onesScoringOption;
    IScoringOption twosScoringOption;
    IScoringOption threesScoringOption;
    IScoringOption foursScoringOption;
    IScoringOption fivesScoringOption;
    IScoringOption sixesScoringOption;
    IScoringOption threeOfKindScoringOption;
    IScoringOption fourOfKindScoringOption;
    IScoringOption fullHouseScoringOption;
    IScoringOption smallStraightScoringOption;
    IScoringOption largeStraightScoringOption;
    IScoringOption chanceScoringOption;
    IScoringOption yahtzeeScoringOption;

    @BeforeEach
    void setup() {
        // create some mock scoring options
        // these should each return some score
        // then for each one I can perform the tests
        scoreCard = new ScoreCard();

        dice = new ArrayList<>();

        game = Mockito.mock(IYahtzeeModel.class);
        // Mockito.when(game.getDice()).thenReturn(dice);
        scoreCard.setGame(game);

        onesScoringOption = Mockito.mock(IScoringOption.class);
        Mockito.when(onesScoringOption.getScoreRecordedForThisOption()).thenReturn(1);
        scoreCard.setOnesScoringOption(onesScoringOption);

        twosScoringOption = Mockito.mock(IScoringOption.class);
        Mockito.when(twosScoringOption.getScoreRecordedForThisOption()).thenReturn(2);
        scoreCard.setTwosScoringOption(twosScoringOption);

        threesScoringOption = Mockito.mock(IScoringOption.class);
        Mockito.when(threesScoringOption.getScoreRecordedForThisOption()).thenReturn(-1);
        Mockito.when(threesScoringOption.calculateScoreForThisOption(dice)).thenReturn(5);
        scoreCard.setThreesScoringOption(threesScoringOption);

        foursScoringOption = Mockito.mock(IScoringOption.class);
        Mockito.when(foursScoringOption.getScoreRecordedForThisOption()).thenReturn(3);
        scoreCard.setFoursScoringOption(foursScoringOption);

        fivesScoringOption = Mockito.mock(IScoringOption.class);
        Mockito.when(fivesScoringOption.getScoreRecordedForThisOption()).thenReturn(-1);
        Mockito.when(fivesScoringOption.calculateScoreForThisOption(dice)).thenReturn(6);
        scoreCard.setFivesScoringOption(fivesScoringOption);

        sixesScoringOption = Mockito.mock(IScoringOption.class);
        Mockito.when(sixesScoringOption.getScoreRecordedForThisOption()).thenReturn(4);
        scoreCard.setSixesScoringOption(sixesScoringOption);

        threeOfKindScoringOption = Mockito.mock(IScoringOption.class);
        Mockito.when(threeOfKindScoringOption.getScoreRecordedForThisOption()).thenReturn(5);
        scoreCard.setThreeOfKindScoringOption(threeOfKindScoringOption);

        fourOfKindScoringOption = Mockito.mock(IScoringOption.class);
        Mockito.when(fourOfKindScoringOption.getScoreRecordedForThisOption()).thenReturn(6);
        scoreCard.setFourOfKindScoringOption(fourOfKindScoringOption);

        fullHouseScoringOption = Mockito.mock(IScoringOption.class);
        Mockito.when(fullHouseScoringOption.getScoreRecordedForThisOption()).thenReturn(7);
        scoreCard.setFullHouseScoringOption(fullHouseScoringOption);

        smallStraightScoringOption = Mockito.mock(IScoringOption.class);
        Mockito.when(smallStraightScoringOption.getScoreRecordedForThisOption()).thenReturn(8);
        scoreCard.setSmallStraightScoringOption(smallStraightScoringOption);

        largeStraightScoringOption = Mockito.mock(IScoringOption.class);
        Mockito.when(largeStraightScoringOption.getScoreRecordedForThisOption()).thenReturn(-1);
        Mockito.when(largeStraightScoringOption.calculateScoreForThisOption(dice)).thenReturn(40);
        scoreCard.setLargeStraightScoringOption(largeStraightScoringOption);

        chanceScoringOption = Mockito.mock(IScoringOption.class);
        Mockito.when(chanceScoringOption.getScoreRecordedForThisOption()).thenReturn(9);
        scoreCard.setChanceScoringOption(chanceScoringOption);

        yahtzeeScoringOption = Mockito.mock(IScoringOption.class);
        Mockito.when(yahtzeeScoringOption.getScoreRecordedForThisOption()).thenReturn(-1);
        Mockito.when(yahtzeeScoringOption.calculateScoreForThisOption(dice)).thenReturn(0);
        scoreCard.setYahtzeeScoringOption(yahtzeeScoringOption);

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
        assertEquals(largeStraightScoringOption, scoreCard.getHighestScoringOption(dice));
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
        Mockito.when(onesScoringOption.getScoreRecordedForThisOption()).thenReturn(63);
        // bonus of 35 given if upper score is above 63
        // given the new mockito instruction above, score should be 10 * 5 + 13 = 63
        assertEquals(Constants.upperBonus, scoreCard.getBonus());
    }

}

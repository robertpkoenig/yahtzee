package stacs.yahtzee;

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
    List<IScoringOption> upperOptions;
    List<IScoringOption> lowerOptions;

    @BeforeEach
    void setup() {
        // create some mock scoring options
        // these should each return some score
        // then for each one I can perform the tests
        scoreCard = new ScoreCard();
        dice = new ArrayList<>();
        game = Mockito.mock(IYahtzeeModel.class);
        Mockito.when(game.getDice()).thenReturn(dice);
        scoreCard.setGame(game);

        upperOptions = new ArrayList<>();
        lowerOptions = new ArrayList<>();

        for (int i = 0 ; i < 6 ; i++) {
            IScoringOption newUpperOption = Mockito.mock(IScoringOption.class);
            Mockito.when(newUpperOption.getScoreRecordedForThisOption()).thenReturn(10);
            // if it's even it's labelled as 'used'
            Mockito.when(newUpperOption.hasBeenUsed()).thenReturn((i % 2 == 0));
            // if it's a multiple of four it's labelled as being satisfied by current dice
            Mockito.when(newUpperOption.isSatisfiedByDice(dice)).thenReturn((i == 4));
            Mockito.when(newUpperOption.isInUpperGroup()).thenReturn(true);
            upperOptions.add(newUpperOption); 
        }
        for (int i = 0 ; i < 7 ; i++) {
            IScoringOption newLowerOption = Mockito.mock(IScoringOption.class);
            Mockito.when(newLowerOption.getScoreRecordedForThisOption()).thenReturn(20);
            // if it's even it's labelled as 'used'
            Mockito.when(newLowerOption.hasBeenUsed()).thenReturn((i % 2 == 0));
            // if it's a multiple of four it's labelled as being satisfied by current dice
            Mockito.when(newLowerOption.isSatisfiedByDice(dice)).thenReturn((i == 4));
            Mockito.when(newLowerOption.isInUpperGroup()).thenReturn(false);
            lowerOptions.add(newLowerOption); 
        }
        List<IScoringOption> allOptions = new ArrayList<>();
        allOptions.addAll(upperOptions);
        allOptions.addAll(lowerOptions);
        scoreCard.setScoringOptions(allOptions);
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
        assertEquals(lowerOptions.get(4), scoreCard.getHighestScoringOption());
    }

    @Test
    void testGetLowerScore() {
        // score should be 20 * 7 = 140
        assertEquals(140, scoreCard.getLowerScore());
    }

    @Test
    void testGetUpperScoreWithBonusOne() {
        // upper score should 10 * 6 = 60
        assertEquals(60, scoreCard.getUpperScore());
    } 

    @Test
    void testGetUpperScoreWithBonusTwo() {
        Mockito.when(upperOptions.get(0).getScoreRecordedForThisOption()).thenReturn(13);
        // bonus of 35 given if upper score is above 63
        // score should therefore be 10 * 5 + 13 + 35 = 98
        assertEquals(98, scoreCard.getUpperScore());
    }

}

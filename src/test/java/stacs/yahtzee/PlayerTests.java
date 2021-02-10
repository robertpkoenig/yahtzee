package stacs.yahtzee;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;

/**
 * Tests of the Player model in the game of Yahtzee
 */
public class PlayerTests {

    IPlayer player;
    int playerNumber;
    IYahtzeeModel game;
    IScoreCard scoreCard;

    @BeforeEach
    void setup() {
        playerNumber = 3;
        game = Mockito.mock(IYahtzeeModel.class);
        Mockito.when(game.getDice()).thenReturn(null);
        this.player = new Player(playerNumber, game);
        this.player.setKeptDice(new ArrayList<>());
        scoreCard = Mockito.mock(IScoreCard.class);
        this.player.setScoreCard(scoreCard);
    }

    @Test
    void testPlayerOrder() {
        assertEquals(playerNumber, player.getPlayingOrder());
    } 

    @Test
    void testRollAllDice() {
        // roll 10 times, and each time, test to see if the new dice values
        // are different from the old ones. There is a small chance that the
        // values do not change after rolling, so rolling is done 10 times and
        // a difference on any iteration causes the test to finish and pass
        List<Integer> diceValues = new ArrayList<>();
        boolean differenceFoundAfterRoll = false;
        for (IDie die : game.getDice()) diceValues.add(die.getCurrentFace());
        for (int i = 0 ; i < 10 ; i++) {
            player.rollDice();
            List<Integer> newDiceValues = new ArrayList<>();
            for (IDie die : game.getDice()) newDiceValues.add(die.getCurrentFace());
            if (!diceValues.equals(newDiceValues)) {
                differenceFoundAfterRoll = true;
                break;
            }
        }
        assertFalse(differenceFoundAfterRoll);
    }

    @Test
    void testKeepOneDie() {
        player.getKeptDice().add(game.getDice().get(2));
        assertEquals(game.getDice().get(2), player.getKeptDice().get(0));
    }

    @Test
    void testKeepTwoDice() {
        player.getKeptDice().add(game.getDice().get(2));
        player.getKeptDice().add(game.getDice().get(3));
        assertEquals(2, game.getDice().size());
    }
    
    @Test
    void testKeepAndUnKeepTwoDice() {
        player.getKeptDice().add(game.getDice().get(2));
        player.getKeptDice().add(game.getDice().get(3));
        player.getKeptDice().remove(1);
        player.getKeptDice().remove(0);
        assertEquals(0, game.getDice().size());
    }

    @Test
    void testGetUnusedScoringOptionsBeforeRoll() {
        assertEquals(null, player.getUnusedScoringOptionsSatisfiedByCurrentDice());
    }
    
    // TODO test for the score after scoring, but not sure how, cause this relies
    // on the other classes to accurately report the score. This feels like it
    // needs an integration test

    // same goes for getting unused scoring options
    // same goes for get highest scoring option
    
    // TODO useScoringOptionAndEndTurn test
    
    // The endTurn method also feels like an integration test

    @Test
    void testGetScoreCard() {
        assertEquals(scoreCard, player.getScoreCard());
    }

    @Test
    void testNumberOfRollsCompletedAfterTwoRolls() {
        player.rollDice();
        player.rollDice();
        assertEquals(2, player.getNumberOfRollsCompleted());
    }

    @Test
    void testThatPlayerCanNotRollMoreThanThreeTimes() {
        player.rollDice();
        player.rollDice();
        player.rollDice();
        assertThrows(IllegalStateException.class, () -> {
            player.rollDice(); 
        });
    }
}

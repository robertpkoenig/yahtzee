package stacs.yahtzee;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;

import stacs.yahtzee.implementation.*;

/**
 * Tests of the Player model in the game of Yahtzee
 */
public class PlayerUnitTests {

    IPlayer player;
    int playerNumber;
    IYahtzeeModel game;
    IScoreCard scoreCard;
    IScoringOption scoringOption;

    @BeforeEach
    void setup() {
        playerNumber = 3;
        game = Mockito.mock(IYahtzeeModel.class);
        List<IDie> dice = new ArrayList<>();
        dice.add(Mockito.mock(IDie.class));
        dice.add(Mockito.mock(IDie.class));
        dice.add(Mockito.mock(IDie.class));
        dice.add(Mockito.mock(IDie.class));
        dice.add(Mockito.mock(IDie.class));
        Mockito.when(game.getDice()).thenReturn(dice);
        this.player = new Player(playerNumber, game);
        this.player.setKeptDice(new ArrayList<>());
        scoreCard = Mockito.mock(IScoreCard.class);
        this.player.setScoreCard(scoreCard);
        this.scoringOption = Mockito.mock(IScoringOption.class);
    }

    @Test
    void testPlayerOrder() {
        assertEquals(playerNumber, player.getPlayingOrder());
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
        assertEquals(2, player.getKeptDice().size());
    }

    @Test
    void testActiveDiceAfterKeepingTwoDice() {
        player.getKeptDice().add(game.getDice().get(2));
        player.getKeptDice().add(game.getDice().get(3));
        assertEquals(3, player.getActiveDice().size());
    }
    
    @Test
    void testKeepAndUnKeepTwoDice() {
        player.getKeptDice().add(game.getDice().get(2));
        player.getKeptDice().add(game.getDice().get(3));
        player.getKeptDice().remove(1);
        player.getKeptDice().remove(0);
        assertEquals(0, player.getKeptDice().size());
    }

    @Test
    void testEndTurnResetsState() {
        player.getKeptDice().add(game.getDice().get(2));
        player.rollDice(); 
        player.useScoringOptionAndEndTurn(scoringOption, null);
        assertEquals(0, player.getKeptDice().size());
        assertEquals(0, player.getNumberOfRollsCompleted());
    }
    
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

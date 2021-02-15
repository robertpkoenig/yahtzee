package stacs.yahtzee;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.mockito.*;

import stacs.yahtzee.implementation.*;
import stacs.yahtzee.implementation.scoringoptions.*;

public class FixedDieIntegrationTest {

    static IYahtzeeModel game;
    static IPlayer playerOne;
    static IPlayer playerTwo;
    static IPlayer playerThree;

    static Random randomGenerator;

    @BeforeAll
    static void setup() {
        game = new YahtzeeModel();

        // test die for testing scenarios with controlled die values
        for (int i = 0 ; i < Constants.numberOfDice ; i++) {
            IDie testDie = Mockito.mock(IDie.class);
            game.addDie(testDie);
        }

        game.addPlayer(playerOne = new Player());
        game.addPlayer(playerTwo = new Player());
        game.addPlayer(playerThree = new Player());
        game.setActivePlayer(0);

        randomGenerator = new Random();

        // set score cards for each player
        for (IPlayer player : game.getPlayerList()) {
            ScoreCard scoreCard = new ScoreCard(player);
            scoreCard.setOnes(new OnesScoringOption());
            scoreCard.setTwos(new TwosScoringOption());
            scoreCard.setThrees(new ThreesScoringOption());
            scoreCard.setFours(new FoursScoringOption());
            scoreCard.setFives(new FivesScoringOption());
            scoreCard.setSixes(new SixesScoringOption());
            scoreCard.setThreeOfKind(new ThreeOfKindScoringOption());
            scoreCard.setFourOfKind(new FourOfKindScoringOption());
            scoreCard.setFullHouse(new FullHouseScoringOption());
            scoreCard.setSmallStraight(new SmallStraightScoringOption());
            scoreCard.setLargeStraight(new LargeStraightScoringOption());
            scoreCard.setChance(new ChanceScoringOption());
            scoreCard.setYahtzee(new YahtzeeScoringOption());
            player.setScoreCard(scoreCard);
        }
    }

    @Test
    void testFullGameIntegration() {
        // no player is a high scorer at the start of the game
        assertEquals(0, game.getPlayersWithHighestScore().size());

        // playerOne's options are recorded correctly
        Mockito.when(game.getDice().get(0).getCurrentFace()).thenReturn(1);
        Mockito.when(game.getDice().get(1).getCurrentFace()).thenReturn(1);
        Mockito.when(game.getDice().get(2).getCurrentFace()).thenReturn(2);
        Mockito.when(game.getDice().get(3).getCurrentFace()).thenReturn(2);
        Mockito.when(game.getDice().get(4).getCurrentFace()).thenReturn(2);
        assertEquals(5,
            game.getActivePlayer().getScoreCard().
            getUnusedScoringOptionsSatisfiedByCurrentDice().size());

        // playerOne's highest scoring option is given correctly
        assertEquals(playerOne.getScoreCard().getFullHouse(),
            playerOne.getScoreCard().getHighestScoringOption());
        
        // dummy roll to ensure the 
        // playerOne can register their score and end turn
        IScoringOption fullHouse = playerOne.getScoreCard().getFullHouse();
        game.getActivePlayer().useScoringOptionAndEndTurn(fullHouse);
        assertEquals(25, playerOne.getScoreCard().getTotalScore());

        // playerOne has the highest score
        assertEquals(playerOne, game.getPlayersWithHighestScore().get(0));

        // playerTwo is now the active player
        assertEquals(playerTwo, game.getActivePlayer());

        // non-active player rolling dice results in exception
        assertThrows(IllegalStateException.class, () -> {
            playerOne.rollDice();
        });

        // playerTwo's options are correctly recorded after throwing yahtzee
        Mockito.when(game.getDice().get(0).getCurrentFace()).thenReturn(1);
        Mockito.when(game.getDice().get(1).getCurrentFace()).thenReturn(1);
        Mockito.when(game.getDice().get(2).getCurrentFace()).thenReturn(1);
        Mockito.when(game.getDice().get(3).getCurrentFace()).thenReturn(1);
        Mockito.when(game.getDice().get(4).getCurrentFace()).thenReturn(1);
        assertEquals(5, 
            game.getActivePlayer().getScoreCard().
            getUnusedScoringOptionsSatisfiedByCurrentDice().size());
        
        // playerTwo highest scoring option is yahtzee
        assertEquals(playerTwo.getScoreCard().getYahtzee(),
            playerTwo.getScoreCard().getHighestScoringOption());
        
        // playerTwo has score of 50 after using yahtzee
        game.getActivePlayer().useScoringOptionAndEndTurn(
            playerTwo.getScoreCard().getYahtzee());
        assertEquals(50, playerTwo.getScoreCard().getTotalScore());
        
        // playerTwo has the highest score
        assertEquals(playerTwo, game.getPlayersWithHighestScore().get(0));
        assertEquals(1, game.getPlayersWithHighestScore().size());

        // playerThree rolls twos as highest option
        Mockito.when(game.getDice().get(0).getCurrentFace()).thenReturn(1);
        Mockito.when(game.getDice().get(1).getCurrentFace()).thenReturn(2);
        Mockito.when(game.getDice().get(2).getCurrentFace()).thenReturn(2);
        Mockito.when(game.getDice().get(3).getCurrentFace()).thenReturn(3);
        Mockito.when(game.getDice().get(4).getCurrentFace()).thenReturn(1);
        assertEquals(playerThree.getScoreCard().getChance(),
            game.getActivePlayer().getScoreCard().getHighestScoringOption());
        
        // playerThree uses twos
        game.getActivePlayer().useScoringOptionAndEndTurn(
            playerThree.getScoreCard().getTwos());
        
        // playerTwo still in the lead
        assertEquals(playerTwo, game.getPlayersWithHighestScore().get(0));
        
        // playerOne is now the active player
        assertEquals(playerOne, game.getActivePlayer());

        // one round has been completed
        assertEquals(1, game.getCurrentRound());

        // playerOne can no longer use their fullHouse
        Mockito.when(game.getDice().get(0).getCurrentFace()).thenReturn(1);
        Mockito.when(game.getDice().get(1).getCurrentFace()).thenReturn(1);
        Mockito.when(game.getDice().get(2).getCurrentFace()).thenReturn(2);
        Mockito.when(game.getDice().get(3).getCurrentFace()).thenReturn(2);
        Mockito.when(game.getDice().get(4).getCurrentFace()).thenReturn(2);
        List<IScoringOption> playerOneScoringOptions = game.getActivePlayer().getScoreCard().
            getUnusedScoringOptionsSatisfiedByCurrentDice();
        assertEquals(4,
            game.getActivePlayer().getScoreCard().
            getUnusedScoringOptionsSatisfiedByCurrentDice().size());
        assertFalse(playerOneScoringOptions.contains(playerOne.getScoreCard().getFullHouse()));

        // trying to use the fullHouse throws exception
        IScoringOption playerOneFullHouse = playerOne.getScoreCard().getFullHouse();
        assertThrows(IllegalStateException.class, () -> {
            playerOne.useScoringOptionAndEndTurn(playerOneFullHouse);
        });

        // playerOne can not roll dice more than three times
        playerOne.rollDice();
        playerOne.rollDice();
        playerOne.rollDice();
        assertThrows(IllegalStateException.class, () -> {
            playerOne.rollDice();
        });

        // player one uses the twos option and player 2 is still in the lead
        playerOne.useScoringOptionAndEndTurn(playerOne.getScoreCard().getTwos());
        assertEquals(playerTwo, game.getPlayersWithHighestScore().get(0));

        // throw exception if playerTwo tries to end turn without recording a score
        playerTwo.rollDice();
        assertThrows(IllegalArgumentException.class, () -> {
            playerTwo.endTurn();
        });
    }

}

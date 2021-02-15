package stacs.yahtzee;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import stacs.yahtzee.implementation.*;
import stacs.yahtzee.implementation.scoringoptions.*;

public class RandomDieIntegrationTest {

    static IYahtzeeModel game;
    static IPlayer playerOne;
    static IPlayer playerTwo;
    static IPlayer playerThree;

    static Random randomGenerator;

    @BeforeAll
    static void setup() {
        game = new YahtzeeModel();


        List<IDie> dice = new ArrayList<>();
        for (int i = 0 ; i < Constants.numberOfDice ; i++) {
            dice.add(new Die());
        }
        game.setDice(dice);

        List<IPlayer> players = new ArrayList<>();
        players.add(playerOne = new Player());
        players.add(playerTwo = new Player());
        players.add(playerThree = new Player());
        game.setPlayers(players);
        game.setActivePlayer(game.getPlayerList().get(0));

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

        // iterate through each round and choose random actions
        while (!game.isDone()) {
            IPlayer activePlayer = game.getActivePlayer();
            activePlayer.rollDice();
            int randomDiceIndex = randomGenerator.nextInt(5);
            activePlayer.addKeptDie(game.getDice().get(randomDiceIndex));
            activePlayer.rollDice();
            randomDiceIndex = randomGenerator.nextInt(5);
            activePlayer.addKeptDie(game.getDice().get(randomDiceIndex));
            List<IScoringOption> possibleScoringOptions =
                activePlayer.getScoreCard().getUnusedScoringOptions();
            int randomScoringOptionIndex = randomGenerator.nextInt(possibleScoringOptions.size());
            IScoringOption selectedScoringOption = possibleScoringOptions.get(randomScoringOptionIndex);
            activePlayer.useScoringOptionAndEndTurn(selectedScoringOption);
        }

        assertTrue(game.getWinners().size() > 0);
        assertTrue(game.getWinners().get(0).getScoreCard().getTotalScore() > 0);
        assertTrue(playerOne.getScoreCard().getTotalScore() > 0);
        assertTrue(playerTwo.getScoreCard().getTotalScore() > 0);
        assertTrue(playerThree.getScoreCard().getTotalScore() > 0);
        assertEquals(12, game.getCurrentRound());
    }

}

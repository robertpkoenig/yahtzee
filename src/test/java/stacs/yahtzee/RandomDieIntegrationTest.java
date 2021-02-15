package stacs.yahtzee;

import static org.junit.Assert.assertTrue;

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
        for (int i = 0 ; i < Constants.numberOfDice ; i++) {
            game.addDie(new Die());
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

        // iterate through each round and choose random actions
        while (!game.isDone()) {
            IPlayer activePlayer = game.getActivePlayer();
            activePlayer.rollDice();
            int randomDiceIndex = randomGenerator.nextInt(5);
            activePlayer.addKeptDie(game.getDice().get(randomDiceIndex));
            activePlayer.rollDice();
            randomDiceIndex = randomGenerator.nextInt(5);
            activePlayer.addKeptDie(game.getDice().get(randomDiceIndex));
            // IScoringOption highestScoringOption = activePlayer.getScoreCard().getHighestScoringOption();
            // get the list of possible scoring options and pick one at random
            List<IScoringOption> possibleScoringOptions =
                activePlayer.getScoreCard().getAllUnusedScoringOptions();
            int randomScoringOptionIndex = randomGenerator.nextInt(possibleScoringOptions.size());
            IScoringOption selectedScoringOption = possibleScoringOptions.get(randomScoringOptionIndex);
            activePlayer.useScoringOptionAndEndTurn(selectedScoringOption);
        }

        assertTrue(game.getWinners().size() > 0);
    }

}

package stacs.yahtzee;

import java.util.List;

/**
 * Model for a player's score card in Yahtzee
 */
public interface IScoreCard {

    /**
     * Returns a list of all scoring options the person has not used, even if
     * they are not satisfied by current dice.
     */
    List<IScoringOption> getUnusedScoringOptions();

    /**
     * @return A list of scoring options that are satisfied by the
     * current dice and which have not already used
     */
    List<IScoringOption> getUnusedScoringOptionsSatisfiedByCurrentDice();

    /**
     * Returns all the scoring options on this score card for which
     * a score has been recorded. This is used to test that
     * the player has registered a score for each round.
     */
    List<IScoringOption> getUsedScoringOptions();

    /**
     * Returns the available scoring option that has the highest score given
     * the current dice values.
     */
    IScoringOption getHighestScoringOption();

    /**
     * Returns the total score for each used scoring option on this score card, 
     * including the optional bonus on the upper section.
     */
    int getTotalScore();

    /**
     * Returns the upper score without the bonus that applies when the upper
     * score is above 63.
     */
    int getUpperScoreWithoutBonus();

    /**
     * Returns the value of the bonus applied to the upper score if upper score
     * is above 63. The bonus is 35.
     * @return 35 if the upper score is 63 or greater, and zero otherwise
     */
    int getBonus();

    /**
     * Gets the score for the 'lower' scoring options.
     */
    int getLowerScore();

    public IScoringOption getOnes();

    void setOnes(IScoringOption ones);

    IScoringOption getTwos();

    void setTwos(IScoringOption twos);

    IScoringOption getThrees();

    void setThrees(IScoringOption threes);

    IScoringOption getFours();

    void setFours(IScoringOption fours);

    IScoringOption getFives();

    void setFives(IScoringOption fives);

    IScoringOption getSixes();
        
    void setSixes(IScoringOption sixes);

    IScoringOption getThreeOfKind();

    void setThreeOfKind(IScoringOption threeOfKind);

    IScoringOption getFourOfKind();

    void setFourOfKind(IScoringOption fourOfKind);

    IScoringOption getFullHouse();

    void setFullHouse(IScoringOption fullHouse);

    IScoringOption getSmallStraight();

    void setSmallStraight(IScoringOption smallStraight);

    IScoringOption getLargeStraight();

    void setLargeStraight(IScoringOption largeStraight);

    IScoringOption getChance();

    void setChance(IScoringOption chance);

    IScoringOption getYahtzee();

    void setYahtzee(IScoringOption yahtzee);
}

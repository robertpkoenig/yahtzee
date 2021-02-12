package stacs.yahtzee;

import java.util.List;

/**
 * Model for a player's score card in Yahtzee
 */
public interface IScoreCard {

    /**
     * Returns the total score for each scoring option on this score card, 
     * including the optional bonus on the upper section.
     */
    int getTotalScore();

    /**
     * This includes a bonus score if the sum of all upper scoring options is greater than 63.
     */
    int getUpperScoreWithoutBonus();

    /**
     * Returns the value of the bonus applied to the upper score if upper score
     * is above 63. The bonus is 35.
     * @return
     */
    int getBonus();

    /**
     * Gets the score for the 'lower' scoring options. In our implementation 
     * @return
     */
    int getLowerScore();

    /**
     * Gets the scoring options this player has not already registered a score for,
     * and which are satisfied by their current dice roll.
     * @return A list of scoring options that are fulfilled with the user's
     * current dice and which they have not already used
     */
    List<IScoringOption> getUnusedScoringOptionsSatisfiedByCurrentDice(List<IDie> dice);

    /**
     * Returns the available scoring option that has the highest score given
     * the current dice values.
     * @return The highest scoring option.
     */
    IScoringOption getHighestScoringOption(List<IDie> dice);

    /**
     * Returns the list of upper scoring options
     */
    List<IScoringOption> getUpperScoringOptions();

    /**
     * Returns the list of lower scoring options
     */
    List<IScoringOption> getLowerScoringOptions();

    /**
     * Sets the game object that this score card is a part of.
     */
    void setGame(IYahtzeeModel game);

    /**
     * Sets the 'ones' scoring option field
     */
    void setOnesScoringOption(IScoringOption onesScoringOption);

    /**
     * Sets the 'twos' scoring option field
     */
    void setTwosScoringOption(IScoringOption twosScoringOption);

    /**
     * Sets the 'threes' scoring option field
     */
    void setThreesScoringOption(IScoringOption threesScoringOption);

    /**
     * Sets the 'fours' scoring option field
     */
    void setFoursScoringOption(IScoringOption foursScoringOption);

    /**
     * Sets the 'fives' scoring option field
     */
    void setFivesScoringOption(IScoringOption fivesScoringOption);

    /**
     * Sets the 'sixes' scoring option field
     */
    void setSixesScoringOption(IScoringOption sixesScoringOption);

    /**
     * Sets the 'three of a kind' scoring option field
     */
    void setThreeOfKindScoringOption(IScoringOption threeOfKindScoringOption);

    /**
     * Sets the 'four of a kind' scoring option field
     */
    void setFourOfKindScoringOption(IScoringOption fourOfKindScoringOption);

    /**
     * Sets the 'full house' scoring option field
     */
    void setFullHouseScoringOption(IScoringOption fullHouseScoringOption);

    /**
     * Sets the 'small straight' scoring option field
     */
    void setSmallStraightScoringOption(IScoringOption smallStraightScoringOption);

    /**
     * Sets the 'large straight' scoring option field
     */
    void setLargeStraightScoringOption(IScoringOption largeStraightScoringOption);

    /**
     * Sets the 'chance' scoring option field
     */
    void setChanceScoringOption(IScoringOption chanceScoringOption);
    
    /**
     * Sets the 'yahtzee' scoring option field
     */
    void setYahtzeeScoringOption(IScoringOption yahtzeeScoringOption);

}

package stacs.yahtzee;

import stacs.yahtzee.scoringoptions.IScoringOption;

import java.util.List;

/**
 * Model for a player's score card in Yahtzee
 */
public interface IScoreCard {
    
    /**
     * Sets the game object that this score card is a part of.
     */
    void setGame(IYahtzeeModel game);

    /**
     * Creates the scoring option objects used in this game and
     * sets them as properties of this score card object. I create this
     * to avoid creating a setter for each scoring option. Feedback on 
     * my design around the scoring options would be appreciated.
     */
    void setAllScoringOptions();

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
    List<IScoringOption> getUnusedScoringOptionsSatisfiedByCurrentDice();

    /**
     * Returns the available scoring option that has the highest score given
     * the current dice values.
     * @return The highest scoring option.
     */
    IScoringOption getHighestScoringOption();

    /**
     * Returns the list of upper scoring options
     */
    List<IScoringOption> getUpperScoringOptions();

    /**
     * Returns the list of lower scoring options
     */
    List<IScoringOption> getLowerScoringOptions();
}

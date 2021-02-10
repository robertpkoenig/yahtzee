package stacs.yahtzee;

import java.util.List;

/**
 * Model for a player's score card in Yahtzee
 */
public interface IScoreCard {
    
    /**
     * 
     * @param scoringOptions
     */
    void setUppperScoringOptions(List<IScoringOption> scoringOptions);

    /**
     * Set the lower scoring options
     * @param scoringOptions The scoring options to be added to the lower set
     */
    void setLowerScoringOptions(List<IScoringOption> scoringOptions);

    /**
     * Returns the total score for each scoring option on this score card.
     */
    int getTotalScore();

    /**
     * Returns the score for the 'upper' scoring options. This excludes a possible bonus value.
     */
    int getUpperScoreWithoutBonus();

    /**
     * This includes a bonus score if the sum of all upper scoring options is greater than 63.
     */
    int getUpperScoreWithBonus();

    /**
     * Returns the bonus score for the 'upper' scoring options. If the score for other 'upper'
     * scoring options are above 63, this value is 35. Otherwise it is 0.
     */
    int getUpperScoreBonus();

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
    List<IPlayerScoringOption> getUnusedScoringOptionsSatisfiedByCurrentDice();

    /**
     * Returns the available scoring option that has the highest score given
     * the current dice values.
     * @return The highest scoring option.
     */
    IPlayerScoringOption getHighestScoringOption();

}

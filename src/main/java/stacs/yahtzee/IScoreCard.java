package stacs.yahtzee;

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
     * 
     * @param scoringOptions
     */
    void setScoringOptions(List<IScoringOption> scoringOptions);

    /**
     * Returns the total score for each scoring option on this score card.
     */
    int getTotalScore();

    /**
     * This includes a bonus score if the sum of all upper scoring options is greater than 63.
     */
    int getUpperScore();

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

}

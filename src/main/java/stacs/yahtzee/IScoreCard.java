package stacs.yahtzee;

import java.util.List;

/**
 * Model for a player's score card in Yahtzee
 */
public interface IScoreCard {
    
    /**
     * Returns the total score for each scoring option on this score card.
     */
    int getTotalScore();

    /**
     * Gets the score for the 'upper' scoring options. This includes a bonus score
     * if the sum of all upper scoring options is greater than 63
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
    List<IPlayerScoringOption> getUnusedScoringOptionsSatisfiedByCurrentDice();

    /**
     * Returns the available scoring option that has the highest score given
     * the current dice values.
     * @return The highest scoring option.
     */
    IPlayerScoringOption getHighestScoringOption();

}
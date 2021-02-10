package stacs.yahtzee;

import java.util.List;

/**
 * Model for a scoring option in a specific player's score card in the game of Yahtzee
 */
public interface IPlayerScoringOption {

    /**
     * Returns the score recorded for this scoring option
     */
    int getScoreRecordedForThisOption();

    /**
     * Calculates the score achieved for this scoring option given a
     * set of dice, records this for the current scoring option, 
     * and marks this scoring option as 'used'. If this operation is
     * unsuccesful because the this scoring option has already been used
     * this method returns false. Otherwise, it returns true.
     * @return
     */
    boolean useThisScoringOption(List<IDice> dice) throws IllegalArgumentException;

    /**
     * Calculates the score achieved for this scoring option given
     * the current dice values. If this scoring option has already been used,
     * this method throws an illegal argument exception.
     * @return The score achieved for this option given the current dice
     */
    int calculatePotentialScoreForThisOption(List<IDice> dice) throws IllegalArgumentException;

    /**
     * Returns true if this option has already been used. Returns false otherwise.
     */
    boolean hasBeenUsed();

}
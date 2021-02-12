package stacs.yahtzee;

import java.util.List;

/**
 * Model for a scoring option in Yahtzee. Each scoring option
 * has it's own implementing class.
 */
public interface IScoringOption {

    /**
     * Returns the score that the owner player has recorded for this option
     */
    int getScoreRecordedForThisOption();

    /**
     * Calculates the score for this option given a dice roll
     */
    int calculateScoreForThisOption(List<IDie> dice);

    /**
     * Calculates the score for using this option given a set of dice.
     * Sets the recorded score for this option accordingly. Sets the 
     * hasBeenUsed flag to true. If this option has already been used,
     * this returns false and throws an exception.
     * @param dice
     */
    void recordScoreForThisOption(List<IDie> dice);
}

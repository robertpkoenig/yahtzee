package stacs.yahtzee;

import java.util.List;

/**
 * Interface for a scoring option in Yahtzee. Each scoring option
 * has it's own implementing class.
 */
public interface IScoringOption {

    /**
     * Returns the score that the owner player has recorded for this option.
     * If the owner player has not yet recorded a score for this option,
     * it returns negative one.
     */
    int getScoreRecordedForThisOption();

    /**
     * Calculates the score for this option given a set of dice
     */
    int calculateScoreForThisOption(List<IDie> dice);

    /**
     * Records a score for this option based on the value evaluated
     * int the 'calculateScoreForThisOption' method.
     * If this option has already been used, this returns false and
     * throws an exception.
     */
    void recordScoreForThisOption(List<IDie> dice);
}

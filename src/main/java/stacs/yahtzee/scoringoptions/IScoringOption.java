package stacs.yahtzee.scoringoptions;

import stacs.yahtzee.IDie;

import java.util.List;

/**
 * Model for a scoring option in Yahtzee. This is wrapped within a 'PlayerScoringOption'
 * before being added to a player's scorecard
 */
public interface IScoringOption {

    /**
     * Returns a string of the current scoring option's name
     */
    String getName();

    /**
     * Returns true if the current dice values satisfy this scoring option,
     * and false otherwise.
     * @param dice The relevant game's dice
     */
    boolean isSatisfiedByDice(List<IDie> dice);

    /**
     * Returns the score that the owner player has recorded for this option
     */
    int getScoreRecordedForThisOption();

    /**
     * Returns true if this scoring option has been used
     */
    boolean hasBeenUsed();

    /**
     * Returns true if this is in the upper group of scoring
     * @return
     */
    boolean isInUpperGroup();

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
    boolean useThisScoringOption(List<IDie> dice);
}

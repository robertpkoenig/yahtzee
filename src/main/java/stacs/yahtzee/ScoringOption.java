package stacs.yahtzee;

import java.util.List;

public interface ScoringOption {

    /**
     * Returns a string of the current scoring option's name
     */
    String getName();

    /**
     * Returns true if the current dice values satisfy this scoring option,
     * and false otherwise.
     * @param dice The relevant game's dice
     */
    boolean doCurrentDiceSatisfyThisScoringOption(List<Dice> dice);
}

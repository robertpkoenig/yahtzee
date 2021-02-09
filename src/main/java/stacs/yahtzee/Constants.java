package stacs.yahtzee;

/**
 * Defines the constants for a game of Yahtzee
 */
public abstract class Constants {
    private static int numberOfDice = 5;
    private static int numberOfRounds = 13;

    /** 
     * @return int
     */
    static int getNumberOfDice() {
        return numberOfDice;
    }
    
    /** 
     * @return int
     */
    static int getNumberOfRounds() {
        return numberOfRounds;
    }
}

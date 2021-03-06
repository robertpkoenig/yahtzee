package stacs.yahtzee;

/**
 * Model for a six sided die
 */
public interface IDie {

    /**
     * Returns the value of the current die face
     */
    int getCurrentFace();

    /**
     * Assigns this die a fresh random value
     */
    void roll();
}

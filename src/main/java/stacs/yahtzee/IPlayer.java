package stacs.yahtzee;

import java.util.List;

/**
 * Model for the player in a game of Yahtzee
 */
public interface IPlayer {

    /**
     * Returns the current player's playing order in the game
     */
    int getPlayingOrder();

    /**
     * Returns the number of rolls thise player has completed in the current
     * round.
     */
    int getNumberOfRollsCompleted();

     /**
     * This method calls the 'roll' method on a list of dice objects.
     * The list of dice objects is the game's dice objects, less the dice
     * the player may have elected to 'keep' after the previous role. If it is
     * their first roll of the round, they must roll all the dice.
     */
    void rollDice();

    /**
     * Sets the dice that this player has elected to 'keep'. The intended use is
     * that the getter is called first to get this player's kept dice, then the caller
     * removes or adds a new set of dice and sends that new list to this method.
     * @param keptDie The list of dice the player has elected not to roll.
     */
    void setKeptDice(List<IDie> keptDice);

    /**
     * Returns the list of dice the player has elected to 'keep' and not roll
     */
    List<IDie> getKeptDice();

    /**
     * Returns the game's dice, excluding those the user has elected to keep.
     * @return
     */
    List<IDie> getActiveDice();

    /**
     * Gets this player's scorecard object.
     */
    IScoreCard getScoreCard();

    /**
     * Sets this player's score card
     */
    void setScoreCard(IScoreCard scoreCard);

    /**
     * Called by the player when they wish to mark their scorecard for a particular
     * scoring option. This must be called exactly once during each of this player's
     * turn, and when it is called, it ends their turn.
     * @param scoringOption The scoring option which this person will take. The score
     * will be calculated automatically and registered in the scorecard card object 
     * by the "player score option" object.
     * @return Returns true if the operation is succesful. Returns false if the player
     * attempted to use a scoring option they have already used.
     */
    boolean useScoringOptionAndEndTurn(IScoringOption scoringOption);

}

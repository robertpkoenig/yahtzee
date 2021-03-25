package stacs.yahtzee;

import java.util.List;

/**
 * Model for the player in a game of Yahtzee
 */
public interface IPlayer {

    /**
     * Sets this player's playing order in a game
     */
    void setPlayingOrder(int playingOrder);
    
    /**
     * Returns the current player's playing order in the game
     */
    int getPlayingOrder();

    /**
     * Sets the game this player is a part of.
     */
    void setGame(IYahtzeeModel game);

    /**
     * Returns the game this player is part of.
     */
    IYahtzeeModel getGame();

    /**
     * Sets this player's score card
     */
    void setScoreCard(IScoreCard scoreCard);

    /**
     * Gets this player's scorecard object.
     */
    IScoreCard getScoreCard();

    /**
     * This method calls the 'roll' method on a list of dice objects.
     * The list of dice objects is the game's dice objects, less the dice
     * the player has elected to 'keep' after the previous role. If it is
     * their first roll of the round, they must roll all the dice.
     */
    void rollDice();

    /**
     * Adds a die to the list of 'kept' die which the user will not roll.
     * @param dieToBeKept The die that the player will add to their 'kept' list.
     */
    void addKeptDie(IDie dieToBeKept);

    /**
     * Removes a die from the list of 'kept' die.
     * @param dieToBeRemoved The die that will be removed 
     */
    void removeKeptDie(IDie dieToBeRemoved);

    /**
     * Returns the list of dice the player has elected to 'keep' and not roll
     */
    List<IDie> getKeptDice();

    /**
     * Returns the game's dice, excluding those the user has elected to keep.
     */
    List<IDie> getActiveDice();

    /**
     * Returns the number of rolls this player has completed in the current
     * round.
     */
    int getNumberOfRollsCompleted();

    /**
     * Called by the player when they wish to mark their scorecard for a particular
     * scoring option. This must be called exactly once during each of this player's
     * turn, and when it is called, it ends their turn.
     * @param scoringOption The scoring option which this person will take. The score
     * will be calculated automatically and registered accordingly
     */
    void useScoringOptionAndEndTurn(IScoringOption scoringOption);

}

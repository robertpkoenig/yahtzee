package stacs.yahtzee;

import java.util.List;

/**
 * This interface defines the model for a game of Yahtzee. It relies on numerous
 * other classes, such as 'Player' and 'Dice'. The rules of Yahtzee as it
 * implemented here, with some exceptions, can be found at:
 * https://en.wikipedia.org/wiki/Yahtzee
 */
public interface IYahtzeeModel {

    /**
     * Creates a specified number player objects and returns them in a list
     * @return List of player objects created
     */
    List<IPlayer> createPlayers(int numPlayers);

    /**
     * Create new 'player' object for each player, and add to the game's
     * list of players.
     * @param numPlayers The number of players specified as a parameter
     * in the constructor in the game object
     */
    void addPlayersToGame(List<IPlayer> newPlayers);

    /**
     * Returns this game's list of players
     */
    List<IPlayer> getPlayerList();

    /**
     * Creates dice objects which are then added to the game. The number of dice
     * objects created here is dictated by the 'numberOfDice' field in the 'Constants'
     * class.
     * @return The list of dice that this method has created
     */
    List<IDice> createDice();

    /**
     * Create new 'dice' objects and add the game's list of dice.
     * The number of dice is specified in the constants file.
     */
    void addDiceToGame(List<IDice> dice);

    /**
     * Returns the dice that belong to the current game
     */
    List<IDice> getDice();

    /**
     * Returns the player who's turn it is.
     */
    IPlayer getActivePlayer();
    
    /**
     * Returns the number of the current round.
     */
    int getCurrentRound();

    /**
     * Returns the total number of rounds in the game.
     */
    int getTotalRounds();

    /**
     * Called by a player object when the player finishes their turn.
     * This allows the model to assign the next player as the active player,
     * and if necessary it increments the round or finishes the game.
     */
    void registerTurnFinished(IPlayer playerFinishingTurn) throws IllegalArgumentException;

    /**
     * Sets the active player for this game
     * @param playerOrder The playing order of the player to be made the active player
     */
    void setActivePlayer(int newActivePlayerOrder);

    /**
     * This method returns the player with highest score. When the game
     * is finished, the person with the highest score is the winner. If
     * no player yet has a score, it returns null.
     */
    IPlayer getPlayerWithHighestScore();

    /**
     * Method to check if the game is finished. This always returns true
     * after the last player's turn in the last round.
     * @return True if the game is finished, otherwise false
     */
    boolean isGameFinished();

    /**
     * Returns a new game in the starting condition with the same number
     * of players.
     * @return The new game in the starting condition
     */
    IYahtzeeModel reStart();

}

package stacs.yahtzee;

public interface Game {

    /**
     * Create new 'player' object for each player, and add to the game's
     * list of players.
     * @param numPlayers The number of players specified as a parameter
     * in the constructor in the game object
     */
    void addPlayersToGame(int numPlayers);

    /**
     * Create new 'dice' objects and add the game's list of dice.
     * The number of dice is specified in the constants file.
     */
    void addDiceToGame();

    /**
     * Returns the player who's turn it is.
     */
    Player getActivePlayer();
    
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
     * or to finish the game.
     */
    void registerTurnFinished(Player playerFinishingTurn);

    /**
     * This method returns the player with highest score. When the game
     * is finished, the person with the highest score is the winner.
     */
    Player getPlayerWithHighestScore();

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
    Game reStart();

}

package stacs.yahtzee;

import java.util.List;

/**
 * This interface defines the model for a game of Yahtzee. It relies on numerous
 * other classes, such as 'Player' and 'Dice'. The rules of Yahtzee as it
 * is implemented here can be found at: https://en.wikipedia.org/wiki/Yahtzee
 * One exception to the rules in this implementation is that there is no bonus
 * for rolling multiple 'yahtzee's.
 */
public interface IYahtzeeModel {

    /**
     * Sets this game's dice.
     */
    void setDice(List<IDie> dice);

    /**
     * Returns the dice that belong to the current game.
     */
    List<IDie> getDice();    

    /**
     * Sets this game's list of players.
     */
    void setPlayers(List<IPlayer> players);

    /**
     * Returns this game's list of players.
     */
    List<IPlayer> getPlayerList();

    /**
     * Sets the active player for this game.
     */
    void setActivePlayer(IPlayer newActivePlayerOrder);

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
    void registerTurnFinished(IPlayer playerFinishingTurn);

    /**
     * Returns a list containing one player with the highest score, or multiple
     * players who are tied for the lead.
     * @return A list of one or more players with the highest score
     */
    List<IPlayer> getPlayersWithHighestScore();

    /**
     * If the game is done, this method returns a list containing either the player
     * who won, or the players who tied for first place.
     * @return A list of one or more players with the highest score at the end of
     * the game
     */
    List<IPlayer> getWinners();

    /**
     * Method to check if the game is finished. This always returns true
     * after the last player's turn in the last round.
     * @return True if the game is finished, otherwise false
     */
    boolean isDone();

}

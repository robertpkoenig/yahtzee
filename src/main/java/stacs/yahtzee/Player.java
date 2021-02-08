package stacs.yahtzee;

import java.util.List;

public interface Player {
     /**
     * This method calls the 'roll' method on a list of dice objects.
     * The list of dice objects is the game's dice objects, less the dice
     * the player may have elected to 'keep' after the previous role. If it is
     * their first roll of the round, they must roll all the dice.
     */
    void rollDice(List<Dice> dice);

    /**
     * This enables a player to 'keep' certin dice that they have previously
     * rolled within the current round so that they are not rolled on subsequent
     * rolls within the current round.
     * @param keptDice The dice the player has elected not to roll.
     */
    void keepDice(Dice keptDice);

    /**
     * This allows the player to reverse their decision to 'keep' a certain dice.
     * @param unKeptDice The dice that the player will no longer 'keep'
     */
    void unKeepDice(Dice unKeptDice);

    /**
     * Get this player's score.
     */
    int getScore();

    /**
     * Gets the scoring options this player has not already registered a score for,
     * and which are satisfied by their current dice roll.
     * @return A list of scoring options that are fulfilled with the users
     * current dice and which they have not already used
     */
    List<PlayerScoringOption> getUnusedScoringOptionsSatisfiedByCurrentDice();

    /**
     * Returns the available scoring option that has the highest score given
     * the current dice values.
     * @return The highest scoring option.
     */
    PlayerScoringOption getHighestScoringOption();

    /**
     * Gets this player's scorecard object.
     */
    ScoreCard getScoreCard();

    /**
     * Called by the player when they wish to mark their scorecard for a particular
     * scoring option. This must be called exactly once during each of this player's
     * turn, and when it is called, it ends their turn.
     * @param scoringOption The scoring option which this person will take. The score
     * will be calculated automatically and registered in the scorecard card object 
     * by the "player score option" object.
     * @return Returns true if the operation is succesful. Returns false if the player
     * attempted to mark 
     */
    boolean useScoringOptionAndEndTurn(PlayerScoringOption scoringOption);

    /**
     * Tells the game that the current player has ended their turn. This is called
     * from the 'applyScoringOptionAndEndTurn' method.
     */
    void endTurn();

}

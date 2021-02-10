package stacs.yahtzee;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.processing.SupportedOptions;

/**
 * An implementation of the YahtzeeModel interface
 */
public class YahtzeeModel implements IYahtzeeModel {
    private List<IPlayer> players;
    private IPlayer activePlayer;
    private int currentRound;
    private List<IDice> dice;
    private boolean isGameFinished;

    public YahtzeeModel(int numPlayers) {
        addPlayersToGame(numPlayers);
        addDiceToGame();
    }

    @Override
    public void addDiceToGame() {
        this.dice = new ArrayList<>();
        int numDice = Constants.getNumberOfDice();
        for (int i = 0 ; i < numDice ; i++) {
            Dice newDie = new Dice();
            this.dice.add(newDie);
        }
    }

    
    /** 
     * @param numPlayers
     */
    @Override
    public void addPlayersToGame(int numPlayers) {
        this.players = new ArrayList<>();
        for (int i = 0 ; i < numPlayers ; i++) {
            Player newPlayer = new Player(i, this);
            this.players.add(newPlayer);
        }
        this.activePlayer = players.get(0);
    }

    
    /** 
     * @return Player
     */
    @Override
    public IPlayer getActivePlayer() {
        return this.activePlayer;
    }

    
    /** 
     * @return int
     */
    @Override
    public int getCurrentRound() {
        return this.currentRound;
    }

    
    /** 
     * @return Player
     */
    @Override
    public IPlayer getPlayerWithHighestScore() {
        IPlayer highestScoringPlayer = null;
        int highestScore = 0;
        for (IPlayer player : players) {
            if (player.getScore() > highestScore) {
                highestScoringPlayer = player;
                highestScore = player.getScore();
            }
        }
        return highestScoringPlayer;
    }

    
    /** 
     * @return int
     */
    @Override
    public int getTotalRounds() {
        return Constants.getNumberOfRounds();
    }

    
    /** 
     * @return boolean
     */
    @Override
    public boolean isGameFinished() {
        return this.isGameFinished;
    }

    
    /** 
     * @return YahtzeeModel
     */
    @Override
    public IYahtzeeModel reStart() {
        return new YahtzeeModel(this.players.size());
    }

    
    /** 
     * @param playerFinishingTurn
     */
    @Override
    public void registerTurnFinished(IPlayer playerFinishingTurn) {
        if (playerFinishingTurn != this.activePlayer) throw new IllegalArgumentException();

        // This logic ensures that the game never goes to '14' rounds, as this could mean
        // that a downstream GUI briefly shows 'round 14' before the game ends.
        if (playerFinishingTurn.getPlayingOrder() == this.players.size()) {
            if (this.currentRound == Constants.getNumberOfRounds()) {
                this.isGameFinished = true;
                this.activePlayer = null;
                return;
            }
            else this.currentRound++;
        }

        int newPlayerOrder = (playerFinishingTurn.getPlayingOrder() + 1) % this.players.size();
        this.activePlayer = players.get(newPlayerOrder);
    }

    @Override
    public List<IDice> getDice() {
        return this.dice;
    }

    @Override
    public List<IPlayer> getPlayerList() {
        return  this.players;
    }

}

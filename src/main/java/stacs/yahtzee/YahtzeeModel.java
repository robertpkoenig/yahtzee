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
    private List<IDie> dice;
    private boolean isDone;

    public YahtzeeModel() {}

    @Override
    public void setDice(List<IDie> newDice) {
        this.dice = newDice;
    }

    @Override
    public void setPlayers(List<IPlayer> newPlayers) {
        this.players = newPlayers;
    }
    
    @Override
    public void setActivePlayer(int newActivePlayerOrder) {
        this.activePlayer = players.get(newActivePlayerOrder);
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
            if (player.getScoreCard().getTotalScore() > highestScore) {
                highestScoringPlayer = player;
                highestScore = player.getScoreCard().getTotalScore();
            }
        }
        return highestScoringPlayer;
    }

    
    /** 
     * @return int
     */
    @Override
    public int getTotalRounds() {
        return Constants.numberOfRounds;
    }

    
    /** 
     * @return boolean
     */
    @Override
    public boolean isDone() {
        return this.isDone;
    }
    
    /** 
     * @param playerFinishingTurn
     */
    @Override
    public void registerTurnFinished(IPlayer playerFinishingTurn) {
        if (playerFinishingTurn != this.activePlayer) throw new IllegalArgumentException();

        // This logic ensures that the game never goes to '14' rounds, as this could mean
        // that a downstream GUI briefly shows 'round 14' before the game ends.
        if (playerFinishingTurn.getPlayingOrder() == this.players.size() - 1) {
            if (this.currentRound >= Constants.numberOfRounds - 1) {
                this.isDone = true;
                this.activePlayer = null;
                return;
            }
            else this.currentRound++;
        }

        int newActivePlayerOrder = (playerFinishingTurn.getPlayingOrder() + 1) % this.players.size();
        setActivePlayer(newActivePlayerOrder);
    }

    

    @Override
    public List<IDie> getDice() {
        return this.dice;
    }

    @Override
    public List<IPlayer> getPlayerList() {
        return  this.players;
    }

}

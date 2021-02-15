package stacs.yahtzee.implementation;

import stacs.yahtzee.*;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of the YahtzeeModel interface
 */
public class YahtzeeModel implements IYahtzeeModel {
    private List<IPlayer> players;
    private IPlayer activePlayer;
    private int currentRound;
    private List<IDie> dice;
    private boolean isDone;

    public YahtzeeModel() {
        players = new ArrayList<>();
        dice = new ArrayList<>();
        currentRound = 0;
    }

    @Override
    public void addDie(IDie newDie) {
        this.dice.add(newDie);
    }

    @Override
    public void addPlayer(IPlayer newPlayer) {
        newPlayer.setPlayingOrder(this.players.size());
        newPlayer.setGame(this);
        this.players.add(newPlayer);
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
    public List<IPlayer> getPlayersWithHighestScore() {
        List<IPlayer> highestScoringPlayers = new ArrayList<>();
        int highestScore = 0;
        for (IPlayer player : players) {
            if (player.getScoreCard().getTotalScore() > highestScore) {
                highestScoringPlayers.clear();
                highestScoringPlayers.add(player);
                highestScore = player.getScoreCard().getTotalScore();
            }
            else if (highestScore > 0 &&
                    player.getScoreCard().getTotalScore() == highestScore) {
                highestScoringPlayers.add(player);
            }
        }
        return highestScoringPlayers;
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
    public void registerTurnFinished(IPlayer playerFinishingTurn) throws IllegalArgumentException{
        if (playerFinishingTurn != this.activePlayer) throw new IllegalArgumentException();
        // prevent player from declaring turn over if they have not registered a score for this round
        if (playerFinishingTurn.getScoreCard().getUsedScoringOptions().size() < currentRound + 1)
            throw new IllegalArgumentException();
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

        int newActivePlayerOrder = 
            (playerFinishingTurn.getPlayingOrder() + 1) % this.players.size();
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

    @Override
    public List<IPlayer> getWinners() throws IllegalStateException{
        if (!this.isDone) throw new IllegalStateException();
        else return getPlayersWithHighestScore();
    }

}

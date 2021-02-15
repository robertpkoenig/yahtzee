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
        currentRound = 0;
    }

    @Override
    public void setDice(List<IDie> dice) {
        this.dice = dice;
    }
    
    @Override
    public List<IDie> getDice() {
        return this.dice;
    }

    @Override
    public void setPlayers(List<IPlayer> players) {
        this.players = players;
        for (int i = 0 ; i < players.size() ; i++) {
            players.get(i).setPlayingOrder(i);
            players.get(i).setGame(this);
        }
    }

    @Override
    public List<IPlayer> getPlayerList() {
        return  this.players;
    }

    @Override
    public void setActivePlayer(IPlayer newActivePlayerOrder) {
        this.activePlayer = newActivePlayerOrder;
    }

    @Override
    public IPlayer getActivePlayer() {
        return this.activePlayer;
    }

    @Override
    public int getCurrentRound() {
        return this.currentRound;
    }

    @Override
    public int getTotalRounds() {
        return Constants.numberOfRounds;
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
        setActivePlayer(this.players.get(newActivePlayerOrder));
    }

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

    @Override
    public List<IPlayer> getWinners() throws IllegalStateException{
        if (!this.isDone) throw new IllegalStateException();
        else return getPlayersWithHighestScore();
    }

    @Override
    public boolean isDone() {
        return this.isDone;
    }
}

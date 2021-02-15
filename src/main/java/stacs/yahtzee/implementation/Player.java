package stacs.yahtzee.implementation;

import stacs.yahtzee.*;

import java.util.ArrayList;
import java.util.List;

public class Player implements IPlayer {

    int playingOrder;
    int score;
    IYahtzeeModel game;
    int rollsCompleted;
    List<IDie> keptDice;
    IScoreCard scoreCard;

    public Player() {
        this.keptDice = new ArrayList<>();
    }

    @Override
    public void setPlayingOrder(int playingOrder) {
        this.playingOrder = playingOrder;
    }

    @Override
    public int getPlayingOrder() {
        return this.playingOrder;
    }

    @Override
    public void setGame(IYahtzeeModel game) {
        if (game.getDice().size() != 5) throw new IllegalStateException();
        this.game = game;
    }

    @Override
    public IYahtzeeModel getGame() {
        return this.game;
    }

    @Override
    public void setScoreCard(IScoreCard scoreCard) {
        this.scoreCard = scoreCard;
    }

    @Override
    public IScoreCard getScoreCard() {
        return this.scoreCard;
    }

    @Override
    public void rollDice() throws IllegalStateException{
        if (this.rollsCompleted >= 3) throw new IllegalStateException();
        if (this.game.getActivePlayer() != this) throw new IllegalStateException();
        for (IDie die : getActiveDice()) die.roll();
        this.rollsCompleted++;
    }
    
    @Override
    public void addKeptDie(IDie newKeptDie) {
        this.keptDice.add(newKeptDie);
    }

    @Override
    public void removeKeptDie(IDie dieToBeRemoved) {
        this.keptDice.remove(dieToBeRemoved);
        
    }

    @Override
    public List<IDie> getKeptDice() {
        return this.keptDice;
    }

    @Override
    public List<IDie> getActiveDice() {
        List<IDie> activeDice = new ArrayList<>();
        for (IDie die : game.getDice()) {
            if (!this.keptDice.contains(die)) activeDice.add(die);
        }
        return activeDice;
    }

    @Override
    public int getNumberOfRollsCompleted() {
        return this.rollsCompleted;
    }

    @Override
    public void useScoringOptionAndEndTurn(IScoringOption scoringOption) {
        scoringOption.recordScoreForThisOption(game.getDice());
        endTurn();
    }

    /**
     * Tells the game to set the next player as the active player, and 
     * resets the player's state.
     */
    private void endTurn() {
        this.keptDice.clear();
        this.rollsCompleted = 0;
        this.game.registerTurnFinished(this);
    }

}

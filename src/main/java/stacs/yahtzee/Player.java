package stacs.yahtzee;

import java.util.ArrayList;
import java.util.List;

public class Player implements IPlayer {

    int playerOrder;
    int score;
    IYahtzeeModel game;
    int rollsCompleted;
    List<IDie> keptDice;
    IScoreCard scoreCard;

    public Player(int playerOrder, IYahtzeeModel game) {
        this.playerOrder = playerOrder;
        this.game = game;
        this.score = 0;
    }

    @Override
    public int getPlayingOrder() {
        return this.playerOrder;
    }

    @Override
    public void rollDice() throws IllegalStateException{
        if (this.rollsCompleted >= 3) throw new IllegalStateException();
        for (IDie die : getActiveDice()) die.roll();
        this.rollsCompleted++;
    }

    @Override
    public List<IDie> getKeptDice() {
        return this.keptDice;
    }
    
    @Override
    public void setKeptDice(List<IDie> keptDice) {
        this.keptDice = keptDice;
    }

    @Override
    public List<IDie> getActiveDice() {
        List<IDie> activeDice = new ArrayList<>();
        for (IDie die : this.game.getDice()) {
            if (!this.keptDice.contains(die)) activeDice.add(die);
        }
        return activeDice;
    }

    @Override
    public IScoreCard getScoreCard() {
        return this.scoreCard;
    }

    @Override
    public boolean useScoringOptionAndEndTurn(IPlayerScoringOption scoringOption) {
        // TODO Auto-generated method stub
        resetPlayerState();
        return false;
    }

    private void resetPlayerState() {
        this.keptDice.clear();
        this.rollsCompleted = 0;
    }

    private void endTurn() {
        this.game.registerTurnFinished(this);
        this.keptDice.clear();
    }

    @Override
    public int getNumberOfRollsCompleted() {
        return this.rollsCompleted;
    }

    @Override
    public void setScoreCard(IScoreCard scoreCard) {
        this.scoreCard = scoreCard;
    }

}

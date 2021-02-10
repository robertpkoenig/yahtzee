package stacs.yahtzee;

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
        // TODO Auto-generated method stub
    }

    @Override
    public List<IDie> getKeptDice() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void setKeptDice(List<IDie> keptDie) {
        // TODO Auto-generated method stub
    }

    @Override
    public int getScore() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<IPlayerScoringOption> getUnusedScoringOptionsSatisfiedByCurrentDice() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IPlayerScoringOption getHighestScoringOption() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public IScoreCard getScoreCard() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean useScoringOptionAndEndTurn(IPlayerScoringOption scoringOption) {
        // TODO Auto-generated method stub
        return false;
    }

    private void endTurn() {
        // TODO Auto-generated method stub
    }

    @Override
    public int getNumberOfRollsCompleted() {
        // TODO Auto-generated method stub
        return 0;
    }

    
}

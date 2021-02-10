package stacs.yahtzee;

import java.util.List;

public class Player implements IPlayer {

    int playerOrder;
    IYahtzeeModel game;
    int rollsCompleted;

    public Player(int playerOrder, IYahtzeeModel game) {
        this.playerOrder = playerOrder;
        this.game = game;
    }

    @Override
    public int getPlayingOrder() {
        return this.playerOrder;
    }

    @Override
    public void rollDice(List<IDice> dice) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keepDice(IDice keptDice) {
        // TODO Auto-generated method stub

    }

    @Override
    public void unKeepDice(IDice unKeptDice) {
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

    @Override
    public void endTurn() {
        // TODO Auto-generated method stub

    }

    @Override
    public int getNumberOfRollsCompleted() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<IDice> getKeptDice() {
        // TODO Auto-generated method stub
        return null;
    }
    
}

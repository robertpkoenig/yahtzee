package stacs.yahtzee;

import java.util.List;

public class ScoreCard implements IScoreCard {

    List<IScoringOption> scoringOptions;

    public ScoreCard() {}

    @Override
    public void setLowerScoringOptions(List<IScoringOption> scoringOptions) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setUppperScoringOptions(List<IScoringOption> scoringOptions) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int getTotalScore() {
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
    public int getLowerScore() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getUpperScoreWithoutBonus() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getUpperScoreWithBonus() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getUpperScoreBonus() {
        // TODO Auto-generated method stub
        return 0;
    }
    
}

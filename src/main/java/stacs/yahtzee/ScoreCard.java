package stacs.yahtzee;

import stacs.yahtzee.scoringoptions.IScoringOption;

import java.util.ArrayList;
import java.util.List;


public class ScoreCard implements IScoreCard {

    List<IScoringOption> scoringOptions;
    IYahtzeeModel game;

    public ScoreCard() {}

    @Override
    public void setGame(IYahtzeeModel game) {
       this.game = game;
    }

    @Override
    public void setScoringOptions(List<IScoringOption> scoringOptions) {
        this.scoringOptions = scoringOptions;
    }

    @Override
    public int getTotalScore() {
        return getUpperScoreWithoutBonus() + getBonus() + getLowerScore();
    }

    @Override
    public List<IScoringOption> getUnusedScoringOptionsSatisfiedByCurrentDice() {
        // for each scoring option check if it is used
        // then check if it is satisfied by the current dice
        // if so, add to the list of satisfying options
        List<IScoringOption> outputList = new ArrayList<>();
        for (IScoringOption scoringOption : scoringOptions) {
            if (scoringOption.getScoreRecordedForThisOption() == -1) continue;
            if (scoringOption.isSatisfiedByDice(this.game.getDice())) {
                outputList.add(scoringOption);
            }
        }
        return outputList;
    }

    @Override
    public IScoringOption getHighestScoringOption() {
        IScoringOption highestScoringOption = null;
        for (IScoringOption scoringOption : getUnusedScoringOptionsSatisfiedByCurrentDice()) {
            if (scoringOption.getScoreRecordedForThisOption() == -1) continue;
            if (highestScoringOption == null ||
                scoringOption.getScoreRecordedForThisOption() > highestScoringOption.getScoreRecordedForThisOption())
                {
                highestScoringOption = scoringOption;
            }
        }
        return highestScoringOption;
    }

    @Override
    public int getLowerScore() {
        int lowerScore = 0;
        for (IScoringOption scoringOption : this.scoringOptions) {
            if (scoringOption.getScoreRecordedForThisOption() == -1) continue;
            if (!scoringOption.isInUpperGroup()) lowerScore += scoringOption.getScoreRecordedForThisOption();
        }
        return lowerScore;
    }

    @Override
    public int getUpperScoreWithoutBonus() {
        int upperScore = 0;
        for (IScoringOption scoringOption : this.scoringOptions) {
            if (scoringOption.getScoreRecordedForThisOption() == -1) continue;
            if (scoringOption.isInUpperGroup()) upperScore += scoringOption.getScoreRecordedForThisOption();
        }
        return upperScore;
    }

    @Override
    public int getBonus() {
        return (getUpperScoreWithoutBonus() >= Constants.upperBonusThreshold) ? Constants.upperBonus : 0;
    }
    
}

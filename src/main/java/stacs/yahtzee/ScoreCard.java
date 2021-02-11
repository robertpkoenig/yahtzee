package stacs.yahtzee;

import stacs.yahtzee.scoringoptions.*;

import java.util.ArrayList;
import java.util.List;


public class ScoreCard implements IScoreCard {

    private IScoringOption Ones;
    private IScoringOption Twos;
    private IScoringOption Threes;
    private IScoringOption Fours;
    private IScoringOption Fives;
    private IScoringOption Sixes;

    private IScoringOption ThreeOfAKind;
    private IScoringOption FourOfAKind;
    private IScoringOption FullHouse;
    private IScoringOption SmallStraight;
    private IScoringOption LargeStraight;
    private IScoringOption Chance;
    private IScoringOption Yahtzee;
    
    IYahtzeeModel game;

    public ScoreCard() {}

    @Override
    public void setGame(IYahtzeeModel game) {
       this.game = game;
    }

    @Override
    public void setAllScoringOptions() {
        this.Ones = new TwosScoringOption();
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
        List<IScoringOption> searchList = new ArrayList<>();
        searchList.addAll(getLowerScoringOptions());
        searchList.addAll(getUpperScoringOptions());
        for (IScoringOption scoringOption : searchList) {
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
        for (IScoringOption scoringOption : getLowerScoringOptions()) {
            if (scoringOption.getScoreRecordedForThisOption() == -1) continue;
            lowerScore += scoringOption.getScoreRecordedForThisOption();
        }
        return lowerScore;
    }

    @Override
    public int getUpperScoreWithoutBonus() {
        int upperScore = 0;
        for (IScoringOption scoringOption : getUpperScoringOptions()) {
            if (scoringOption.getScoreRecordedForThisOption() == -1) continue;
            upperScore += scoringOption.getScoreRecordedForThisOption();
        }
        return upperScore;
    }

    @Override
    public int getBonus() {
        return (getUpperScoreWithoutBonus() >= Constants.upperBonusThreshold) ? Constants.upperBonus : 0;
    }

    @Override
    public List<IScoringOption> getUpperScoringOptions() {
        List<IScoringOption> upperScoringOptions = new ArrayList<>();
        upperScoringOptions.add(this.Ones);
        upperScoringOptions.add(this.Twos);
        upperScoringOptions.add(this.Threes);
        upperScoringOptions.add(this.Fours);
        upperScoringOptions.add(this.Fives);
        upperScoringOptions.add(this.Sixes);
        return upperScoringOptions;
    }

    @Override
    public List<IScoringOption> getLowerScoringOptions() {
        List<IScoringOption> lowerScoringOptions = new ArrayList<>();
        lowerScoringOptions.add(this.ThreeOfAKind);
        lowerScoringOptions.add(this.FourOfAKind);
        lowerScoringOptions.add(this.FullHouse);
        lowerScoringOptions.add(this.SmallStraight);
        lowerScoringOptions.add(this.LargeStraight);
        lowerScoringOptions.add(this.Chance);
        lowerScoringOptions.add(this.Yahtzee);
        return lowerScoringOptions;
    }
    
}

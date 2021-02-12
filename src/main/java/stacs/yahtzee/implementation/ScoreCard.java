package stacs.yahtzee.implementation;

import stacs.yahtzee.implementation.scoringoptions.*;
import stacs.yahtzee.*;

import java.util.ArrayList;
import java.util.List;


public class ScoreCard implements IScoreCard {

    private IScoringOption ones;
    private IScoringOption twos;
    private IScoringOption threes;
    private IScoringOption fours;
    private IScoringOption fives;
    private IScoringOption sixes;

    private IScoringOption threeOfKind;
    private IScoringOption fourOfKind;
    private IScoringOption fullHouse;
    private IScoringOption smallStraight;
    private IScoringOption largeStraight;
    private IScoringOption chance;
    private IScoringOption yahtzee;
    
    IYahtzeeModel game;

    public ScoreCard() {}

    @Override
    public void setGame(IYahtzeeModel game) {
       this.game = game;
    }

    @Override
    public int getTotalScore() {
        System.out.println(getUpperScoreWithoutBonus());
        System.out.println(getBonus());
        System.out.println(getLowerScore());
        return getUpperScoreWithoutBonus() + getBonus() + getLowerScore();
    }

    @Override
    public List<IScoringOption> getUnusedScoringOptionsSatisfiedByCurrentDice(List<IDie> dice) {
        List<IScoringOption> outputList = new ArrayList<>();
        List<IScoringOption> searchList = new ArrayList<>();
        searchList.addAll(getLowerScoringOptions());
        searchList.addAll(getUpperScoringOptions());
        for (IScoringOption scoringOption : searchList) {
            if (scoringOption.getScoreRecordedForThisOption() != -1) continue;
            if (scoringOption.calculateScoreForThisOption(this.game.getDice()) > 0) {
                outputList.add(scoringOption);
            }
        }
        return outputList;
    }

    @Override
    public IScoringOption getHighestScoringOption(List<IDie> dice) {
        IScoringOption highestScoringOption = null;
        for (IScoringOption scoringOption : getUnusedScoringOptionsSatisfiedByCurrentDice(dice)) {
            if (scoringOption.getScoreRecordedForThisOption() != -1) continue;
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
        upperScoringOptions.add(this.ones);
        upperScoringOptions.add(this.twos);
        upperScoringOptions.add(this.threes);
        upperScoringOptions.add(this.fours);
        upperScoringOptions.add(this.fives);
        upperScoringOptions.add(this.sixes);
        return upperScoringOptions;
    }

    @Override
    public List<IScoringOption> getLowerScoringOptions() {
        List<IScoringOption> lowerScoringOptions = new ArrayList<>();
        lowerScoringOptions.add(this.threeOfKind);
        lowerScoringOptions.add(this.fourOfKind);
        lowerScoringOptions.add(this.fullHouse);
        lowerScoringOptions.add(this.smallStraight);
        lowerScoringOptions.add(this.largeStraight);
        lowerScoringOptions.add(this.chance);
        lowerScoringOptions.add(this.yahtzee);
        return lowerScoringOptions;
    }

    @Override
    public void setOnesScoringOption(IScoringOption onesScoringOption) {
        this.ones = onesScoringOption;
    }

    @Override
    public void setTwosScoringOption(IScoringOption twosScoringOption) {
        this.twos = twosScoringOption;

    }

    @Override
    public void setThreesScoringOption(IScoringOption threesScoringOption) {
        this.threes = threesScoringOption;

    }

    @Override
    public void setFoursScoringOption(IScoringOption foursScoringOption) {
        this.fours = foursScoringOption;

    }

    @Override
    public void setFivesScoringOption(IScoringOption fivesScoringOption) {
        this.fives = fivesScoringOption;

    }

    @Override
    public void setSixesScoringOption(IScoringOption sixesScoringOption) {
        this.sixes = sixesScoringOption;

    }

    @Override
    public void setThreeOfKindScoringOption(IScoringOption threeOfKindScoringOption) {
        this.threeOfKind = threeOfKindScoringOption;

    }

    @Override
    public void setFourOfKindScoringOption(IScoringOption fourOfKindScoringOption) {
        this.fourOfKind = fourOfKindScoringOption;

    }

    @Override
    public void setFullHouseScoringOption(IScoringOption fullHouseScoringOption) {
        this.fullHouse = fullHouseScoringOption;

    }

    @Override
    public void setSmallStraightScoringOption(IScoringOption smallStraightScoringOption) {
        this.smallStraight = smallStraightScoringOption;

    }

    @Override
    public void setLargeStraightScoringOption(IScoringOption largeStraightScoringOption) {
        this.largeStraight = largeStraightScoringOption;

    }

    @Override
    public void setChanceScoringOption(IScoringOption chanceScoringOption) {
        this.chance = chanceScoringOption;

    }

    @Override
    public void setYahtzeeScoringOption(IScoringOption yahtzeeScoringOption) {
        this.yahtzee = yahtzeeScoringOption;

    }
    
}

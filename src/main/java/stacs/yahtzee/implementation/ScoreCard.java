package stacs.yahtzee.implementation;

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

    public ScoreCard() {}

    @Override
    public int getTotalScore() {
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
            if (scoringOption.calculateScoreForThisOption(dice) > 0) {
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

    private List<IScoringOption> getUpperScoringOptions() {
        List<IScoringOption> upperScoringOptions = new ArrayList<>();
        upperScoringOptions.add(this.ones);
        upperScoringOptions.add(this.twos);
        upperScoringOptions.add(this.threes);
        upperScoringOptions.add(this.fours);
        upperScoringOptions.add(this.fives);
        upperScoringOptions.add(this.sixes);
        return upperScoringOptions;
    }

    private List<IScoringOption> getLowerScoringOptions() {
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

    public IScoringOption getOnes() {
        return ones;
    }

    public void setOnes(IScoringOption ones) {
        this.ones = ones;
    }

    public IScoringOption getTwos() {
        return twos;
    }

    public void setTwos(IScoringOption twos) {
        this.twos = twos;
    }

    public IScoringOption getThrees() {
        return threes;
    }

    public void setThrees(IScoringOption threes) {
        this.threes = threes;
    }

    public IScoringOption getFours() {
        return fours;
    }

    public void setFours(IScoringOption fours) {
        this.fours = fours;
    }

    public IScoringOption getFives() {
        return fives;
    }

    public void setFives(IScoringOption fives) {
        this.fives = fives;
    }

    public IScoringOption getSixes() {
        return sixes;
    }

    public void setSixes(IScoringOption sixes) {
        this.sixes = sixes;
    }

    public IScoringOption getThreeOfKind() {
        return threeOfKind;
    }

    public void setThreeOfKind(IScoringOption threeOfKind) {
        this.threeOfKind = threeOfKind;
    }

    public IScoringOption getFourOfKind() {
        return fourOfKind;
    }

    public void setFourOfKind(IScoringOption fourOfKind) {
        this.fourOfKind = fourOfKind;
    }

    public IScoringOption getFullHouse() {
        return fullHouse;
    }

    public void setFullHouse(IScoringOption fullHouse) {
        this.fullHouse = fullHouse;
    }

    public IScoringOption getSmallStraight() {
        return smallStraight;
    }

    public void setSmallStraight(IScoringOption smallStraight) {
        this.smallStraight = smallStraight;
    }

    public IScoringOption getLargeStraight() {
        return largeStraight;
    }

    public void setLargeStraight(IScoringOption largeStraight) {
        this.largeStraight = largeStraight;
    }

    public IScoringOption getChance() {
        return chance;
    }

    public void setChance(IScoringOption chance) {
        this.chance = chance;
    }

    public IScoringOption getYahtzee() {
        return yahtzee;
    }

    public void setYahtzee(IScoringOption yahtzee) {
        this.yahtzee = yahtzee;
    }

}

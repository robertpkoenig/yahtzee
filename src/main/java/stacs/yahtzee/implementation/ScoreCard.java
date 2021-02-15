package stacs.yahtzee.implementation;

import stacs.yahtzee.*;

import java.util.ArrayList;
import java.util.List;


public class ScoreCard implements IScoreCard {

    private IYahtzeeModel game;

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

    public ScoreCard(IPlayer player) {
        this.game = player.getGame();
    }

    @Override
    public int getTotalScore() {
        return getUpperScoreWithoutBonus() + getBonus() + getLowerScore();
    }

    @Override
    public List<IScoringOption> getUnusedScoringOptionsSatisfiedByCurrentDice() {
        List<IScoringOption> unusedScoringOptionsSatisfiedByCurrentDice = new ArrayList<>();
        List<IScoringOption> searchList = getAllScoringOptions();
        for (IScoringOption scoringOption : searchList) {
            if (scoringOption.getScoreRecordedForThisOption() != -1) continue;
            if (scoringOption.calculateScoreForThisOption(game.getDice()) > 0) {
                unusedScoringOptionsSatisfiedByCurrentDice.add(scoringOption);
            }
        }
        return unusedScoringOptionsSatisfiedByCurrentDice;
    }

    @Override
    public IScoringOption getHighestScoringOption() {
        IScoringOption highestScoringOption = null;
        for (IScoringOption scoringOption : getUnusedScoringOptionsSatisfiedByCurrentDice()) {
            if (highestScoringOption == null ||
                    scoringOption.calculateScoreForThisOption(game.getDice()) >
                    highestScoringOption.calculateScoreForThisOption(game.getDice()))
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

    @Override
    public List<IScoringOption> getUsedScoringOptions() {
        List<IScoringOption> usedScoringOptions = new ArrayList<>();
        List<IScoringOption> searchList = getAllScoringOptions();
        for (IScoringOption scoringOption : searchList) {
            if (scoringOption.getScoreRecordedForThisOption() != -1)
                usedScoringOptions.add(scoringOption);
        }
        return usedScoringOptions;
    }

    private List<IScoringOption> getAllScoringOptions() {
        List<IScoringOption> allScoringOptions = new ArrayList<>();
        allScoringOptions.addAll(getUpperScoringOptions());
        allScoringOptions.addAll(getLowerScoringOptions());
        return allScoringOptions;
    }

    @Override
    public IScoringOption getOnes() {
        return ones;
    }

    @Override
    public void setOnes(IScoringOption ones) {
        this.ones = ones;
    }

    @Override
    public IScoringOption getTwos() {
        return twos;
    }

    @Override
    public void setTwos(IScoringOption twos) {
        this.twos = twos;
    }

    @Override
    public IScoringOption getThrees() {
        return threes;
    }

    @Override
    public void setThrees(IScoringOption threes) {
        this.threes = threes;
    }

    @Override
    public IScoringOption getFours() {
        return fours;
    }

    @Override
    public void setFours(IScoringOption fours) {
        this.fours = fours;
    }

    @Override
    public IScoringOption getFives() {
        return fives;
    }

    @Override
    public void setFives(IScoringOption fives) {
        this.fives = fives;
    }

    @Override
    public IScoringOption getSixes() {
        return sixes;
    }

    @Override
    public void setSixes(IScoringOption sixes) {
        this.sixes = sixes;
    }

    @Override
    public IScoringOption getThreeOfKind() {
        return threeOfKind;
    }

    @Override
    public void setThreeOfKind(IScoringOption threeOfKind) {
        this.threeOfKind = threeOfKind;
    }

    @Override
    public IScoringOption getFourOfKind() {
        return fourOfKind;
    }

    @Override
    public void setFourOfKind(IScoringOption fourOfKind) {
        this.fourOfKind = fourOfKind;
    }

    @Override
    public IScoringOption getFullHouse() {
        return fullHouse;
    }

    @Override
    public void setFullHouse(IScoringOption fullHouse) {
        this.fullHouse = fullHouse;
    }

    @Override
    public IScoringOption getSmallStraight() {
        return smallStraight;
    }

    @Override
    public void setSmallStraight(IScoringOption smallStraight) {
        this.smallStraight = smallStraight;
    }

    @Override
    public IScoringOption getLargeStraight() {
        return largeStraight;
    }

    @Override
    public void setLargeStraight(IScoringOption largeStraight) {
        this.largeStraight = largeStraight;
    }

    @Override
    public IScoringOption getChance() {
        return chance;
    }

    @Override
    public void setChance(IScoringOption chance) {
        this.chance = chance;
    }

    @Override
    public IScoringOption getYahtzee() {
        return yahtzee;
    }

    @Override
    public void setYahtzee(IScoringOption yahtzee) {
        this.yahtzee = yahtzee;
    }

}

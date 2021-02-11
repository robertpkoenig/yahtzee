package stacs.yahtzee.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;

public abstract class AScoringOption implements IScoringOption {

    int scoreRecorded;

    protected AScoringOption() {
        this.scoreRecorded = -1;
    }

    @Override
    public int getScoreRecordedForThisOption() {
        return this.scoreRecorded;
    }

    @Override
    public void recordScoreForThisOption(List<IDie> dice) throws IllegalStateException {
        if (this.scoreRecorded != -1) throw new IllegalStateException();
        this.scoreRecorded = calculateScoreForThisOption(dice);
    }
    
}

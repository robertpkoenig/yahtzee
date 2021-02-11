package stacs.yahtzee.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;

public class LargeStraightScoringOption extends AScoringOption {

    static final String name = "Large Straight";
    static final String description = 
        "When four dice have sequential value, score is 40";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        return 0;
    }
    
}

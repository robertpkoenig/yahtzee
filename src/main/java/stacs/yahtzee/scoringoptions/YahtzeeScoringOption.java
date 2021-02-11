package stacs.yahtzee.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;

public class YahtzeeScoringOption extends AScoringOption {

    static final String name = "Yahtzee";
    static final String description = 
        "When all dice have same face value, score is 50";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        return 0;
    }
    
}

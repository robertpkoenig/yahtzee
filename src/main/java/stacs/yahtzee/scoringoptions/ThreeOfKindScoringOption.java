package stacs.yahtzee.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;

public class ThreeOfKindScoringOption extends AScoringOption {

    static final String name = "Three of a kind";
    static final String description = 
        "When three dice have the same face, score is sum of all dice face values";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        return 0;
    }
    
}

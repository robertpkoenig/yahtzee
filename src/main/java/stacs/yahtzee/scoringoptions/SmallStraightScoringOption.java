package stacs.yahtzee.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;

public class SmallStraightScoringOption extends AScoringOption {

    static final String name = "Small Straight";
    static final String description = 
        "When the player has four sequential dice, score is 30";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        return 0;
    }
    
}

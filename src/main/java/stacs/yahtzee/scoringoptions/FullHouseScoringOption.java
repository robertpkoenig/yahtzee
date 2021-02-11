package stacs.yahtzee.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;

public class FullHouseScoringOption extends AScoringOption {

    static final String name = "Full House";
    static final String description = 
        "When three dice with one number, and two of another, score is 25";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        return 0;
    }
    
}

package stacs.yahtzee.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;

public class SixesScoringOption extends AScoringOption {

    static final String name = "Sixes";
    static final String description = 
        "Number of dice with value 6 multiplied by 6";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        return 0;
    }
    
}

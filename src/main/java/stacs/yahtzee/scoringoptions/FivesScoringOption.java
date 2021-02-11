package stacs.yahtzee.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;

public class FivesScoringOption extends AScoringOption {

    static final String name = "Fives";
    static final String description = 
        "Number of dice with value 5 multiplied by 5";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        return 0;
    }
    
}

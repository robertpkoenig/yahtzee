package stacs.yahtzee.implementation.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;

public class ThreesScoringOption extends AScoringOption {

    static final String name = "Threes";
    static final String description = 
        "Number of dice with value 3 multiplied by 3";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        int score = 0;
        for (IDie die : dice) {
            if (die.getCurrentFace() == 3) score += 3;
        }
        return score;
    }
    
}

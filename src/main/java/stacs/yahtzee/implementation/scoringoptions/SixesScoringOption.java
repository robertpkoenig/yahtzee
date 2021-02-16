package stacs.yahtzee.implementation.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;

/**
 * Model for the 'Sixes' scoring option. This is calculated as:
 * The number of dice with value 6 multiplied by 6.
 */
public class SixesScoringOption extends AScoringOption {

    static final String name = "Sixes";
    static final String description = 
        "Number of dice with value 6 multiplied by 6";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        int score = 0;
        for (IDie die : dice) {
            if (die.getCurrentFace() == 6) score += 6;
        }
        return score;
    }
    
}

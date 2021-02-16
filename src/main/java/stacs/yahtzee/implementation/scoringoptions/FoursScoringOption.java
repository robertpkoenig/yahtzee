package stacs.yahtzee.implementation.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;

/**
 * Model for the 'Fours' scoring option. This is calculated as:
 * The number of dice with value 4 multiplied by 4
 */
public class FoursScoringOption extends AScoringOption {

    static final String name = "Fours";
    static final String description = 
        "Number of dice with value 4 multiplied by 4";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        int score = 0;
        for (IDie die : dice) {
            if (die.getCurrentFace() == 4) score += 4;
        }
        return score;
    }
    
}

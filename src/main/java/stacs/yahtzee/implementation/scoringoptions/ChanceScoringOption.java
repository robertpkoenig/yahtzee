package stacs.yahtzee.implementation.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;

/**
 * Model for the 'Chance' scoring option. This is calculated as
 * the sum of all dice values, and has no satisfying condition.
 */
public class ChanceScoringOption extends AScoringOption {

    static final String name = "Chance";
    static final String description = 
        "Sum of all dice values";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        int sumOfDiceFaceValues = 0;
        for (IDie die : dice) sumOfDiceFaceValues += die.getCurrentFace();
        return sumOfDiceFaceValues;
    }
    
}

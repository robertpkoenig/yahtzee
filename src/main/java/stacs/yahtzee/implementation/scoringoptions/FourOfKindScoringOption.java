package stacs.yahtzee.implementation.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;
import stacs.yahtzee.implementation.Constants;

/**
 * Model for the 'Four of a kind' scoring option. If four dice have the 
 * same face value, the score for this option is sum of all faces
 */
public class FourOfKindScoringOption extends AScoringOption {

    static final String name = "Four of a kind";
    static final String description = 
        "If four dice have the same face value, score is sum of all faces";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        int[] counts = new int[Constants.numberOfDiceFaces];
        int sum = 0;
        boolean fourOfKindFound = false;

        for (IDie die : dice) {
            // subtract one from face value to zero index
            counts[die.getCurrentFace() - 1]++;
        }
        for (int count : counts) if (count >= 4) fourOfKindFound = true;
        if (!fourOfKindFound) return 0;

        for (IDie die : dice) {
            sum += die.getCurrentFace();
        }
        return sum;

    }
    
}

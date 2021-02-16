package stacs.yahtzee.implementation.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;
import stacs.yahtzee.implementation.Constants;

/**
 * Model for the 'Three of a kind' scoring option. When three dice have the
 * same face, score for this option is sum of all dice face values.
 */
public class ThreeOfKindScoringOption extends AScoringOption {

    static final String name = "Three of a kind";
    static final String description = 
        "When three dice have the same face, score is sum of all dice face values";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        int[] counts = new int[Constants.numberOfDiceFaces];
        int sum = 0;
        boolean threeOfKindFound = false;

        for (IDie die : dice) {
            // subtract one from face value to zero index
            counts[die.getCurrentFace() - 1]++;
        }
        for (int count : counts) if (count >= 3) threeOfKindFound = true;
        if (!threeOfKindFound) return 0;

        for (IDie die : dice) {
            sum += die.getCurrentFace();
        }
        return sum;
    }
    
}

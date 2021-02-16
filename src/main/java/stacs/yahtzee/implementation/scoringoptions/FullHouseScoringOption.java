package stacs.yahtzee.implementation.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;
import stacs.yahtzee.implementation.Constants;

/**
 * Model for the 'Full House' scoring option. When three dice with one number,
 * and two of another, the score for this option is 25.
 */
public class FullHouseScoringOption extends AScoringOption {

    static final String name = "Full House";
    static final String description = 
        "When three dice with one number, and two of another, score is 25";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        int[] counts = new int[Constants.numberOfDiceFaces];
        boolean threeOfKindFound = false;
        boolean twoOfKindFound = false;

        for (IDie die : dice) {
            counts[die.getCurrentFace() - 1]++;
        }
        
        for (int count : counts) {
            if (count == 3) threeOfKindFound = true;
            if (count == 2) twoOfKindFound = true;
        }
        return (threeOfKindFound && twoOfKindFound) ? 25 : 0;
    }
    
}

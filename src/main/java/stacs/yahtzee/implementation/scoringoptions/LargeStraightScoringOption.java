package stacs.yahtzee.implementation.scoringoptions;

import java.util.List;

import stacs.yahtzee.implementation.Constants;
import stacs.yahtzee.IDie;

public class LargeStraightScoringOption extends AScoringOption {

    static final String name = "Large Straight";
    static final String description = 
        "When four dice have sequential value, score is 40";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        int[] diceValueCounts = new int[Constants.numberOfDiceFaces];
        for (IDie die : dice) {
            // substract one from face value to zero-index
            diceValueCounts[die.getCurrentFace() - 1]++;
        }
        int numberOfConsecutiveDiceValuesSeen = 0;
        for (int diceValueCount : diceValueCounts) {
            if (diceValueCount > 0) numberOfConsecutiveDiceValuesSeen++;
            else numberOfConsecutiveDiceValuesSeen = 0;
            if (numberOfConsecutiveDiceValuesSeen >= 5) return 40;
        }
        return 0;
    }
    
}

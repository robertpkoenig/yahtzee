package stacs.yahtzee.scoringoptions;

import java.util.List;

import stacs.yahtzee.Constants;
import stacs.yahtzee.IDie;

public class SmallStraightScoringOption extends AScoringOption {

    static final String name = "Small Straight";
    static final String description = 
        "When the player has four sequential dice, score is 30";

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
            if (numberOfConsecutiveDiceValuesSeen >= 4) return 30;
        }
        return 0;
    }
    
}

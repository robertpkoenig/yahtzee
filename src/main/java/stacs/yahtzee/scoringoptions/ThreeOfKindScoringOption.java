package stacs.yahtzee.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;

public class ThreeOfKindScoringOption extends AScoringOption {

    static final String name = "Three of a kind";
    static final String description = 
        "When three dice have the same face, score is sum of all dice face values";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        int[] counts = new int[dice.size()];
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

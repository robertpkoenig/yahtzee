package stacs.yahtzee.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;

public class FourOfKindScoringOption extends AScoringOption {

    static final String name = "Twos";
    static final String description = 
        "Number of dice with value 2 multiplied by 2";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        int[] counts = new int[dice.size()];
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

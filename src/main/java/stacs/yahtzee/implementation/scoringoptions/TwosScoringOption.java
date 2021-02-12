package stacs.yahtzee.implementation.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;

public class TwosScoringOption extends AScoringOption {

    static final String name = "Twos";
    static final String description = 
        "Number of dice with value 2 multiplied by 2";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        int score = 0;
        for (IDie die : dice) {
            if (die.getCurrentFace() == 2) score += 2;
        }
        return score;
    }
    
}
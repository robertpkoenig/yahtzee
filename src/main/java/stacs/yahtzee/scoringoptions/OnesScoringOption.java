package stacs.yahtzee.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;

public class OnesScoringOption extends AScoringOption {

    static final String name = "Ones";
    static final String description = 
        "Number of dice with value 1 multiplied by 1";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        int score = 0;
        for (IDie die : dice) {
            if (die.getCurrentFace() ==1) score++;
        }
        return score;
    }
    
}

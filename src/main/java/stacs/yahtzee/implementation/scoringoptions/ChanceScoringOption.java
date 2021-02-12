package stacs.yahtzee.implementation.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;

public class ChanceScoringOption extends AScoringOption {

    static final String name = "Twos";
    static final String description = 
        "Number of dice with value 2 multiplied by 2";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        int sumOfDiceFaceValues = 0;
        for (IDie die : dice) sumOfDiceFaceValues += die.getCurrentFace();
        return sumOfDiceFaceValues;
    }
    
}

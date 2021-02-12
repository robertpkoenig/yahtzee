package stacs.yahtzee.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;

public class YahtzeeScoringOption extends AScoringOption {

    static final String name = "Yahtzee";
    static final String description = 
        "When all dice have same face value, score is 50";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        int firstDiceValue = dice.get(0).getCurrentFace();
        for (IDie die : dice) if (die.getCurrentFace() != firstDiceValue) return 0;
        return 50;
    }
    
}

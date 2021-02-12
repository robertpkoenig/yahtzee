package stacs.yahtzee.scoringoptions;

import java.util.List;

import stacs.yahtzee.IDie;

public class FullHouseScoringOption extends AScoringOption {

    static final String name = "Full House";
    static final String description = 
        "When three dice with one number, and two of another, score is 25";

    @Override
    public int calculateScoreForThisOption(List<IDie> dice) {
        int[] counts = new int[5];
        boolean threeOfKindFound = false;
        boolean twoOfKindFound = false;

        // experimenting with this logic based on Martin Fowler's lecture
        // you reccomended. He says have to loops that each do one thing, 
        // rather than one loop that does two things. Not sure about this.
        // obviously this could all be done with less code, just trying to
        // optimize readability/understandability over parsimony.
        for (IDie die : dice) {
            counts[die.getCurrentFace()]++;
        }
        
        for (int count : counts) {
            if (count == 3) threeOfKindFound = true;
            if (count == 2) twoOfKindFound = true;
        }
        return (threeOfKindFound && twoOfKindFound) ? 25 : 0;
    }
    
}

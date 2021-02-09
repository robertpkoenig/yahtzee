package stacs.yahtzee;

import java.util.List;

/**
 * Model for a player's score card in Yahtzee
 */
public interface ScoreCard {

    /**
     * Returns the list of this score card's unused scoring options
     */
    List<PlayerScoringOption> getUnusedScoringOptions();
    
    /**
     * Returns the total score for each scoring option on this score card.
     */
    int getScore();

}

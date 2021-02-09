package stacs.yahtzee;

import java.util.List;

/**
 * An implementation of the YahtzeeModel interface
 */
public class YahtzeeModel implements IYahtzeeModel {
    private List<Player> players;
    private Player activePlayer;
    private int currentRound;
    private List<Dice> dice;

    public YahtzeeModel(int numPlayers) {

    }

    @Override
    public void addDiceToGame() {
        // TODO Auto-generated method stub

    }

    
    /** 
     * @param numPlayers
     */
    @Override
    public void addPlayersToGame(int numPlayers) {
        // TODO Auto-generated method stub

    }

    
    /** 
     * @return Player
     */
    @Override
    public Player getActivePlayer() {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @return int
     */
    @Override
    public int getCurrentRound() {
        // TODO Auto-generated method stub
        return 0;
    }

    
    /** 
     * @return Player
     */
    @Override
    public Player getPlayerWithHighestScore() {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @return int
     */
    @Override
    public int getTotalRounds() {
        // TODO Auto-generated method stub
        return 0;
    }

    
    /** 
     * @return boolean
     */
    @Override
    public boolean isGameFinished() {
        // TODO Auto-generated method stub
        return false;
    }

    
    /** 
     * @return YahtzeeModel
     */
    @Override
    public YahtzeeModel reStart() {
        // TODO Auto-generated method stub
        return null;
    }

    
    /** 
     * @param playerFinishingTurn
     */
    @Override
    public void registerTurnFinished(Player playerFinishingTurn) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Dice> getDice() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Player> getPlayerList() {
        // TODO Auto-generated method stub
        return null;
    }

}

package stacs.yahtzee;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */
public class YahtzeeModelTests {
  
  YahtzeeModel model;
  int numPlayers;

  @BeforeEach
  void setup() {
    // create a new game with 6 players
    numPlayers = 6;
    model = new YahtzeeModel(numPlayers);
  }

  @Test
  void testPlayerQuantity() {
    // correct number of players added
    assertEquals(numPlayers, model.getPlayerList().size());
  }

  @Test
  void testDiceQuantity() {
    // correct number of dice added
    assertEquals(Constants.getNumberOfDice(), model.getDice().size());
  }

  @Test
  void testInitialActivePlayerHasPlayingOrderZero() {
    assertEquals(0, model.getActivePlayer().getPlayingOrder());
  } 

  @Test
  void testInitialHighestScoringPlayerIsNull() {
    assertNull(model.getPlayerWithHighestScore());
  }

  // if the activeplayer registers their turn finished, this should result in the 
  // second player being the active player
  @Test
  void testActivePlayerSwitching() {
    // when active player finishes turn, next player becomes the active player
    model.registerTurnFinished(model.getActivePlayer());
    assertEquals(model.getPlayerList().get(1), model.getActivePlayer());
  }

  // if all players register their turn finished, this should result in the 
  // first player being the active player again
  @Test
  void testActivePlayerWrapAround() {
    // when all players have their first turn, the first player is the active player again
    for (int i = 0 ; i < numPlayers ; i++) {
      // register turn ended of active player
      model.registerTurnFinished(model.getActivePlayer());
    }
    assertEquals(model.getPlayerList().get(0), model.getActivePlayer());
  }

}

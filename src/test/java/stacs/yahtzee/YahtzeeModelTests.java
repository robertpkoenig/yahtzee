package stacs.yahtzee;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;

/**
 * 
 */
public class YahtzeeModelTests {
  
  YahtzeeModel model;
  int numPlayers;

  @BeforeEach
  void setup() {
    // create a new game with mock Player and Dice objects
    numPlayers = 6;

    List<IPlayer> players = new ArrayList<>();
    for (int i = 0 ; i < numPlayers ; i++) {
      IPlayer newPlayer = Mockito.mock(IPlayer.class);
      Mockito.when(newPlayer.getPlayingOrder()).thenReturn(i);
      players.add(newPlayer);
    }

    List<IDice> dice = new ArrayList<>();
    for (int i = 0 ; i < Constants.getNumberOfDice() ; i++) {
      IDice newDie = Mockito.mock(IDice.class);
      dice.add(newDie);
    }

    model = new YahtzeeModel(players, dice);
  }

  @Test
  void testPlayerQuantity() {
    assertEquals(numPlayers, model.getPlayerList().size());
  }

  @Test
  void testDiceQuantity() {
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

  @Test
  void testActivePlayerSwitching() {
    // if the activeplayer registers their turn finished, this should result in the 
    // second player being the active player
    model.registerTurnFinished(model.getActivePlayer());
    assertEquals(model.getPlayerList().get(1), model.getActivePlayer());
  }

  @Test
  void testActivePlayerWrapAround() {
    // if all players register their turn finished, this should result in the 
    // first player being the active player again
    for (int i = 0 ; i < numPlayers ; i++) {
      model.registerTurnFinished(model.getActivePlayer());
    }
    assertEquals(model.getPlayerList().get(0), model.getActivePlayer());
  }

  @Test
  void testRoundIncrement() {
    // when all players take their turn, the round is incremented by 1
    for (int i = 0 ; i < numPlayers ; i++) {
      model.registerTurnFinished(model.getActivePlayer());
    }
    assertEquals(1, model.getCurrentRound());
  }

  @Test
  void testGameEndsAfterAllPlayersAndAllRounds() {
    for (int i = 0 ; i < Constants.getNumberOfRounds() ; i++) {
      for (int j = 0 ; j < numPlayers ; j++) {
        model.registerTurnFinished(model.getActivePlayer());
      }  
    }
    assertEquals(true, model.isDone());
  }

}

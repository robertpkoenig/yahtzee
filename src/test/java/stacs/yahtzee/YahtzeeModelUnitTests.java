package stacs.yahtzee;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;

import stacs.yahtzee.implementation.*;

/**
 * 
 */
public class YahtzeeModelUnitTests {
  
  YahtzeeModel model;
  int numPlayers;
  List<IPlayer> players;
  List<IDie> dice;

  @BeforeEach
  void setup() {
    // create a new game with mock Player and Dice objects
    numPlayers = 6;

    players = new ArrayList<>();
    for (int i = 0 ; i < numPlayers ; i++) {
      IPlayer newPlayer = Mockito.mock(IPlayer.class);
      // return i as the player order and the player score
      Mockito.when(newPlayer.getPlayingOrder()).thenReturn(i);
      players.add(newPlayer);
    }

    dice = new ArrayList<>();
    for (int i = 0 ; i < Constants.numberOfDice ; i++) {
      IDie newDie = Mockito.mock(IDie.class);
      dice.add(newDie);
    }

    model = new YahtzeeModel();
    model.setPlayers(players);
    model.setDice(dice);
    model.setActivePlayer(0);
  }

  @Test
  void testPlayerQuantity() {
    assertEquals(numPlayers, model.getPlayerList().size());
  }

  @Test
  void testDiceQuantity() {
    assertEquals(Constants.numberOfDice, model.getDice().size());
  }

  @Test
  void testInitialActivePlayerHasPlayingOrderZero() {
    assertEquals(0, model.getActivePlayer().getPlayingOrder());
  } 

  @Test
  void testInitialHighestScoringPlayerListIsEmpty() {
    for (int i = 0 ; i < players.size() ; i++) {
      IScoreCard scoreCard = Mockito.mock(IScoreCard.class);
      Mockito.when(scoreCard.getTotalScore()).thenReturn(0);
      Mockito.when(players.get(i).getScoreCard()).thenReturn(scoreCard);
    }
    assertEquals(0, model.getPlayersWithHighestScore().size());
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
    for (int i = 0 ; i < Constants.numberOfRounds ; i++) {
      for (int j = 0 ; j < numPlayers ; j++) {
        model.registerTurnFinished(model.getActivePlayer());
      }  
    }
    assertEquals(true, model.isDone());
  }

  @Test
  void testPlayerListIsReturned() {
    assertEquals(players, model.getPlayerList());
  }

  @Test
  void testDiceListIsReturned() {
    assertEquals(dice, model.getDice());
  }

  @Test
  void testGetSinglePlayerWithHighestScore() {
    // the mock player objects are instructed to return their player
    // order as their score, so the highest score should be player 5 with 5
    for (int i = 0 ; i < players.size() ; i++) {
      IScoreCard scoreCard = Mockito.mock(IScoreCard.class);
      Mockito.when(scoreCard.getTotalScore()).thenReturn(i);
      Mockito.when(players.get(i).getScoreCard()).thenReturn(scoreCard);
    }
    assertEquals(players.get(numPlayers - 1), model.getPlayersWithHighestScore().get(0));
  }

  @Test
  void testGetSixPlayersWithHighestScore() {
    for (int i = 0 ; i < players.size() ; i++) {
      IScoreCard scoreCard = Mockito.mock(IScoreCard.class);
      Mockito.when(scoreCard.getTotalScore()).thenReturn(10);
      Mockito.when(players.get(i).getScoreCard()).thenReturn(scoreCard);
    }
    assertEquals(6, model.getPlayersWithHighestScore().size());
  }

  @Test
  void testGetWinnerWhenGameIsDone() {
    // play the game through
    for (int i = 0 ; i < Constants.numberOfRounds ; i++) {
      for (int j = 0 ; j < numPlayers ; j++) {
        model.registerTurnFinished(model.getActivePlayer());
      }  
    }
    for (int i = 0 ; i < players.size() ; i++) {
      IScoreCard scoreCard = Mockito.mock(IScoreCard.class);
      Mockito.when(scoreCard.getTotalScore()).thenReturn(i);
      Mockito.when(players.get(i).getScoreCard()).thenReturn(scoreCard);
    }
    assertEquals(players.get(numPlayers - 1), model.getWinners().get(0));
  }

  @Test
  void testGetWinnerBeforeGameIsDone() {
    for (int i = 0 ; i < players.size() ; i++) {
      IScoreCard scoreCard = Mockito.mock(IScoreCard.class);
      Mockito.when(scoreCard.getTotalScore()).thenReturn(i);
      Mockito.when(players.get(i).getScoreCard()).thenReturn(scoreCard);
    }
    assertThrows(IllegalStateException.class, () -> {
      model.getWinners();
    });
  } 
}

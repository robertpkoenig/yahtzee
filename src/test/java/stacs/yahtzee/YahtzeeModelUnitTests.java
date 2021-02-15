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
  
  YahtzeeModel game;
  int numPlayers;
  List<IPlayer> players;
  List<IDie> dice;

  @BeforeEach
  void setup() {
    // create a new game with mock Player and Dice objects
    game = new YahtzeeModel();
    numPlayers = 6;

    players = new ArrayList<>();
    for (int i = 0 ; i < numPlayers ; i++) {
      IPlayer newPlayer = Mockito.mock(IPlayer.class);
      Mockito.when(newPlayer.getPlayingOrder()).thenReturn(i);
      IScoreCard scoreCard = Mockito.mock(IScoreCard.class);
      Mockito.when(newPlayer.getScoreCard()).thenReturn(scoreCard);
      @SuppressWarnings( "unchecked" )
      List<IScoringOption> usedScoringOptions = Mockito.mock(List.class);
      Mockito.when(usedScoringOptions.size()).thenReturn(1);
      Mockito.when(scoreCard.getUsedScoringOptions()).thenReturn(usedScoringOptions);
      players.add(newPlayer);
      game.addPlayer(newPlayer);
    }

    dice = new ArrayList<>();
    for (int i = 0 ; i < Constants.numberOfDice ; i++) {
      IDie newDie = Mockito.mock(IDie.class);
      dice.add(newDie);
      game.addDie(newDie);
    }

    game.setActivePlayer(0);
  }

  @Test
  void testPlayerQuantity() {
    assertEquals(numPlayers, game.getPlayerList().size());
  }

  @Test
  void testDiceQuantity() {
    assertEquals(Constants.numberOfDice, game.getDice().size());
  }

  @Test
  void testInitialActivePlayerHasPlayingOrderZero() {
    assertEquals(0, game.getActivePlayer().getPlayingOrder());
  } 

  @Test
  void testInitialHighestScoringPlayerListIsEmpty() {
    for (int i = 0 ; i < players.size() ; i++) {
      IScoreCard scoreCard = Mockito.mock(IScoreCard.class);
      Mockito.when(scoreCard.getTotalScore()).thenReturn(0);
      Mockito.when(players.get(i).getScoreCard()).thenReturn(scoreCard);
    }
    assertEquals(0, game.getPlayersWithHighestScore().size());
  }

  @Test
  void testActivePlayerSwitching() {
    // if the activeplayer registers their turn finished, this should result in the 
    // second player being the active player
    game.registerTurnFinished(game.getActivePlayer());
    assertEquals(game.getPlayerList().get(1), game.getActivePlayer());
  }

  @Test
  void testActivePlayerWrapAround() {
    // if all players register their turn finished, this should result in the 
    // first player being the active player again
    for (int i = 0 ; i < numPlayers ; i++) {
      game.registerTurnFinished(game.getActivePlayer());
    }
    assertEquals(game.getPlayerList().get(0), game.getActivePlayer());
  }

  @Test
  void testRoundIncrement() {
    // when all players take their turn, the round is incremented by 1
    for (int i = 0 ; i < numPlayers ; i++) {
      game.registerTurnFinished(game.getActivePlayer());
    }
    assertEquals(1, game.getCurrentRound());
  }

  @Test
  void testGameEndsAfterAllPlayersAndAllRounds() {
    for (int i = 0 ; i < Constants.numberOfRounds ; i++) {
      for (int j = 0 ; j < numPlayers ; j++) {
        Mockito.when(game.getActivePlayer().getScoreCard()
          .getUsedScoringOptions().size()).thenReturn(i);
        game.registerTurnFinished(game.getActivePlayer());
      }  
    }
    assertEquals(true, game.isDone());
  }

  @Test
  void testPlayerListIsReturned() {
    assertEquals(players, game.getPlayerList());
  }

  @Test
  void testDiceListIsReturned() {
    assertEquals(dice, game.getDice());
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
    assertEquals(players.get(numPlayers - 1), game.getPlayersWithHighestScore().get(0));
  }

  @Test
  void testGetSixPlayersWithHighestScore() {
    for (int i = 0 ; i < players.size() ; i++) {
      IScoreCard scoreCard = Mockito.mock(IScoreCard.class);
      Mockito.when(scoreCard.getTotalScore()).thenReturn(10);
      Mockito.when(players.get(i).getScoreCard()).thenReturn(scoreCard);
    }
    assertEquals(6, game.getPlayersWithHighestScore().size());
  }

  @Test
  void testGetWinnerWhenGameIsDone() {
    // play the game through
    for (int i = 0 ; i < Constants.numberOfRounds ; i++) {
      for (int j = 0 ; j < numPlayers ; j++) {
        Mockito.when(game.getActivePlayer().getScoreCard()
          .getUsedScoringOptions().size()).thenReturn(i);
        game.registerTurnFinished(game.getActivePlayer());
      }  
    }
    for (int i = 0 ; i < players.size() ; i++) {
      IScoreCard scoreCard = Mockito.mock(IScoreCard.class);
      Mockito.when(scoreCard.getTotalScore()).thenReturn(i);
      Mockito.when(players.get(i).getScoreCard()).thenReturn(scoreCard);
    }
    assertEquals(players.get(numPlayers - 1), game.getWinners().get(0));
  }

  @Test
  void testGetWinnerBeforeGameIsDone() {
    for (int i = 0 ; i < players.size() ; i++) {
      IScoreCard scoreCard = Mockito.mock(IScoreCard.class);
      Mockito.when(scoreCard.getTotalScore()).thenReturn(i);
      Mockito.when(players.get(i).getScoreCard()).thenReturn(scoreCard);
    }
    assertThrows(IllegalStateException.class, () -> {
      game.getWinners();
    });
  } 
}

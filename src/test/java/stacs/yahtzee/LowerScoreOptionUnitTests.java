package stacs.yahtzee;

import stacs.yahtzee.scoringoptions.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.*;

public class LowerScoreOptionUnitTests {

    static IScoringOption threeOfKind;
    static IScoringOption fourOfKind;
    static IScoringOption fullHouse;
    static IScoringOption smallStraight;
    static IScoringOption largeStraight;
    static IScoringOption chance;
    static IScoringOption yahtzee;

    static List<IDie> dice;

    @BeforeAll
    static void setup() {
        threeOfKind = new ThreeOfKindScoringOption();
        fourOfKind = new FourOfKindScoringOption();
        fullHouse = new FullHouseScoringOption();
        smallStraight = new SmallStraightScoringOption();
        largeStraight = new LargeStraightScoringOption();
        chance = new ChanceScoringOption();
        yahtzee = new YahtzeeScoringOption();
    }

    @Test
    void testThreeOfKindTrue() {
        dice = new ArrayList<>();
        IDie newDie1 = Mockito.mock(IDie.class);
        Mockito.when(newDie1.getCurrentFace()).thenReturn(1);
        dice.add(newDie1);
        IDie newDie2 = Mockito.mock(IDie.class);
        Mockito.when(newDie2.getCurrentFace()).thenReturn(1);
        dice.add(newDie2); 
        IDie newDie3 = Mockito.mock(IDie.class);
        Mockito.when(newDie3.getCurrentFace()).thenReturn(1);
        dice.add(newDie3); 
        IDie newDie4 = Mockito.mock(IDie.class);
        Mockito.when(newDie4.getCurrentFace()).thenReturn(2);
        dice.add(newDie4); 
        IDie newDie5 = Mockito.mock(IDie.class);
        Mockito.when(newDie5.getCurrentFace()).thenReturn(2);
        dice.add(newDie5);
        // returned value should be sum of all dice faces
        assertEquals(7, threeOfKind.calculateScoreForThisOption(dice));
    }

    @Test
    void testThreeOfKindFalse() {
        dice = new ArrayList<>();
        IDie newDie1 = Mockito.mock(IDie.class);
        Mockito.when(newDie1.getCurrentFace()).thenReturn(4);
        dice.add(newDie1);
        IDie newDie2 = Mockito.mock(IDie.class);
        Mockito.when(newDie2.getCurrentFace()).thenReturn(1);
        dice.add(newDie2); 
        IDie newDie3 = Mockito.mock(IDie.class);
        Mockito.when(newDie3.getCurrentFace()).thenReturn(1);
        dice.add(newDie3); 
        IDie newDie4 = Mockito.mock(IDie.class);
        Mockito.when(newDie4.getCurrentFace()).thenReturn(2);
        dice.add(newDie4); 
        IDie newDie5 = Mockito.mock(IDie.class);
        Mockito.when(newDie5.getCurrentFace()).thenReturn(2);
        dice.add(newDie5);
        assertEquals(0, threeOfKind.calculateScoreForThisOption(dice));
    }

    @Test
    void testFourOfKindTrue() {
        dice = new ArrayList<>();
        IDie newDie1 = Mockito.mock(IDie.class);
        Mockito.when(newDie1.getCurrentFace()).thenReturn(1);
        dice.add(newDie1);
        IDie newDie2 = Mockito.mock(IDie.class);
        Mockito.when(newDie2.getCurrentFace()).thenReturn(1);
        dice.add(newDie2); 
        IDie newDie3 = Mockito.mock(IDie.class);
        Mockito.when(newDie3.getCurrentFace()).thenReturn(1);
        dice.add(newDie3); 
        IDie newDie4 = Mockito.mock(IDie.class);
        Mockito.when(newDie4.getCurrentFace()).thenReturn(1);
        dice.add(newDie4); 
        IDie newDie5 = Mockito.mock(IDie.class);
        Mockito.when(newDie5.getCurrentFace()).thenReturn(2);
        dice.add(newDie5);
        // returned value should be sum of all dice faces
        assertEquals(6, fourOfKind.calculateScoreForThisOption(dice));
    }

    @Test
    void testFourOfKindFalse() {
        dice = new ArrayList<>();
        IDie newDie1 = Mockito.mock(IDie.class);
        Mockito.when(newDie1.getCurrentFace()).thenReturn(1);
        dice.add(newDie1);
        IDie newDie2 = Mockito.mock(IDie.class);
        Mockito.when(newDie2.getCurrentFace()).thenReturn(1);
        dice.add(newDie2); 
        IDie newDie3 = Mockito.mock(IDie.class);
        Mockito.when(newDie3.getCurrentFace()).thenReturn(1);
        dice.add(newDie3); 
        IDie newDie4 = Mockito.mock(IDie.class);
        Mockito.when(newDie4.getCurrentFace()).thenReturn(2);
        dice.add(newDie4); 
        IDie newDie5 = Mockito.mock(IDie.class);
        Mockito.when(newDie5.getCurrentFace()).thenReturn(2);
        dice.add(newDie5);
        // returned value should be sum of all dice faces
        assertEquals(0, fourOfKind.calculateScoreForThisOption(dice));
    }

    @Test
    void testFullHouseTrue() {
        dice = new ArrayList<>();
        IDie newDie1 = Mockito.mock(IDie.class);
        Mockito.when(newDie1.getCurrentFace()).thenReturn(1);
        dice.add(newDie1);
        IDie newDie2 = Mockito.mock(IDie.class);
        Mockito.when(newDie2.getCurrentFace()).thenReturn(1);
        dice.add(newDie2); 
        IDie newDie3 = Mockito.mock(IDie.class);
        Mockito.when(newDie3.getCurrentFace()).thenReturn(1);
        dice.add(newDie3); 
        IDie newDie4 = Mockito.mock(IDie.class);
        Mockito.when(newDie4.getCurrentFace()).thenReturn(2);
        dice.add(newDie4); 
        IDie newDie5 = Mockito.mock(IDie.class);
        Mockito.when(newDie5.getCurrentFace()).thenReturn(2);
        dice.add(newDie5);
        // returned value should be sum of all dice faces
        assertEquals(25, fullHouse.calculateScoreForThisOption(dice));
    }

    @Test
    void testFullHouseFalse() {
        dice = new ArrayList<>();
        IDie newDie1 = Mockito.mock(IDie.class);
        Mockito.when(newDie1.getCurrentFace()).thenReturn(1);
        dice.add(newDie1);
        IDie newDie2 = Mockito.mock(IDie.class);
        Mockito.when(newDie2.getCurrentFace()).thenReturn(1);
        dice.add(newDie2); 
        IDie newDie3 = Mockito.mock(IDie.class);
        Mockito.when(newDie3.getCurrentFace()).thenReturn(4);
        dice.add(newDie3); 
        IDie newDie4 = Mockito.mock(IDie.class);
        Mockito.when(newDie4.getCurrentFace()).thenReturn(2);
        dice.add(newDie4); 
        IDie newDie5 = Mockito.mock(IDie.class);
        Mockito.when(newDie5.getCurrentFace()).thenReturn(2);
        dice.add(newDie5);
        // returned value should be sum of all dice faces
        assertEquals(0, fullHouse.calculateScoreForThisOption(dice));
    }

    @Test
    void testSmallStraightTrue() {
        dice = new ArrayList<>();
        IDie newDie1 = Mockito.mock(IDie.class);
        Mockito.when(newDie1.getCurrentFace()).thenReturn(1);
        dice.add(newDie1);
        IDie newDie2 = Mockito.mock(IDie.class);
        Mockito.when(newDie2.getCurrentFace()).thenReturn(2);
        dice.add(newDie2); 
        IDie newDie3 = Mockito.mock(IDie.class);
        Mockito.when(newDie3.getCurrentFace()).thenReturn(3);
        dice.add(newDie3); 
        IDie newDie4 = Mockito.mock(IDie.class);
        Mockito.when(newDie4.getCurrentFace()).thenReturn(4);
        dice.add(newDie4); 
        IDie newDie5 = Mockito.mock(IDie.class);
        Mockito.when(newDie5.getCurrentFace()).thenReturn(2);
        dice.add(newDie5);
        // returned value should be sum of all dice faces
        assertEquals(30, smallStraight.calculateScoreForThisOption(dice));
    }

    @Test
    void testSmallStraightFalse() {
        dice = new ArrayList<>();
        IDie newDie1 = Mockito.mock(IDie.class);
        Mockito.when(newDie1.getCurrentFace()).thenReturn(1);
        dice.add(newDie1);
        IDie newDie2 = Mockito.mock(IDie.class);
        Mockito.when(newDie2.getCurrentFace()).thenReturn(2);
        dice.add(newDie2); 
        IDie newDie3 = Mockito.mock(IDie.class);
        Mockito.when(newDie3.getCurrentFace()).thenReturn(2);
        dice.add(newDie3); 
        IDie newDie4 = Mockito.mock(IDie.class);
        Mockito.when(newDie4.getCurrentFace()).thenReturn(4);
        dice.add(newDie4); 
        IDie newDie5 = Mockito.mock(IDie.class);
        Mockito.when(newDie5.getCurrentFace()).thenReturn(2);
        dice.add(newDie5);
        // returned value should be sum of all dice faces
        assertEquals(0, smallStraight.calculateScoreForThisOption(dice));
    }


    @Test
    void testLargeStraightTrue() {
        dice = new ArrayList<>();
        IDie newDie1 = Mockito.mock(IDie.class);
        Mockito.when(newDie1.getCurrentFace()).thenReturn(1);
        dice.add(newDie1);
        IDie newDie2 = Mockito.mock(IDie.class);
        Mockito.when(newDie2.getCurrentFace()).thenReturn(2);
        dice.add(newDie2); 
        IDie newDie3 = Mockito.mock(IDie.class);
        Mockito.when(newDie3.getCurrentFace()).thenReturn(3);
        dice.add(newDie3); 
        IDie newDie4 = Mockito.mock(IDie.class);
        Mockito.when(newDie4.getCurrentFace()).thenReturn(4);
        dice.add(newDie4); 
        IDie newDie5 = Mockito.mock(IDie.class);
        Mockito.when(newDie5.getCurrentFace()).thenReturn(5);
        dice.add(newDie5);
        // returned value should be sum of all dice faces
        assertEquals(40, largeStraight.calculateScoreForThisOption(dice));
    }

    @Test
    void testLargeStraightFalse() {
        dice = new ArrayList<>();
        IDie newDie1 = Mockito.mock(IDie.class);
        Mockito.when(newDie1.getCurrentFace()).thenReturn(1);
        dice.add(newDie1);
        IDie newDie2 = Mockito.mock(IDie.class);
        Mockito.when(newDie2.getCurrentFace()).thenReturn(2);
        dice.add(newDie2); 
        IDie newDie3 = Mockito.mock(IDie.class);
        Mockito.when(newDie3.getCurrentFace()).thenReturn(3);
        dice.add(newDie3); 
        IDie newDie4 = Mockito.mock(IDie.class);
        Mockito.when(newDie4.getCurrentFace()).thenReturn(6);
        dice.add(newDie4); 
        IDie newDie5 = Mockito.mock(IDie.class);
        Mockito.when(newDie5.getCurrentFace()).thenReturn(5);
        dice.add(newDie5);
        // returned value should be sum of all dice faces
        assertEquals(0, largeStraight.calculateScoreForThisOption(dice));
    }

    @Test
    void testChanceTrue() {
        dice = new ArrayList<>();
        IDie newDie1 = Mockito.mock(IDie.class);
        Mockito.when(newDie1.getCurrentFace()).thenReturn(2);
        dice.add(newDie1);
        IDie newDie2 = Mockito.mock(IDie.class);
        Mockito.when(newDie2.getCurrentFace()).thenReturn(2);
        dice.add(newDie2); 
        IDie newDie3 = Mockito.mock(IDie.class);
        Mockito.when(newDie3.getCurrentFace()).thenReturn(2);
        dice.add(newDie3); 
        IDie newDie4 = Mockito.mock(IDie.class);
        Mockito.when(newDie4.getCurrentFace()).thenReturn(2);
        dice.add(newDie4); 
        IDie newDie5 = Mockito.mock(IDie.class);
        Mockito.when(newDie5.getCurrentFace()).thenReturn(2);
        dice.add(newDie5);
        // returned value should be sum of all dice faces
        assertEquals(10, chance.calculateScoreForThisOption(dice));
    }

    @Test
    void testYahtzeeTrue() {
        dice = new ArrayList<>();
        IDie newDie1 = Mockito.mock(IDie.class);
        Mockito.when(newDie1.getCurrentFace()).thenReturn(2);
        dice.add(newDie1);
        IDie newDie2 = Mockito.mock(IDie.class);
        Mockito.when(newDie2.getCurrentFace()).thenReturn(2);
        dice.add(newDie2); 
        IDie newDie3 = Mockito.mock(IDie.class);
        Mockito.when(newDie3.getCurrentFace()).thenReturn(2);
        dice.add(newDie3); 
        IDie newDie4 = Mockito.mock(IDie.class);
        Mockito.when(newDie4.getCurrentFace()).thenReturn(2);
        dice.add(newDie4); 
        IDie newDie5 = Mockito.mock(IDie.class);
        Mockito.when(newDie5.getCurrentFace()).thenReturn(2);
        dice.add(newDie5);
        // returned value should be sum of all dice faces
        assertEquals(50, yahtzee.calculateScoreForThisOption(dice));
    }

    @Test
    void testYahtzeeFalse() {
        dice = new ArrayList<>();
        IDie newDie1 = Mockito.mock(IDie.class);
        Mockito.when(newDie1.getCurrentFace()).thenReturn(4);
        dice.add(newDie1);
        IDie newDie2 = Mockito.mock(IDie.class);
        Mockito.when(newDie2.getCurrentFace()).thenReturn(2);
        dice.add(newDie2); 
        IDie newDie3 = Mockito.mock(IDie.class);
        Mockito.when(newDie3.getCurrentFace()).thenReturn(2);
        dice.add(newDie3); 
        IDie newDie4 = Mockito.mock(IDie.class);
        Mockito.when(newDie4.getCurrentFace()).thenReturn(2);
        dice.add(newDie4); 
        IDie newDie5 = Mockito.mock(IDie.class);
        Mockito.when(newDie5.getCurrentFace()).thenReturn(2);
        dice.add(newDie5);
        // returned value should be sum of all dice faces
        assertEquals(0, yahtzee.calculateScoreForThisOption(dice));
    }
}

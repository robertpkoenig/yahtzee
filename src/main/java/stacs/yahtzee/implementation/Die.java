package stacs.yahtzee.implementation;

import stacs.yahtzee.IDie;

import java.util.Random;

public class Die implements IDie {

    private int currentFace;
    private Random randomGenerator;

    public Die() {
        this.currentFace = -1;
        this.randomGenerator = new Random();
    }

    @Override
    public int getCurrentFace() {
        return this.currentFace;
    }

    @Override
    public void roll() {
        this.currentFace = randomGenerator.nextInt(6) + 1;
    }
    
}

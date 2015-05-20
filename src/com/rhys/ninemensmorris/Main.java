package com.rhys.ninemensmorris;

import com.rhys.ninemensmorris.View.Display;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Display display = new Display();

        //display.drawBoard();

        int testTurnNum = 50;

        for (int i = 0; i < testTurnNum; i++) {
            String[] possibleSpots = display.getBoard().getPossibleSpots();

            Random rand = new Random();
            int randInt = rand.nextInt(possibleSpots.length);

            display.move(possibleSpots[randInt]);
        }
    }
}

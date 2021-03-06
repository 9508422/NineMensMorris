package com.rhys.ninemensmorris;

import com.rhys.ninemensmorris.Controller.Game;

/**
 * @author Rhys Gevaux
 * @version 2015.05.31
 */
class Main {
    /**
     * Creates and starts game
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        Game game = Game.getInstance();
        game.play();
    }
}

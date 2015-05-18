package com.rhys.ninemensmorris.View;

import com.rhys.ninemensmorris.Controller.Game;
import com.rhys.ninemensmorris.Model.Board;

/**
 * Created by Rhys on 14/05/2015.
 */
public class Display {
    private Board board;
    private Game game;

    public Display() {
        this.board = new Board();
        this.game = new Game(board);
    }

    public void place(String destStr) {
        game.place(destStr);
    }

    public void drawBoard() {
        for (String spot : board.getPossibleSpots()) {
            System.out.print(board.getSpot(spot).toString());
        }
    }
}

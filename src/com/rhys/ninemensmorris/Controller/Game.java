package com.rhys.ninemensmorris.Controller;

import com.rhys.ninemensmorris.Model.Board;
import com.rhys.ninemensmorris.Model.Move;
import com.rhys.ninemensmorris.Model.Player;

import java.util.Stack;

/**
 * Created by Rhys on 14/05/2015.
 */
public class Game {
    private Stack<Move> moveStack;
    private Player[] players;
    private Board board;
    private int turn;

    public Game(Board board) {
        this.moveStack = new Stack<Move>();
        this.players = new Player[]{new Player("W"), new Player("B")};
        this.board = board;
        turn = 0;
    }

    public void place(String destStr) {
        moveStack.add(players[turn].place(board.getSpot(destStr)));
    }

    private void changeTurn() {
        if (turn == 0) {
            turn = 1;
        } else {
            turn = 0;
        }
    }
}

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
        this.game = new Game(board, "Rhys", "Abi");
    }

    public void move(String destStr) {
        System.out.println(game.move(destStr.toLowerCase()));
        drawBoard();
    }

    public void move(String sourceStr, String destStr) {
        System.out.println(game.move(sourceStr.toLowerCase(), destStr.toLowerCase()));
        drawBoard();
    }

    public Board getBoard() {
        return board;
    }

    public void drawBoard() {
        System.out.println("7 " + board.getSpot("a7") + " - - " + board.getSpot("d7") + " - - " + board.getSpot("g7"));
        System.out.println("6 | " + board.getSpot("b6") + " - " + board.getSpot("d6") + " - " + board.getSpot("f6") +
                " |");
        System.out.println("5 | | " + board.getSpot("c5") + " " + board.getSpot("d5") + " " + board.getSpot("e5") +
                " | |");
        System.out.println("4 " + board.getSpot("a4") + " " + board.getSpot("b4") + " " + board.getSpot("c4") + "   " +
                board.getSpot("e4") + " " + board.getSpot("f4") + " " + board.getSpot("g4"));
        System.out.println("3 | | " + board.getSpot("c3") + " " + board.getSpot("d3") + " " + board.getSpot("e3") +
                " | |");
        System.out.println("2 | " + board.getSpot("b2") + " - " + board.getSpot("d2") + " - " + board.getSpot("f2") +
                " |");
        System.out.println("1 " + board.getSpot("a1") + " - - " + board.getSpot("d1") + " - - " + board.getSpot("g1"));
        System.out.println("  a b c d e f g\n");
    }
}

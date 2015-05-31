package com.rhys.ninemensmorris.View;

import com.rhys.ninemensmorris.Model.Board;

/**
 * @author Rhys Gevaux
 * @version 2015.05.31
 */
public class Display {
    private static final Display INSTANCE = new Display();

    private final Board board;

    /**
     * The Display constructor
     */
    private Display() {
        this.board = Board.getInstance();
    }

    /**
     * @return Instance of the Display
     */
    public static Display getInstance() {
        return INSTANCE;
    }

    /**
     * @param output desired output for console
     */
    public void out(String output) {
        System.out.print(output);
    }

    /**
     *
     */
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
        System.out.println("  a b c d e f g");
    }
}

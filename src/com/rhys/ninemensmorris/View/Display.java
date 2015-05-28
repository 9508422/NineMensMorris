package com.rhys.ninemensmorris.View;

import com.rhys.ninemensmorris.Model.Board;

/**
 * @author Rhys Gevaux
 * @version 2015.05.27
 */
public class Display {
	private final Board board;

	/**
	 * @param board
	 */
	public Display(Board board) {
		this.board = board;
	}

	/**
	 * @param output
	 */
	public void out(String output) {
		System.out.print(output);
	}

	/**
	 *
	 */
	public void drawBoard() {
		int kb = 1024;
		Runtime runtime = Runtime.getRuntime();
		System.out.println("Used Memory:" + (runtime.totalMemory() - runtime.freeMemory()) / kb + " KB");


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

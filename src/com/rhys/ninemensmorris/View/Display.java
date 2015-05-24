package com.rhys.ninemensmorris.View;

import com.rhys.ninemensmorris.Controller.Game;
import com.rhys.ninemensmorris.Model.Board;

import java.util.Scanner;

/**
 * Created by Rhys on 14/05/2015.
 */
public class Display {
    private final Game game;
    private final Board board;

    public Display(Game game, Board board) {
        this.game = game;
        this.board = board;
        play();
    }

    public void play() {
        Scanner in = new Scanner(System.in);
        String input;

        System.out.print("Player one name: ");
        game.addPlayer(in.next().trim());
        System.out.print("Player two name: ");
        game.addPlayer(in.next().trim());

        game.start();

        while (game.getGameState() != Game.STATE_COMPLETE) {
            drawBoard();

            if (game.getGameState() == Game.STATE_PLACE) {
                System.out.print(game.getCurrentPlayer().toString("name") + ", place piece (xy): ");
            } else if (game.getGameState() == Game.STATE_REMOVE) {
                System.out.print(game.getCurrentPlayer().toString("name") + ", remove one of " + game.getOtherPlayer().toString("name") + "'s pieces (xy): ");
            } else if (game.getGameState() == Game.STATE_SLIDE) {
                System.out.print(game.getCurrentPlayer().toString("name") + ", slide one of your pieces (x1y1->x2y2): ");
            } else if (game.getGameState() == Game.STATE_FLY) {
                System.out.print(game.getCurrentPlayer().toString("name") + ", fly one of your pieces (x1y1->x2y2): ");
            }

            input = in.next().trim().toLowerCase();
            if (input.equals("stop")) {
                System.out.println("Game stopped by " + game.getCurrentPlayer().toString("name"));
                in.close();
                return;
            } else if (input.equals("undo")) {
                System.out.println(game.undo());
            } else {
                if (input.length() > 2) {
                    String[] str = input.split("->");
                    System.out.println(game.move(str[0], str[1]));
                } else {
                    System.out.println(game.move(input));
                }
            }
        }
        in.close();
        System.out.println("Game complete! " + game.getOtherPlayer().toString("name") + " won!");
    }

    private void drawBoard() {
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

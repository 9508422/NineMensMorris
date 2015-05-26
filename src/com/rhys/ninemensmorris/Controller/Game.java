package com.rhys.ninemensmorris.Controller;

import com.rhys.ninemensmorris.Model.Board;
import com.rhys.ninemensmorris.Model.Human;
import com.rhys.ninemensmorris.Model.Move;
import com.rhys.ninemensmorris.Model.Player;
import com.rhys.ninemensmorris.View.Display;

import java.util.Scanner;
import java.util.Stack;

/**
 * Created by Rhys on 14/05/2015.
 */
public class Game {
    public static final int STATE_PLACE = 0;
    public static final int STATE_REMOVE = 1;
    public static final int STATE_SLIDE = 2;
    public static final int STATE_FLY = 3;
    private static final int STATE_COMPLETE = 4;

    private final Board board;
    private final Stack<Move> moveStack;
    private final Display display;

    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;

    private int gameState;

    /**
     *
     */
    public Game() {
        this.moveStack = new Stack<Move>();
        this.board = new Board();
        this.playerOne = null;
        this.playerTwo = null;
        this.currentPlayer = null;
        display = new Display(board);
    }

    /**
     * @param destStr
     */
    private void setGameState(String destStr) {
        if (destStr != null && board.pieceInMill(board.getSpot(destStr))) {
            gameState = STATE_REMOVE;
        } else if (!currentPlayer.hasAllPiecesPlaced()) {
            gameState = STATE_PLACE;
        } else if (currentPlayer.hasThreePiecesLeft()) {
            gameState = STATE_FLY;
        } else if (currentPlayer.hasNoLegalMove() || currentPlayer.hasTwoPiecesLeft()) {
            gameState = STATE_COMPLETE;
        } else {
            gameState = STATE_SLIDE;
        }
    }

    /**
     *
     */
    private void setGameState() {
        setGameState(null);
    }

    /**
     *
     * @return
     */
    Player getOtherPlayer() {
        if (currentPlayer.equals(playerOne)) {
            return playerTwo;
        } else {
            return playerOne;
        }
    }

    /**
     *
     */
    public void play() {
        Scanner in = new Scanner(System.in);
        display.out("Player one's name: ");
        playerOne = new Human(in.next().trim());
        display.out("Player two's name: ");
        playerTwo = new Human(in.next().trim());
        currentPlayer = playerOne;

        setGameState();
        display.drawBoard();

        while (gameState != STATE_COMPLETE) {
            String input;

            switch (gameState) {
                case STATE_PLACE:
                    display.out(currentPlayer.toString("name") + ", place a piece (x1y1): ");
                    break;
                case STATE_REMOVE:
                    display.out(currentPlayer.toString("name") + ", remove one of " +
                            getOtherPlayer().toString("name") + "'s pieces (x1y1): ");
                    break;
                case STATE_SLIDE:
                    display.out(currentPlayer.toString("name") + ", slide a piece (x1y1->x2y2): ");
                    break;
                case STATE_FLY:
                    display.out(currentPlayer.toString("name") + ", fly a piece (x1y1->x2y2): ");
                    break;
            }

            input = in.next().trim().toLowerCase();
            if (input.equals("stop")) {
                display.out("Game stopped by " + currentPlayer + "\n");
                System.exit(1);
            } else if (input.equals("undo")) {
                display.out(undo());
            } else if (input.length() == 2) {
                if (board.hasSpot(input)) {
                    move(null, input);
                } else {
                    display.out("Move invalid.\n");
                }
            } else if (input.length() == 6 && input.contains("->")) {
                String splitStr[] = input.split("->");
                if (board.hasSpots(splitStr)) {
                    move(splitStr[0], splitStr[1]);
                } else {
                    display.out("Move invalid.\n");
                }
            } else {
                display.out("Invalid input.\n");
            }

            display.drawBoard();
        }
        display.out(getOtherPlayer().toString("name") + " won!");
    }

    /**
     *
     * @param srcStr
     * @param destStr
     */
    void move(String srcStr, String destStr) {
        Move move = currentPlayer.move(gameState, board.getSpot(srcStr), board.getSpot(destStr));
        if (move != null) {
            moveStack.push(move);
            display.out("Move successful.\n");

            if (board.pieceInMill(board.getSpot(destStr))) {
                gameState = STATE_REMOVE;
                display.out("Mill created!\n");
            } else {
                changeTurn();
                setGameState();
            }
        } else {
            display.out("Move failed, try again.\n");
        }
    }

    /**
     *
     * @return
     */
    String undo() {
        if (moveStack.size() > 0) {
            currentPlayer = moveStack.peek().getPlayer();
            moveStack.pop().undo();

            if (moveStack.empty()) {
                setGameState();
            } else {
                setGameState(moveStack.peek().getDest().getCoord());
            }
            return "Move undone.\n";
        } else {
            return "No moves to undo!\n";
        }
    }

    /**
     *
     */
    private void changeTurn() {
        if (currentPlayer.equals(playerOne)) {
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }
    }
}

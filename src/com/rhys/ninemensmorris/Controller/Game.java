package com.rhys.ninemensmorris.Controller;

import com.rhys.ninemensmorris.Model.Board;
import com.rhys.ninemensmorris.Model.Human;
import com.rhys.ninemensmorris.Model.Move;
import com.rhys.ninemensmorris.Model.Player;
import com.rhys.ninemensmorris.View.Display;

import java.util.Scanner;
import java.util.Stack;

/**
 * @author Rhys Gevaux
 * @version 2015.05.31
 */
public class Game {
    // Static integers for all the game states.
    public static final int STATE_PLACE = 0;
    public static final int STATE_REMOVE = 1;
    public static final int STATE_SLIDE = 2;
    public static final int STATE_FLY = 3;
    private static final Game INSTANCE = new Game(); // singleton instance
    private static final int STATE_COMPLETE = 4;

    private final Board board;
    private final Display display;
    private final Stack<Move> moveStack;

    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;

    private int gameState;

    /**
     * Constructs the game object.
     */
    private Game() {
        this.playerOne = null;
        this.playerTwo = null;
        this.currentPlayer = null;
        this.board = Board.getInstance();
        this.display = Display.getInstance();
        this.moveStack = new Stack<Move>();
    }

    /**
     * @return instance of Game
     */
    public static Game getInstance() {
        return INSTANCE;
    }

    /**
     * Plays the game.
     */
    public void play() {
        // get player's names
        Scanner in = new Scanner(System.in);
        display.out("Player one's name: ");
        playerOne = new Human(in.nextLine().trim());
        display.out("Player two's name: ");
        playerTwo = new Human(in.nextLine().trim());
        currentPlayer = playerOne;

        display.drawBoard();

        // loop that runs the game until someone wins
        while (gameState != STATE_COMPLETE) {
            String input;

            // checks game state for best output
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

            // take user input
            input = in.nextLine().trim().toLowerCase();

            if (input.equals("stop")) {
                display.out("Game stopped by " + currentPlayer.toString("name") + "\n");
                System.exit(1); // exit the game
            } else if (input.equals("undo")) {
                undo();
            } else if (input.length() == 2 && gameState == STATE_PLACE) { // e.g. "a1"
                if (board.hasSpot(input)) {
                    move(null, input);
                } else {
                    display.out("Spot doesn't exist.\n");
                }
            } else if (input.length() == 2 && gameState == STATE_REMOVE) { // e.g. "a1"
                if (board.hasSpot(input)) {
                    move(input, null);
                } else {
                    display.out("Spot doesn't exist.\n");
                }
            } else if (input.length() == 6 && input.contains("->") && (gameState == STATE_SLIDE ||
                    gameState == STATE_FLY)) { // e.g. "a1->a4"
                String splitStr[] = input.split("->");
                if (board.hasSpots(splitStr)) {
                    move(splitStr[0], splitStr[1]);
                } else {
                    display.out("Spot(s) doesn't exist.\n");
                }
            } else {
                display.out("Invalid input.\n");
            }

            display.drawBoard();
        }
        display.out(getOtherPlayer().toString("name") + " won!");
    }

    /**
     * Gets the not current player
     *
     * @return not current player
     */
    private Player getOtherPlayer() {
        if (currentPlayer.equals(playerOne)) {
            return playerTwo;
        } else {
            return playerOne;
        }
    }

    /**
     * Undoes the most recent move.
     */
    private void undo() {
        if (moveStack.size() > 1) {
            moveStack.pop().undo(); // undoes last move

            // redoes the move before to get correct game state
            moveStack.peek().undo();
            changeGameState();
            moveStack.peek().move();
            currentPlayer = moveStack.peek().getPlayer();
            changeGameState();
            display.out("Move undone.\n");
        } else if (moveStack.size() > 0) {
            moveStack.pop().undo();
            changeGameState();
            display.out("Move undone.\n");
        } else {
            display.out("No moves to undo!\n");
        }
    }

    /**
     * Asks the player if the move is valid.
     * If the move is valid, tells the player to move the piece and adds the resulting move to the Move Stack.
     *
     * @param srcStr  The source spot of the move.
     * @param destStr The destination spot of the move.
     */
    private void move(String srcStr, String destStr) {
        // checks if move is valid
        if (currentPlayer.validMove(gameState, board.getSpot(srcStr), board.getSpot(destStr))) {
            // makes move and puts it into the stack
            moveStack.push(currentPlayer.move(gameState, board.getSpot(srcStr), board.getSpot(destStr)));
            display.out("Move successful.\n");
            changeGameState();
        } else {
            display.out("Move failed, try again.\n");
        }
    }

    /**
     * Sets the game state given a destination spot of a recent move.
     */
    private void changeGameState() {
        if (board.updateMills()) {
            gameState = STATE_REMOVE;
            display.out("Mill created!\n");
        } else {
            changeTurn();
            if (!currentPlayer.hasAllPiecesPlaced()) {
                gameState = STATE_PLACE;
            } else if (currentPlayer.hasThreePiecesLeft()) {
                gameState = STATE_FLY;
            } else if (!currentPlayer.hasLegalMove() || currentPlayer.hasTwoPiecesLeft()) {
                gameState = STATE_COMPLETE;
            } else {
                gameState = STATE_SLIDE;
            }
        }
    }

    /**
     * Sets the currentPlayer to the other player.
     */
    private void changeTurn() {
        currentPlayer = getOtherPlayer();
    }
}

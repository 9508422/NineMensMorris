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
 * @version 2015.05.27
 */
public class Game {
	// Static integers for all the game states.
	public static final int STATE_PLACE = 0;
	public static final int STATE_REMOVE = 1;
	public static final int STATE_SLIDE = 2;
	public static final int STATE_FLY = 3;
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
	public Game() {
		this.playerOne = null;
		this.playerTwo = null;
		this.currentPlayer = null;
		this.board = new Board();
		display = new Display(board);
		this.moveStack = new Stack<Move>();
	}

	/**
	 * Plays the game.
	 */
	public void play() {
		// get player's names
		Scanner in = new Scanner(System.in);
		display.out("Player one's name: ");
		playerOne = new Human(in.next().trim());
		display.out("Player two's name: ");
		playerTwo = new Human(in.next().trim());
		currentPlayer = playerOne;

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
				display.out("Game stopped by " + currentPlayer.toString("name") + "\n");
				System.exit(1);
			} else if (input.equals("undo")) {
				display.out(undo());
			} else if (input.length() == 2 && gameState == STATE_PLACE) {
				if (board.hasSpot(input)) {
					move(null, input);
				} else {
					display.out("Move invalid.\n");
				}
			} else if (input.length() == 2 && gameState == STATE_REMOVE) {
				if (board.hasSpot(input)) {
					move(input, null);
				} else {
					display.out("Move invalid.\n");
				}
			} else if (input.length() == 6 && input.contains("->") && (gameState == STATE_SLIDE ||
					gameState == STATE_FLY)) {
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
	 * Gets the not current player
	 *
	 * @return not current player
	 */
	Player getOtherPlayer() {
		if (currentPlayer.equals(playerOne)) {
			return playerTwo;
		} else {
			return playerOne;
		}
	}

	/**
	 * Undoes the most recent move.
	 *
	 * @return A string to output based on if the undo was successful.
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
	 * Tells the player to move the piece.
	 * If the move is successful, adds the move to the stack, otherwise report an error.
	 *
	 * @param srcStr  The source spot of the move.
	 * @param destStr The destination spot of the move.
	 */
	void move(String srcStr, String destStr) {
		Move move = currentPlayer.move(gameState, board.getSpot(srcStr), board.getSpot(destStr));
		if (move != null) {
			moveStack.push(move);
			display.out("Move successful.\n");

			setGameState(destStr);
			if (gameState == STATE_REMOVE) {
				display.out("Mill created!\n");
			} else {
				changeTurn();
			}
		} else {
			display.out("Move failed, try again.\n");
		}
	}

	/**
	 * Sets the game state.
	 */
	private void setGameState() {
		setGameState(null);
	}

	/**
	 * Sets the game state given a destination spot of a recent move.
	 *
	 * @param destStr the destination of the piece that most recently moved.
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
	 * Sets the currentPlayer to the other player.
	 */
	private void changeTurn() {
		currentPlayer = getOtherPlayer();
	}
}

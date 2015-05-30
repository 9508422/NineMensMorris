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
	private static final Game INSTANCE = new Game();
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

			input = in.nextLine().trim().toLowerCase();

			if (input.equals("stop")) {
				display.out("Game stopped by " + currentPlayer.toString("name") + "\n");
				System.exit(1);
			} else if (input.equals("undo")) {
				undo();
			} else if (input.length() == 2 && gameState == STATE_PLACE) {
				if (board.hasSpot(input)) {
					move(null, input);
				} else {
					display.out("Spot doesn't exist.\n");
				}
			} else if (input.length() == 2 && gameState == STATE_REMOVE) {
				if (board.hasSpot(input)) {
					move(input, null);
				} else {
					display.out("Spot doesn't exist.\n");
				}
			} else if (input.length() == 6 && input.contains("->") && (gameState == STATE_SLIDE ||
					gameState == STATE_FLY)) {
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
	Player getOtherPlayer() {
		if (currentPlayer.equals(playerOne)) {
			return playerTwo;
		} else {
			return playerOne;
		}
	}

	/**
	 * Undoes the most recent move.
	 */
	void undo() {
		if (moveStack.size() > 1) {
			currentPlayer = moveStack.peek().getPlayer(); // sets the currentPlayer to the player who made the last move
			moveStack.pop().undo(); // undoes last move

			// redoes the move before to get correct game state
			moveStack.peek().undo();
			setGameState();
			moveStack.peek().move();
			setGameState();
			display.out("Move undone.\n");
		} else if (moveStack.size() > 0) {
			currentPlayer = moveStack.peek().getPlayer();
			moveStack.pop().undo();
			setGameState();
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
	void move(String srcStr, String destStr) {
		if (currentPlayer.validMove(gameState, board.getSpot(srcStr), board.getSpot(destStr))) {
			moveStack.push(currentPlayer.move(gameState, board.getSpot(srcStr), board.getSpot(destStr)));
			display.out("Move successful.\n");

			setGameState();
			if (gameState != STATE_REMOVE) {
				changeTurn();
			}
		} else {
			display.out("Move failed, try again.\n");
		}
	}

	/**
	 * Sets the game state given a destination spot of a recent move.
	 */
	private void setGameState() {
		if (board.millCreated()) {
			gameState = STATE_REMOVE;
			display.out("Mill created!\n");
		} else if (!getOtherPlayer().hasAllPiecesPlaced()) {
			gameState = STATE_PLACE;
		} else if (getOtherPlayer().hasThreePiecesLeft()) {
			gameState = STATE_FLY;
		} else if (getOtherPlayer().hasNoLegalMove() || getOtherPlayer().hasTwoPiecesLeft()) {
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

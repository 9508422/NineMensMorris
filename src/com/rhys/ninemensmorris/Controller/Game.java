package com.rhys.ninemensmorris.Controller;

import com.rhys.ninemensmorris.Model.Board;
import com.rhys.ninemensmorris.Model.Human;
import com.rhys.ninemensmorris.Model.Move;
import com.rhys.ninemensmorris.Model.Player;

import java.util.Stack;

/**
 * Created by Rhys on 14/05/2015.
 */
public class Game {
    public static final int STATE_PLACE = 0;
    public static final int STATE_REMOVE = 1;
    public static final int STATE_SLIDE = 2;
    public static final int STATE_FLY = 3;
    public static final int STATE_COMPLETE = 4;

    private final Stack<Move> moveStack;
    private final Player playerOne;
    private final Player playerTwo;
    private final Board board;
    private Player currentPlayer;
    private int gameState;

    public Game(Board board, String playerOne, String playerTwo) {
        this.moveStack = new Stack<Move>();
        this.playerOne = new Human(playerOne);
        this.playerTwo = new Human(playerTwo);
        this.currentPlayer = this.playerOne;
        this.board = board;
        setGameState();
    }

    public int getGameState() {
        return gameState;
    }

    private void setGameState(String destStr) {
        if (board.wasMillCreated(board.getSpot(destStr))) {
            gameState = STATE_REMOVE;
        } else if (currentPlayer.allPiecesPlaced()) {
            if (currentPlayer.threePiecesLeft()) {
                gameState = STATE_FLY;
            } else if (currentPlayer.twoPiecesLeft()) {
                gameState = STATE_COMPLETE;
            } else {
                gameState = STATE_SLIDE;
            }
        } else {
            gameState = STATE_PLACE;
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    private void setCurrentPlayer(Player player) {
        currentPlayer = player;
    }

    public Player getOtherPlayer() {
        if (currentPlayer.equals(playerOne)) {
            return playerTwo;
        } else {
            return playerOne;
        }
    }

    private void setGameState() {
        if (currentPlayer.allPiecesPlaced()) {
            if (currentPlayer.threePiecesLeft()) {
                gameState = STATE_FLY;
            } else if (currentPlayer.hasLegalMove() || currentPlayer.twoPiecesLeft()) {
                gameState = STATE_COMPLETE;
            } else {
                gameState = STATE_SLIDE;
            }
        } else {
            gameState = STATE_PLACE;
        }
    }

    public String move(String destStr) {
        if (gameState != STATE_SLIDE && gameState != STATE_FLY) {
            return move(null, destStr);
        } else {
            return "You need to select which piece to move to that destination!";
        }
    }

    public String move(String sourceStr, String destStr) {
        if (board.spotExists(sourceStr)) {
            if (board.spotExists(destStr)) {
                String player = currentPlayer.toString("name");
                if (gameState == STATE_PLACE) {
                    return place(player, destStr);
                } else if (gameState == STATE_REMOVE) {
                    return remove(player, destStr);
                } else if (gameState == STATE_SLIDE) {
                    return slide(player, sourceStr, destStr);
                } else if (gameState == STATE_FLY) {
                    return fly(player, sourceStr, destStr);
                } else {
                    return "Game has left a valid state!";
                }
            } else {
                return "Destination spot doesn't exist!";
            }
        } else {
            return "Source spot doesn't exist!";
        }
    }

    private String place(String player, String destStr) {
        if (!board.getSpot(destStr).hasPiece()) {
            moveStack.push(currentPlayer.place(board.getSpot(destStr)));
            setGameState(destStr);

            if (gameState == STATE_REMOVE) {
                return player + " placed a piece on " + destStr + "\nMill created!";
            } else {
                changeTurn();
                return player + " placed a piece on " + destStr;
            }
        } else {
            return "Piece already located on: " + destStr;
        }
    }

    private String remove(String player, String spotStr) {
        if (board.getSpot(spotStr).hasPiece()) {
            if (!currentPlayer.hasPiece(board.getSpot(spotStr).getPiece())) {
                moveStack.push(currentPlayer.remove(board.getSpot(spotStr)));
                changeTurn();
                setGameState();

                return player + " removed a piece on " + spotStr;
            } else {
                return "You cannot remove your own piece!";
            }
        } else {
            return "There is no piece located on " + spotStr;
        }
    }

    private String slide(String player, String sourceStr, String destStr) {
        if (board.getSpot(sourceStr).hasPiece()) {
            if (currentPlayer.hasPiece(board.getSpot(sourceStr).getPiece())) {
                if (!board.getSpot(destStr).hasPiece()) {
                    if (board.getSpot(sourceStr).hasNeighbour(board.getSpot(destStr))) {
                        moveStack.push(currentPlayer.slide(board.getSpot(sourceStr), board.getSpot(destStr)));
                        setGameState(destStr);

                        if (gameState == STATE_REMOVE) {
                            return player + " slid a piece from " + sourceStr + " to " + destStr + "\nMill created, remove opponents piece!";
                        } else {
                            changeTurn();
                            return player + " slid a piece from " + sourceStr + " to " + destStr;
                        }
                    } else {
                        return destStr + " is not a neighbour of " + sourceStr;
                    }
                } else {
                    return "There is already a piece on " + destStr;
                }
            } else {
                return "That is not your piece to move!";
            }
        } else {
            return "There is no piece on " + sourceStr;
        }
    }

    private String fly(String player, String sourceStr, String destStr) {
        if (board.getSpot(sourceStr).hasPiece()) {
            if (currentPlayer.hasPiece(board.getSpot(sourceStr).getPiece())) {
                if (!board.getSpot(destStr).hasPiece()) {
                    moveStack.push(currentPlayer.fly(board.getSpot(sourceStr), board.getSpot(destStr)));
                    setGameState(destStr);

                    if (gameState == STATE_REMOVE) {
                        return player + " flew a piece from " + sourceStr + " to " + destStr + "\nMill created, remove opponents piece!";
                    } else {
                        changeTurn();
                        return player + " flew a piece from " + sourceStr + " to " + destStr;
                    }
                } else {
                    return "There is already a piece on " + destStr;
                }
            } else {
                return "That is not your piece to move!";
            }
        } else {
            return "There is no piece on " + sourceStr;
        }
    }

    public String undo() {
        if (moveStack.size() > 0) {
            setCurrentPlayer(moveStack.peek().getPlayer());
            moveStack.pop().undo();

            if (moveStack.empty()) {
                setGameState();
            } else {
                setGameState(moveStack.peek().getDest().getCoord());
            }
            return "Move undone!";
        } else {
            return "No moves to undo!";
        }
    }

    private void changeTurn() {
        if (currentPlayer.equals(playerOne)) {
            currentPlayer = playerTwo;
        } else {
            currentPlayer = playerOne;
        }
    }
}

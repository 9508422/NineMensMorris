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
    private static final int STATE_PLACE = 0;
    private static final int STATE_REMOVE = 1;
    private static final int STATE_SLIDE = 2;
    private static final int STATE_FLY = 3;
    private static final int STATE_COMPLETE = 4;
    private static final int PLAYER_ONE = 0;
    private static final int PLAYER_TWO = 1;
    private Stack<Move> moveStack;
    private Player[] players;
    private Board board;
    private int turn;
    private int gameState; // 0 = place, 1 = remove, 2 = slide, 3 = fly, 4 = complete

    public Game(Board board, String playerOne, String playerTwo) {
        this.moveStack = new Stack<Move>();
        this.players = new Player[]{new Human(playerOne), new Human(playerTwo)};
        this.board = board;
        turn = PLAYER_ONE;
        this.gameState = setGameState();
    }

    public int getGameState() {
        return gameState;
    }

    public String getCurrentPlayer() {
        return players[turn].toString("name");
    }

    public String getOtherPlayer() {
        if (turn == 0) {
            return players[1].toString("name");
        } else {
            return players[0].toString("name");
        }
    }

    private int setGameState() {
        if (players[turn].allPiecesPlaced()) {
            if (players[turn].threePiecesLeft()) {
                return STATE_FLY;
            } else if (players[turn].twoPiecesLeft()) {
                return STATE_COMPLETE;
            } else {
                return STATE_SLIDE;
            }
        } else {
            return STATE_PLACE;
        }
    }

    public String move(String destStr) {
        if (board.spotExists(destStr)) {
            if (gameState != 2) {
                return move(null, destStr);
            } else {
                return "You need to select which piece to move to that destination!";
            }
        } else {
            return "Spot does not exist!";
        }
    }

    public String move(String sourceStr, String destStr) {
        if (board.spotExists(sourceStr)) {
            String player = players[turn].toString("name");
            if (gameState == STATE_PLACE) {
                return place(player, destStr);
            } else if (gameState == STATE_REMOVE) {
                return remove(player, destStr);
            } else if (gameState == STATE_SLIDE) {
                return slide(player, sourceStr, destStr);
            } else if (gameState == STATE_FLY) {
                return fly(player, sourceStr, destStr);
            } else {
                return null;
            }
        } else {
            return "Destination spot doesn't exist!";
        }
    }

    private String place(String player, String destStr) {
        if (board.getSpot(destStr).hasPiece()) {
            return "Piece already located on: " + destStr;
        } else {
            moveStack.push(players[turn].place(board.getSpot(destStr)));
            if (board.wasMillCreated(board.getSpot(destStr))) {
                gameState = STATE_REMOVE;
                return player + " placed a piece on " + destStr + "\nMill created!";
            }
            turn = changeTurn();
            gameState = setGameState();
            return player + " placed a piece on " + destStr;
        }
    }

    private String remove(String player, String spotStr) {
        if (board.getSpot(spotStr).hasPiece()) {
            if (!board.getSpot(spotStr).getPiece().getPlayer().equals(players[turn])) {
                moveStack.push(players[turn].remove(board.getSpot(spotStr)));
                turn = changeTurn();
                gameState = setGameState();
                if (gameState == STATE_REMOVE) {
                    return player + "removed a piece on " + spotStr + " and won!";
                } else {
                    return player + " removed a piece on " + spotStr;
                }
            } else {
                return "You cannot remove your own piece!";
            }
        } else {
            return "There is no piece located on " + spotStr;
        }
    }

    private String slide(String player, String sourceStr, String destStr) {
        if (board.getSpot(sourceStr).hasPiece()) {
            if (board.getSpot(sourceStr).getPiece().getPlayer().equals(players[turn])) {
                if (!board.getSpot(destStr).hasPiece()) {
                    if (board.getSpot(sourceStr).hasNeighbour(board.getSpot(destStr))) {
                        moveStack.push(players[turn].slide(board.getSpot(sourceStr), board.getSpot(destStr)));
                        if (board.wasMillCreated(board.getSpot(destStr))) {
                            gameState = STATE_REMOVE;
                            return player + " slid a piece from " + sourceStr + " to " + destStr + "\nMill created, remove opponents piece!";
                        }
                        turn = changeTurn();
                        gameState = setGameState();
                        return player + " slid a piece from " + sourceStr + " to " + destStr;
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
            if (board.getSpot(sourceStr).getPiece().getPlayer().equals(players[turn])) {
                if (!board.getSpot(destStr).hasPiece()) {
                    moveStack.push(players[turn].fly(board.getSpot(sourceStr), board.getSpot(destStr)));
                    if (board.wasMillCreated(board.getSpot(destStr))) {
                        gameState = STATE_REMOVE;
                        return player + " flew a piece from " + sourceStr + " to " + destStr + "\nMill created, remove opponents piece!";
                    }
                    turn = changeTurn();
                    gameState = setGameState();
                    return player + " flew a piece from " + sourceStr + " to " + destStr;
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
            moveStack.pop().undo();
            if (moveStack.size() == 0 || players[turn] == moveStack.peek().getPlayer()) {
                turn = changeTurn();
            }
            return "Move undone!";
        } else {
            return "No moves to undo!";
        }
    }

    private int changeTurn() {
        if (turn == PLAYER_ONE) {
            return PLAYER_TWO;
        } else {
            return PLAYER_ONE;
        }
    }
}

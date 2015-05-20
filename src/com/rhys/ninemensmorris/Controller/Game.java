package com.rhys.ninemensmorris.Controller;

import com.rhys.ninemensmorris.Model.Board;
import com.rhys.ninemensmorris.Model.Move;
import com.rhys.ninemensmorris.Model.Player;

import java.util.Stack;

/**
 * Created by Rhys on 14/05/2015.
 */
public class Game {
    private Stack<Move> moveStack;
    private Player[] players;
    private Board board;
    private int turn;
    private int gameState; // 0 = place, 1 = remove, 2 = slide, 3 = hop

    public Game(Board board, String playerOne, String playerTwo) {
        this.moveStack = new Stack<Move>();
        this.players = new Player[]{new Player(playerOne), new Player(playerTwo)};
        this.board = board;
        turn = 0;
        this.gameState = checkGameState();
    }

    private int checkGameState() {
        if (players[turn].allPiecesPlaced()) {
            if (players[turn].threePiecesLeft()) {
                return 3;
            } else {
                return 2;
            }
        } else {
            return 0;
        }
    }

    public String move(String destStr) {
        if (gameState != 2) {
            return move(null, destStr);
        } else {
            return "You need to select which piece to move to that destination!";
        }
    }

    public String move(String sourceStr, String destStr) {
        if (gameState == 0) {
            return place(destStr);
        } else if (gameState == 1) {
            return remove(destStr);
        } else if (gameState == 2) {
            return slide(sourceStr, destStr);
        } else if (gameState == 3) {
            return null;
        }
        return null;
    }

    private String place(String destStr) {
        String player = players[turn].toString("name");
         if (board.getSpot(destStr).hasPiece()) {
             return "Piece already located on: " + destStr;
         } else {
             moveStack.add(players[turn].place(board.getSpot(destStr)));
             if (board.wasMillCreated(board.getSpot(destStr))) {
                 gameState = 1;
                 return player + " placed a piece on: " + destStr + "\nMill created, remove opponents piece!";
             }
             turn = changeTurn();
             gameState = checkGameState();
             return player + " placed a piece on " + destStr;
         }
    }

    private String remove(String spotStr) {
        String player = players[turn].toString("name");
        if (board.getSpot(spotStr).hasPiece()) {
            if (!board.getSpot(spotStr).getPiece().getPlayer().equals(players[turn])) {
                moveStack.add(players[turn].remove(board.getSpot(spotStr)));
                turn = changeTurn();
                gameState = checkGameState();
                return player + " removed a piece on " + spotStr;
            } else {
                return "You cannot remove your own piece!";
            }
        } else {
            return "No piece located at: " + spotStr;
        }
    }

    private String slide(String sourceStr, String destStr) {
        String player = players[turn].toString("name");
        if (board.getSpot(sourceStr).hasPiece()) {
            if (board.getSpot(sourceStr).getPiece().getPlayer().equals(players[turn])) {
                if (!board.getSpot(destStr).hasPiece()) {
                    if (board.getSpot(sourceStr).hasNeighbour(board.getSpot(destStr))) {
                        moveStack.add(players[turn].slide(board.getSpot(sourceStr), board.getSpot(destStr)));
                        turn = changeTurn();
                        gameState = checkGameState();
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

    private String hop(String sourceStr, String destStr) {
        String player = players[turn].toString("name");
        if (board.getSpot(sourceStr).hasPiece()) {
            if (board.getSpot(sourceStr).getPiece().getPlayer().equals(players[turn])) {
                if (!board.getSpot(destStr).hasPiece()) {
                    moveStack.add(players[turn].hop(board.getSpot(sourceStr), board.getSpot(destStr)));
                    turn = changeTurn();
                    gameState = checkGameState();
                    return player + " hopped a piece from " + sourceStr + " to " + destStr;
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

    private int changeTurn() {
        if (turn == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}

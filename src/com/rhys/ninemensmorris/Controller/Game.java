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
    private Stack<Move> moveStack;
    private Player[] players;
    private Board board;
    private int turn;
    private int gameState; // 0 = place, 1 = remove, 2 = slide, 3 = hop, 4 = complete

    public Game(Board board, String playerOne, String playerTwo) {
        this.moveStack = new Stack<Move>();
        this.players = new Player[] {new Human(playerOne), new Human(playerTwo)};
        this.board = board;
        turn = 0; // 0 = player 1, 1 = player 2
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
                return 3;
            } else if (players[turn].twoPiecesLeft()) {
                return 4;
            } else {
                return 2;
            }
        } else {
            return 0;
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
            if (gameState == 0) {
                return place(player, destStr);
            } else if (gameState == 1) {
                return remove(player, destStr);
            } else if (gameState == 2) {
                return slide(player, sourceStr, destStr);
            } else if (gameState == 3) {
                return hop(player, sourceStr, destStr);
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
             moveStack.add(players[turn].place(board.getSpot(destStr)));
             if (board.wasMillCreated(board.getSpot(destStr))) {
                 gameState = 1;
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
                moveStack.add(players[turn].remove(board.getSpot(spotStr)));
                turn = changeTurn();
                gameState = setGameState();
                if (gameState == 4) {
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
                        moveStack.add(players[turn].slide(board.getSpot(sourceStr), board.getSpot(destStr)));
                        if (board.wasMillCreated(board.getSpot(destStr))) {
                            gameState = 1;
                            return player + " placed a piece on: " + destStr + "\nMill created, remove opponents piece!";
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

    private String hop(String player, String sourceStr, String destStr) {
        if (board.getSpot(sourceStr).hasPiece()) {
            if (board.getSpot(sourceStr).getPiece().getPlayer().equals(players[turn])) {
                if (!board.getSpot(destStr).hasPiece()) {
                    moveStack.add(players[turn].hop(board.getSpot(sourceStr), board.getSpot(destStr)));
                    if (board.wasMillCreated(board.getSpot(destStr))) {
                        gameState = 1;
                        return player + " placed a piece on: " + destStr + "\nMill created, remove opponents piece!";
                    }
                    turn = changeTurn();
                    gameState = setGameState();
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

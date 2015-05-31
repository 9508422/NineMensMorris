package com.rhys.ninemensmorris.Model;

import com.rhys.ninemensmorris.Controller.Game;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Rhys Gevaux
 * @version 2015.05.31
 */
public abstract class Player {
    private final Set<Piece> pieceSet;
    private final String name;
    private final String colour;

    /**
     * Player constructor
     * Fills the set of Pieces with new Pieces
     * Sets the "colour" based off their name
     *
     * @param name the desired name of the Player
     */
    Player(String name) {
        pieceSet = new HashSet<Piece>();
        for (int i = 1; i <= 9; i++) {
            pieceSet.add(new Piece(this));
        }
        this.name = name;
        this.colour = name.substring(0, 1);
    }

    /**
     * @param piece the Piece to be added to the Player's pieceSet
     */
    public void addPiece(Piece piece) {
        pieceSet.add(piece);
    }

    /**
     * @param piece the Piece to be checked if the Player owns it
     * @return if the Player has the Piece
     */
    public boolean hasPiece(Piece piece) {
        return pieceSet.contains(piece);
    }

    /**
     * @param piece the Piece to be Removed from the Player
     */
    public void removePiece(Piece piece) {
        pieceSet.remove(piece);
    }

    /**
     * @return if the Player has placed all their Pieces
     */
    public boolean hasAllPiecesPlaced() {
        for (Piece piece : pieceSet) {
            if (!piece.hasSpot()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @return if the Player has three Pieces left
     */
    public boolean hasThreePiecesLeft() {
        return pieceSet.size() == 3;
    }

    /**
     * @return if the Player has two Pieces left
     */
    public boolean hasTwoPiecesLeft() {
        return pieceSet.size() == 2;
    }

    /**
     * @return if the Player has a legal Move to make
     */
    public boolean hasLegalMove() {
        for (Piece piece : pieceSet) {
            if (piece.hasLegalMove()) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param toggle changes the output based on what is wanted
     * @return a String version of the Player, either the full name or the "colour"
     */
    public String toString(String toggle) {
        if (toggle.equals("name")) {
            return name;
        } else if (toggle.equals("colour")) {
            return colour;
        }
        return null;
    }

    /**
     * @return if the Player has all their Pieces in Mills
     */
    public boolean allPiecesInMill() {
        for (Piece piece : pieceSet) {
            if (piece.hasSpot() && !piece.getInMill()) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param gameState the current Game state
     * @param src       the source Spot
     * @param dest      the destination Spot
     * @return the Move created and made
     */
    public Move move(int gameState, Spot src, Spot dest) {
        Move move = null;
        Piece piece;

        // if still placing Pieces, get an unplaced Piece
        if (gameState == Game.STATE_PLACE) {
            piece = getUnplacedPiece();
        } else {
            piece = src.getPiece();
        }

        // creates the right Move type based on game state
        switch (gameState) {
            case Game.STATE_PLACE:
                move = new Place();
                break;
            case Game.STATE_REMOVE:
                move = new Remove();
                break;
            case Game.STATE_SLIDE:
                move = new Slide();
                break;
            case Game.STATE_FLY:
                move = new Fly();
                break;
        }
        if (move != null) {
            move.move(this, piece, src, dest);
        }
        return move;
    }

    /**
     * @return an unplaced Piece
     */
    private Piece getUnplacedPiece() {
        for (Piece piece : pieceSet) {
            if (!piece.hasSpot()) {
                return piece;
            }
        }
        return null;
    }

    /**
     * @param gameState the current Game state
     * @param src       the source Spot
     * @param dest      the destination Spot
     * @return if the Move is valid
     */
    public boolean validMove(int gameState, Spot src, Spot dest) {
        Move move = null;
        Piece piece;

        // if still placing Pieces, get an unplaced Piece
        if (gameState == Game.STATE_PLACE) {
            piece = getUnplacedPiece();
        } else {
            piece = src.getPiece();
        }

        // creates the right Move type based on game state
        switch (gameState) {
            case Game.STATE_PLACE:
                move = new Place();
                break;
            case Game.STATE_REMOVE:
                move = new Remove();
                break;
            case Game.STATE_SLIDE:
                move = new Slide();
                break;
            case Game.STATE_FLY:
                move = new Fly();
                break;
        }

        // move is valid if game state is valid, and if the result of validMove is true
        return move != null && move.validMove(this, piece, src, dest);
    }
}

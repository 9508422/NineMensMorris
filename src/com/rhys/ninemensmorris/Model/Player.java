package com.rhys.ninemensmorris.Model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rhys on 14/05/2015.
 */
public abstract class Player {
    final Set<Piece> pieceSet;
    private final String name;
    private final String colour;

    /**
     *
     * @param name
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
     *
     * @param piece
     */
    public void addPiece(Piece piece) {
        pieceSet.add(piece);
    }

    /**
     *
     * @param piece
     */
    public void removePiece(Piece piece) {
        pieceSet.remove(piece);
    }

    /**
     *
     * @param place
     * @param dest
     * @return
     */
    public abstract boolean place(Place place, Spot dest);

    /**
     *
     * @param remove
     * @param spot
     * @return
     */
    public abstract boolean remove(Remove remove, Spot spot);

    /**
     *
     * @param slide
     * @param src
     * @param dest
     * @return
     */
    public abstract boolean slide(Slide slide, Spot src, Spot dest);

    /**
     *
     * @param fly
     * @param src
     * @param dest
     * @return
     */
    public abstract boolean fly(Fly fly, Spot src, Spot dest);

    /**
     *
     * @return
     */
    public boolean allPiecesPlaced() {
        for (Piece piece : pieceSet) {
            if (piece.getSpot() == null) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * @return
     */
    public boolean threePiecesLeft() {
        return pieceSet.size() == 3;
    }

    /**
     *
     * @return
     */
    public boolean twoPiecesLeft() {
        return pieceSet.size() == 2;
    }

    /**
     *
     * @return
     */
    public boolean noLegalMove() {
        for (Piece piece : pieceSet) {
            for (Spot spot : piece.getSpot().getNeighbours()) {
                if (!spot.hasPiece()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *
     * @param toggle
     * @return
     */
    public String toString(String toggle) {
        if (toggle.equals("name")) {
            return name;
        } else if (toggle.equals("colour")) {
            return colour;
        }
        return null;
    }
}

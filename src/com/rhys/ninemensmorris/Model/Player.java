package com.rhys.ninemensmorris.Model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rhys on 14/05/2015.
 */
public class Player {
    private Set<Piece> pieceSet;
    private String name;
    private String colour;

    public Player(String name) {
        pieceSet = new HashSet<Piece>();
        for (int i = 1; i < 9; i++) {
            pieceSet.add(new Piece(this));
        }
        this.name = name;
        this.colour = name.substring(0, 1);
    }

    public Place place(Spot dest) {
        for (Piece piece : pieceSet) {
            if (piece.getSpot() == null) {
                return new Place(this, piece, dest);
            }
        }
        return null;
    }

    public void removePiece(Piece piece) {
        pieceSet.remove(piece);
    }

    public Remove remove(Spot spot) {
        return new Remove(this, spot.getPiece(), spot);
    }

    public Slide slide(Spot source, Spot dest) {
        return new Slide(this, source, dest);
    }

    public Hop hop(Spot source, Spot dest) {
        return new Hop(this, source, dest);
    }

    public boolean allPiecesPlaced() {
        for (Piece piece : pieceSet) {
            if (piece.getSpot() == null) {
                return false;
            }
        }
        return true;
    }

    public boolean threePiecesLeft() {
        return pieceSet.size() == 3;
    }

    public String toString(String toggle) {
        if (toggle == "name") {
            return name;
        } else if (toggle == "colour") {
            return colour;
        }
        return null;
    }
}

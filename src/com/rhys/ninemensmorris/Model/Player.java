package com.rhys.ninemensmorris.Model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rhys on 14/05/2015.
 */
public abstract class Player {
    private final Set<Piece> pieceSet;
    private final String name;
    private final String colour;

    Player(String name) {
        pieceSet = new HashSet<Piece>();
        for (int i = 1; i <= 9; i++) {
            pieceSet.add(new Piece(this));
        }
        this.name = name;
        this.colour = name.substring(0, 1);
    }

    public void addPiece(Piece piece) {
        pieceSet.add(piece);
    }

    public boolean hasPiece(Piece piece) {
        return pieceSet.contains(piece);
    }

    public void removePiece(Piece piece) {
        pieceSet.remove(piece);
    }

    public boolean place(Place place, Spot dest) {
        for (Piece piece : pieceSet) {
            if (piece.getSpot() == null) {
                return place.move(this, piece, dest);
            }
        }
        return false;
    }

    public boolean remove(Remove remove, Spot spot) {
        return spot.hasPiece() && !pieceSet.contains(spot.getPiece()) && remove.move(this, spot);
    }

    public boolean slide(Slide slide, Spot src, Spot dest) {
        return src.hasPiece() && pieceSet.contains(src.getPiece()) && !dest.hasPiece() && slide.move(this, src, dest);
    }

    public boolean fly(Fly fly, Spot src, Spot dest) {
        return src.hasPiece() && pieceSet.contains(src.getPiece()) && !dest.hasPiece() && fly.move(this, src, dest);
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

    public boolean twoPiecesLeft() {
        return pieceSet.size() == 2;
    }

    public boolean hasLegalMove() {
        for (Piece piece : pieceSet) {
            for (Spot spot : piece.getSpot().getNeighbours()) {
                if (!spot.hasPiece()) {
                    return true;
                }
            }
        }
        return false;
    }

    public String toString(String toggle) {
        if (toggle.equals("name")) {
            return name;
        } else if (toggle.equals("colour")) {
            return colour;
        }
        return null;
    }
}

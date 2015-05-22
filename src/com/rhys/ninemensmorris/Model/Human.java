package com.rhys.ninemensmorris.Model;

/**
 * Created by Rhys on 14/05/2015.
 */
public class Human extends Player {
    public Human(String name) {
        super(name);
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
        return src.hasPiece() && pieceSet.contains(src.getPiece()) && slide.move(this, src, dest);
    }

    public boolean fly(Fly fly, Spot src, Spot dest) {
        return src.hasPiece() && pieceSet.contains(src.getPiece()) && fly.move(this, src, dest);
    }
}

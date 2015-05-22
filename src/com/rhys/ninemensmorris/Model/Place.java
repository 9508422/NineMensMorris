package com.rhys.ninemensmorris.Model;

/**
 * Created by Rhys on 14/05/2015.
 */
public class Place extends Move {

    public Place(Player player, Piece piece, Spot dest) {
        super(player, piece, null, dest);

        piece.setSpot(dest);
        dest.setPiece(piece);
    }

    @Override
    public void undo() {
        super.getPiece().setSpot(getSource());
        super.getDest().removePiece();
    }
}

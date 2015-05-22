package com.rhys.ninemensmorris.Model;

/**
 * Created by Rhys on 18/05/2015.
 */
public class Remove extends Move {
    public Remove(Player player, Piece piece, Spot spot) {
        super(player, piece, spot, null);

        spot.removePiece();
        piece.removeSpot();
        piece.getPlayer().removePiece(piece);
    }

    @Override
    public void undo() {
        super.getPiece().getPlayer().addPiece(super.getPiece());
        super.getPiece().setSpot(super.getSource());
        super.getSource().setPiece(super.getPiece());
    }
}

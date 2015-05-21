package com.rhys.ninemensmorris.Model;

/**
 * Created by Rhys on 18/05/2015.
 */
public class Remove extends Move {
    public Remove(Player player, Piece piece, Spot spot) {
        super(player, piece, spot, null);
        piece.getPlayer().removePiece(piece);
    }
}

package com.rhys.ninemensmorris.Model;

/**
 * Created by Rhys on 14/05/2015.
 */
public class Move {
    private Player player;
    private Piece piece;
    private Spot source;
    private Spot dest;

    public Move(Player player, Piece piece, Spot source, Spot dest) {
        this.player = player;
        this.piece = piece;
        this.source = source;
        this.dest = dest;
        this.piece.setSpot(this.dest);
        this.dest.setPiece(this.piece);
    }
}

package com.rhys.ninemensmorris.Model;

/**
 * Created by Rhys on 14/05/2015.
 */
public class Place implements Move {
    private Player player;
    private Piece piece;
    private Spot src;
    private Spot dest;

    public boolean move(Player player, Piece piece, Spot dest) {
        if (piece.place(dest)) {
            this.player = player;
            this.piece = piece;
            this.src = null;
            this.dest = dest;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void undo() {
        piece.setSpot(src);
        dest.removePiece();
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public Spot getDest() {
        return dest;
    }
}

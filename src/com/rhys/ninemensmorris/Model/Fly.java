package com.rhys.ninemensmorris.Model;

/**
 * Created by Rhys on 20/05/2015.
 */
public class Fly implements Move {
    private Player player;
    private Piece piece;
    private Spot src;
    private Spot dest;

    public boolean move(Player player, Spot src, Spot dest) {
        if (src.getPiece().fly(dest)) {
            this.player = player;
            this.piece = src.getPiece();
            this.src = src;
            this.dest = dest;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void undo() {
        src.setPiece(piece);
        dest.removePiece();
        piece.setSpot(src);
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

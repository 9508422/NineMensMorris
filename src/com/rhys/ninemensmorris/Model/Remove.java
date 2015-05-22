package com.rhys.ninemensmorris.Model;

/**
 * Created by Rhys on 18/05/2015.
 */
public class Remove implements Move {
    private Player player;
    private Piece piece;
    private Spot src;
    private Spot dest;

    public boolean move(Player player, Spot src) {
        if (piece.remove()) {
            this.player = player;
            this.piece = src.getPiece();
            this.src = src;
            this.dest = null;
            this.piece.getPlayer().removePiece(this.piece);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void undo() {
        piece.getPlayer().addPiece(piece);
        piece.setSpot(src);
        src.setPiece(piece);
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

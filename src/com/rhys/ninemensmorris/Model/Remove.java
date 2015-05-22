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
        return move(player, src.getPiece(), src, null);
    }

    @Override
    public boolean move(Player player, Piece piece, Spot src, Spot dest) {
        if (piece.remove()) {
            this.player = player;
            this.piece = piece;
            this.src = src;
            this.dest = dest;
            piece.getPlayer().removePiece(piece);
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
    public Piece getPiece() {
        return piece;
    }

    @Override
    public Spot getDest() {
        return dest;
    }

    @Override
    public Spot getSrc() {
        return src;
    }
}

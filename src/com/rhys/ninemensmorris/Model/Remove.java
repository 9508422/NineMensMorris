package com.rhys.ninemensmorris.Model;

/**
 * @author Rhys Gevaux
 * @version 2015.05.31
 */
public class Remove implements Move {
    private Player player;
    private Piece piece;
    private Spot src;
    private Spot dest;

    @Override
    public boolean validMove(Player player, Piece piece, Spot src, Spot dest) {
        return src.hasPiece() && !player.hasPiece(piece) && piece.validRemove();
    }

    @Override
    public void move(Player player, Piece piece, Spot src, Spot dest) {
        piece.remove();
        this.player = player;
        this.piece = piece;
        this.src = src;
        this.dest = dest;
        this.piece.getPlayer().removePiece(this.piece);
    }

    @Override
    public void move() {
        move(player, piece, src, dest);
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

}

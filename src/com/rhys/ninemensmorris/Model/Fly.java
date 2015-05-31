package com.rhys.ninemensmorris.Model;

/**
 * @author Rhys Gevaux
 * @version 2015.05.31
 */
public class Fly implements Move {
    private Player player;
    private Piece piece;
    private Spot src;
    private Spot dest;

    @Override
    public boolean validMove(Player player, Piece piece, Spot src, Spot dest) {
        return player.hasPiece(piece) && piece.validFly(dest);
    }

    @Override
    public void move(Player player, Piece piece, Spot src, Spot dest) {
        piece.fly(dest);
        this.player = player;
        this.piece = piece;
        this.src = src;
        this.dest = dest;
    }

    @Override
    public void move() {
        move(player, piece, src, dest);
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

}

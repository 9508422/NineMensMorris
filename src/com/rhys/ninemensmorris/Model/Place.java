package com.rhys.ninemensmorris.Model;

/**
 * @author Rhys Gevaux
 * @version 2015.05.27
 */
public class Place implements Move {
    private Player player;
    private Piece piece;
    private Spot src;
    private Spot dest;

    @Override
    public boolean validMove(Player player, Piece piece, Spot src, Spot dest) {
        return player.hasPiece(piece) && piece.validPlace(dest);
    }

    @Override
    public void move(Player player, Piece piece, Spot noSpot, Spot dest) {
        piece.place(dest);
        this.player = player;
        this.piece = piece;
        this.src = noSpot;
        this.dest = dest;
    }

    @Override
    public void move() {
        move(player, piece, src, dest);
    }

    @Override
    public void undo() {
        piece.removeSpot();
        dest.removePiece();
    }

    @Override
    public Player getPlayer() {
        return player;
    }

}

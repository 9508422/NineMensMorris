package com.rhys.ninemensmorris.Model;

/**
 * @author  Rhys Gevaux
 * @version 2015.05.27
 */
public class Remove implements Move {
    private Player player;
    private Piece piece;
    private Spot src;
    private Spot dest;

    /**
     *
     * @param player
     * @param piece
     * @param noSpot
     * @param src
     * @return
     */
    @Override
    public boolean move(Player player, Piece piece, Spot noSpot, Spot src) {
        if (piece.getPlayer() != player && piece.remove()) {
            this.player = player;
            this.piece = piece;
            this.src = src;
            this.dest = noSpot;
            this.piece.getPlayer().removePiece(this.piece);
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     */
    @Override
    public void undo() {
        piece.getPlayer().addPiece(piece);
        piece.setSpot(src);
        src.setPiece(piece);
    }

    /**
     *
     * @return
     */
    @Override
    public Player getPlayer() {
        return player;
    }

    /**
     *
     * @return
     */
    @Override
    public Spot getDest() {
        return dest;
    }
}

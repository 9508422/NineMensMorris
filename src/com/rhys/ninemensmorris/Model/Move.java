package com.rhys.ninemensmorris.Model;

/**
 * @author  Rhys Gevaux
 * @version 2015.05.27
 */
public interface Move {

    /**
     *
     * @param player
     * @param piece
     * @param src
     * @param dest
     * @return
     */
    boolean move(Player player, Piece piece, Spot src, Spot dest);

    /**
     *
     */
    void undo();

    /**
     *
     * @return
     */
    Player getPlayer();

    /**
     *
     * @return
     */
    Spot getDest();
}

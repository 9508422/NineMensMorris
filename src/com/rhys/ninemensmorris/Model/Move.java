package com.rhys.ninemensmorris.Model;

/**
 * Created by Rhys on 14/05/2015.
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

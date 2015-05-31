package com.rhys.ninemensmorris.Model;

/**
 * @author Rhys Gevaux
 * @version 2015.05.31
 */
public interface Move {

    /**
     * @param player the Player making the Move
     * @param piece  the Piece to be moved
     * @param src    the source Spot
     * @param dest   the destination Spot
     * @return if the desired Move is valid
     */
    boolean validMove(Player player, Piece piece, Spot src, Spot dest);

    /**
     * @param player the Player making the Move
     * @param piece  the Piece to be moved
     * @param src    the source Spot
     * @param dest   the destination Spot
     */
    void move(Player player, Piece piece, Spot src, Spot dest);

    /**
     * Required by Game to redo an undone move to correctly set the game state
     */
    void move();

    /**
     * Undoes the Move
     */
    void undo();

    /**
     * @return the Player associated with the Move
     */
    Player getPlayer();

}

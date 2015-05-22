package com.rhys.ninemensmorris.Model;

/**
 * Created by Rhys on 14/05/2015.
 */
public interface Move {

    boolean move(Player player, Piece piece, Spot src, Spot dest);

    void undo();

    Player getPlayer();

    Piece getPiece();

    Spot getDest();

    Spot getSrc();
}

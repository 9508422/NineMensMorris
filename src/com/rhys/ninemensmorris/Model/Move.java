package com.rhys.ninemensmorris.Model;

/**
 * Created by Rhys on 14/05/2015.
 */
public interface Move {

    // --Commented out by Inspection (23/05/2015 00:50):boolean move(Player player, Piece piece, Spot src, Spot dest);

    void undo();

    Player getPlayer();

    Spot getDest();
}

package com.rhys.ninemensmorris.Model;

/**
 * Created by Rhys on 14/05/2015.
 */
public class Place extends Move {
    public Place(Player player, Spot dest) {
        super(player, new Piece(player, dest), null, dest);
    }
}

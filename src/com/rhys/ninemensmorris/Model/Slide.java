package com.rhys.ninemensmorris.Model;

/**
 * Created by Rhys on 20/05/2015.
 */
public class Slide extends Move {
    public Slide(Player player, Spot source, Spot dest) {
        super(player, source.getPiece(), source, dest);
    }
}

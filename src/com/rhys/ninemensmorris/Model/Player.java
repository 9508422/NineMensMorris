package com.rhys.ninemensmorris.Model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rhys on 14/05/2015.
 */
public class Player {
    private Set<Piece> pieceSet;
    private String colour;

    public Player(String colour) {
        pieceSet = new HashSet<Piece>();
        this.colour = colour;
    }

    public Place place(Spot dest) {
        if (!dest.hasPiece()) {
            return new Place(this, dest);
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        return colour;
    }
}

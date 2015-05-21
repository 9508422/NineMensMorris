package com.rhys.ninemensmorris.Model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rhys on 14/05/2015.
 */
public class Spot {
    private Piece piece;
    private final Set<Spot> neighbours;

    public Spot() {
        this.piece = null;
        this.neighbours = new HashSet<Spot>();
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public void setNeighbours(Spot[] neighbours) {
        Collections.addAll(this.neighbours, neighbours);
    }

    public boolean hasNeighbour(Spot neighbour) {
        return neighbours.contains(neighbour);
    }

    public boolean hasPiece() {
        return !(piece == null);
    }

    @Override
    public String toString() {
        if (hasPiece()) {
            return piece.toString();
        } else {
            return "O";
        }
    }
}

package com.rhys.ninemensmorris.Model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rhys on 14/05/2015.
 */
public class Spot {
    private Piece piece;
    private String coord;
    private Set<Spot> neighbours;

    public Spot(String coord) {
        this.piece = null;
        this.coord = coord;
        this.neighbours = new HashSet<Spot>();
    }

    public Piece getPiece() {
        return piece;
    }

    public void setNeighbours(Spot[] neighbours) {
        for (int i = 0; i < neighbours.length; i++) {
            this.neighbours.add(neighbours[i]);
        }
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
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

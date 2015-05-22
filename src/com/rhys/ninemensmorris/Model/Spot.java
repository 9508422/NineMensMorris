package com.rhys.ninemensmorris.Model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rhys on 14/05/2015.
 */
public class Spot {
    private final Set<Spot> neighbours;
    private final String coord;
    private Piece piece;

    public Spot(String coord) {
        this.piece = null;
        this.neighbours = new HashSet<Spot>();
        this.coord = coord;
    }

    public Piece getPiece() {
        return piece;
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

    public void removePiece() {
        piece = null;
    }

    public String getCoord() {
        return coord;
    }

    public Set<Spot> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Spot[] neighbours) {
        Collections.addAll(this.neighbours, neighbours);
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

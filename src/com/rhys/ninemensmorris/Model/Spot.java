package com.rhys.ninemensmorris.Model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Rhys Gevaux
 * @version 2015.05.31
 */
public class Spot {
    private final Set<Spot> neighbours;
    private Piece piece;

    /**
     */
    public Spot() {
        this.neighbours = new HashSet<Spot>();
        this.piece = null;
    }

    /**
     * @return the neighbours of the Spot
     */
    public Set<Spot> getNeighbours() {
        return neighbours;
    }

    /**
     * @param neighbours the neighbours to be set for the Spot
     */
    public void setNeighbours(Spot[] neighbours) {
        Collections.addAll(this.neighbours, neighbours);
    }

    /**
     * @param neighbour the Spot to be checked
     * @return if the Spot has a given Spot as its neighbour
     */
    public boolean hasNeighbour(Spot neighbour) {
        return neighbours.contains(neighbour);
    }

    /**
     * @return the Piece assigned to the Spot
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     * @param piece the Piece to be put on the Spot
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     * @return if the Spot has a Piece
     */
    public boolean hasPiece() {
        return !(piece == null);
    }

    /**
     * Removes the Piece from the Spot
     */
    public void removePiece() {
        piece = null;
    }

    /**
     * @return a String version of the Spot
     */
    @Override
    public String toString() {
        if (hasPiece()) {
            return piece.toString();
        } else {
            return "O";
        }
    }
}

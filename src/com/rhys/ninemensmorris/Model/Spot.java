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

    /**
     *
     * @param coord
     */
    public Spot(String coord) {
        this.neighbours = new HashSet<Spot>();
        this.coord = coord;
        this.piece = null;
    }

    /**
     *
     * @return
     */
    public Set<Spot> getNeighbours() {
        return neighbours;
    }

    /**
     *
     * @param neighbours
     */
    public void setNeighbours(Spot[] neighbours) {
        Collections.addAll(this.neighbours, neighbours);
    }

    /**
     *
     * @param neighbour
     * @return
     */
    public boolean hasNeighbour(Spot neighbour) {
        return neighbours.contains(neighbour);
    }

    /**
     *
     * @return
     */
    public String getCoord() {
        return coord;
    }

    /**
     *
     * @return
     */
    public Piece getPiece() {
        return piece;
    }

    /**
     *
     * @param piece
     */
    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    /**
     *
     */
    public void removePiece() {
        piece = null;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        if (hasPiece()) {
            return piece.toString();
        } else {
            return "O";
        }
    }

    /**
     *
     * @return
     */
    public boolean hasPiece() {
        return !(piece == null);
    }
}

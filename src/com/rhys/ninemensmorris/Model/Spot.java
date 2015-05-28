package com.rhys.ninemensmorris.Model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Rhys Gevaux
 * @version 2015.05.27
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
	 * @return
	 */
	public Set<Spot> getNeighbours() {
		return neighbours;
	}

	/**
	 * @param neighbours
	 */
	public void setNeighbours(Spot[] neighbours) {
		Collections.addAll(this.neighbours, neighbours);
	}

	/**
	 * @param neighbour
	 * @return
	 */
	public boolean hasNeighbour(Spot neighbour) {
		return neighbours.contains(neighbour);
	}

	/**
	 * @return
	 */
	public Piece getPiece() {
		return piece;
	}

	/**
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
	 * @return
	 */
	public boolean hasPiece() {
		return !(piece == null);
	}
}

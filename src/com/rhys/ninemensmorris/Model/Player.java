package com.rhys.ninemensmorris.Model;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Rhys Gevaux
 * @version 2015.05.27
 */
public abstract class Player {
	private final Set<Piece> pieceSet;
	private final String name;
	private final String colour;

	/**
	 * @param name
	 */
	Player(String name) {
		pieceSet = new HashSet<Piece>();
		for (int i = 1; i <= 9; i++) {
			pieceSet.add(new Piece(this));
		}
		this.name = name;
		this.colour = name.substring(0, 1);
	}

	/**
	 * @param gameState
	 * @param src
	 * @param dest
	 * @return
	 */
	public abstract Move move(int gameState, Spot src, Spot dest);

	/**
	 * @return
	 */
	Piece getUnplacedPiece() {
		for (Piece piece : pieceSet) {
			if (!piece.hasSpot()) {
				return piece;
			}
		}
		return null;
	}

	/**
	 * @param piece
	 */
	public void addPiece(Piece piece) {
		pieceSet.add(piece);
	}

	/**
	 * @param piece
	 */
	public void removePiece(Piece piece) {
		pieceSet.remove(piece);
	}

	/**
	 * @return
	 */
	public boolean hasAllPiecesPlaced() {
		for (Piece piece : pieceSet) {
			if (piece.getSpot() == null) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return
	 */
	public boolean hasThreePiecesLeft() {
		return pieceSet.size() == 3;
	}

	/**
	 * @return
	 */
	public boolean hasTwoPiecesLeft() {
		return pieceSet.size() == 2;
	}

	/**
	 * @return
	 */
	public boolean hasNoLegalMove() {
		for (Piece piece : pieceSet) {
			if (piece.hasSpot()) {
				for (Spot spot : piece.getSpot().getNeighbours()) {
					if (!spot.hasPiece()) {
						return false;
					}
				}
			} else {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param toggle
	 * @return
	 */
	public String toString(String toggle) {
		if (toggle.equals("name")) {
			return name;
		} else if (toggle.equals("colour")) {
			return colour;
		}
		return null;
	}

	public boolean allPiecesInMill() {
		for (Piece piece : pieceSet) {
			if (!piece.getInMill()) {
				return false;
			}
		}
		return true;
	}
}

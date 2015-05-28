package com.rhys.ninemensmorris.Model;

/**
 * @author Rhys Gevaux
 * @version 2015.05.27
 */
public class Piece {
	private final Player player;
	private Spot spot;

	/**
	 * @param player
	 */
	public Piece(Player player) {
		this.player = player;
		this.spot = null;
	}

	/**
	 * @return
	 */
	public Spot getSpot() {
		return spot;
	}

	/**
	 * @param spot
	 */
	public void setSpot(Spot spot) {
		this.spot = spot;
	}

	/**
	 * @return
	 */
	public boolean hasSpot() {
		return spot != null;
	}

	/**
	 * @param dest
	 * @return
	 */
	public boolean place(Spot dest) {
		if (!dest.hasPiece()) {
			dest.setPiece(this);
			spot = dest;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @return
	 */
	public boolean remove() {
		if (spot != null) {
			spot.removePiece();
			spot = null;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param dest
	 * @return
	 */
	public boolean slide(Spot dest) {
		if (spot.hasNeighbour(dest) && !dest.hasPiece()) {
			spot.removePiece();
			spot = dest;
			spot.setPiece(this);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param dest
	 * @return
	 */
	public boolean fly(Spot dest) {
		if (!dest.hasPiece()) {
			spot.removePiece();
			spot = dest;
			spot.setPiece(this);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param piece
	 * @return
	 */
	public boolean equals(Piece piece) {
		return player.equals(piece.getPlayer());
	}

	/**
	 * @return
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @return
	 */
	@Override
	public String toString() {
		return player.toString("colour");
	}
}

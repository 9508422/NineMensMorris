package com.rhys.ninemensmorris.Model;

/**
 * @author Rhys Gevaux
 * @version 2015.05.27
 */
public class Piece {
	private final Player player;
	private Spot spot;
	private boolean inMill;

	/**
	 * @param player
	 */
	public Piece(Player player) {
		this.player = player;
		this.spot = null;
		this.inMill = false;
	}

	public boolean getInMill() {
		return inMill;
	}

	public void setInMill() {
		inMill = true;
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

	public boolean validPlace(Spot dest) {
		return !dest.hasPiece();
	}

	/**
	 * @param dest
	 */
	public void place(Spot dest) {
		dest.setPiece(this);
		spot = dest;
	}

	public boolean validRemove() {
		return spot != null && ((!player.allPiecesInMill() && !inMill) || player.allPiecesInMill());
	}

	/**
	 */
	public void remove() {
		spot.removePiece();
		spot = null;
	}

	public boolean validSlide(Spot dest) {
		return spot.hasNeighbour(dest) && !dest.hasPiece();
	}

	/**
	 * @param dest
	 */
	public void slide(Spot dest) {
		spot.removePiece();
		spot = dest;
		spot.setPiece(this);
	}

	public boolean validFly(Spot dest) {
		return !dest.hasPiece();
	}

	/**
	 * @param dest
	 */
	public void fly(Spot dest) {
		spot.removePiece();
		spot = dest;
		spot.setPiece(this);
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

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
	 * Constructor for Piece
	 * Takes in assigned Player
	 * @param player
	 */
	public Piece(Player player) {
		this.player = player;
		this.spot = null;
		this.inMill = false;
	}

	/**
	 * @return if the Piece is in a Mill
	 */
	public boolean getInMill() {
		return inMill;
	}

	/**
	 * Sets if the Piece is or isn't in a Mill
	 */
	public void setInMill(boolean bool) {
		inMill = bool;
	}

	/**
	 * @return the Spot that the Piece is located on
	 */
	public Spot getSpot() {
		return spot;
	}

	/**
	 * Sets the Spot that the Piece is placed on
	 * @param spot The desired Spot for Piece to be placed on
	 */
	public void setSpot(Spot spot) {
		this.spot = spot;
	}

	/**
	 * @return if the Piece is located on a Spot
	 */
	public boolean hasSpot() {
		return spot != null;
	}

	/**
	 * Checks if a Place move is valid
	 * @param dest the destination Spot to be tested
	 * @return if the Place move is valid
	 */
	public boolean validPlace(Spot dest) {
		return !dest.hasPiece();
	}

	/**
	 * Places the Piece on the desired Spot
	 * @param dest the destination Spot for the Piece to be placed on
	 */
	public void place(Spot dest) {
		dest.setPiece(this);
		spot = dest;
	}

	/**
	 * Checks if a Remove move is valid
	 * @return if the Remove move is valid
	 */
	public boolean validRemove() {
		return spot != null && ((!player.allPiecesInMill() && !inMill) || player.allPiecesInMill());
	}

	/**
	 * Removes the Piece from its current spot
	 */
	public void remove() {
		spot.removePiece();
		spot = null;
	}

	/**
	 * Checks if a Slide move is valid
	 * @param dest the destination Spot to be tested
	 * @return if the Slide move is valid
	 */
	public boolean validSlide(Spot dest) {
		return spot.hasNeighbour(dest) && !dest.hasPiece();
	}

	/**
	 * @param dest the destination Spot for the Piece to be placed on
	 */
	public void slide(Spot dest) {
		spot.removePiece();
		spot = dest;
		spot.setPiece(this);
	}

	/**
	 * Checks if a Fly move is valid
	 * @param dest the destination Spot to be tested
	 * @return if the Fly move is valid
	 */
	public boolean validFly(Spot dest) {
		return !dest.hasPiece();
	}

	/**
	 * @param dest the destination Spot for the Piece to be placed on
	 */
	public void fly(Spot dest) {
		spot.removePiece();
		spot = dest;
		spot.setPiece(this);
	}

	/**
	 * Pieces are determined to be equal if they belong to the same Player
	 * @param piece the Piece to be equated too
	 * @return if the Pieces are equal
	 */
	public boolean equals(Piece piece) {
		return player.equals(piece.getPlayer());
	}

	/**
	 * @return the Player that the Piece belongs to
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @return a string containing the "colour" of the Player
	 */
	@Override
	public String toString() {
		return player.toString("colour");
	}
}

package com.rhys.ninemensmorris.Model;

/**
 * @author Rhys Gevaux
 * @version 2015.05.27
 */
public class Place implements Move {
	private Player player;
	private Piece piece;
	private Spot src;
	private Spot dest;

	public boolean validMove(Player player, Piece piece, Spot dest) {
		return piece.validPlace(dest);
	}

	/**
	 * @param player
	 * @param piece
	 * @param noSpot
	 * @param dest
	 * @return
	 */
	@Override
	public void move(Player player, Piece piece, Spot noSpot, Spot dest) {
		piece.place(dest);
		this.player = player;
		this.piece = piece;
		this.src = noSpot;
		this.dest = dest;
	}

	/**
	 *
	 */
	@Override
	public void undo() {
		piece.setSpot(src);
		dest.removePiece();
	}

	/**
	 * @return
	 */
	@Override
	public Player getPlayer() {
		return player;
	}

	/**
	 * @return
	 */
	@Override
	public Spot getDest() {
		return dest;
	}

	/**
	 *
	 * @return
	 */
	@Override
	public Spot getSrc() {
		return src;
	}
}

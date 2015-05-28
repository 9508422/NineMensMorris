package com.rhys.ninemensmorris.Model;

/**
 * @author Rhys Gevaux
 * @version 2015.05.27
 */
public class Fly implements Move {
	private Player player;
	private Piece piece;
	private Spot src;
	private Spot dest;

	/**
	 *
	 * @param player
	 * @param piece
	 * @param dest
	 * @return
	 */
	@Override
	public boolean validMove(Player player, Piece piece, Spot dest) {
		return piece.validFly(dest);
	}

	/**
	 * @param player
	 * @param piece
	 * @param src
	 * @param dest
	 * @return
	 */
	@Override
	public void move(Player player, Piece piece, Spot src, Spot dest) {
		piece.fly(dest);
		this.player = player;
		this.piece = piece;
		this.src = src;
		this.dest = dest;
	}

	/**
	 *
	 */
	@Override
	public void undo() {
		src.setPiece(piece);
		dest.removePiece();
		piece.setSpot(src);
	}

	/**
	 * @return
	 */
	@Override
	public Player getPlayer() {
		return player;
	}

}

package com.rhys.ninemensmorris.Model;

/**
 * @author Rhys Gevaux
 * @version 2015.05.27
 */
public interface Move {

	/**
	 * @param player
	 * @param piece
	 * @param dest
	 * @return
	 */
	boolean validMove(Player player, Piece piece, Spot dest);

	/**
	 * @param player
	 * @param piece
	 * @param src
	 * @param dest
	 */
	void move(Player player, Piece piece, Spot src, Spot dest);

	/**
	 *
	 */
	void move();

	/**
	 *
	 */
	void undo();

	/**
	 * @return
	 */
	Player getPlayer();

}

package com.rhys.ninemensmorris.Model;

import com.rhys.ninemensmorris.Controller.Game;

/**
 * @author Rhys Gevaux
 * @version 2015.05.27
 */
public class Human extends Player {
	public Human(String name) {
		super(name);
	}

	/**
	 * @param gameState
	 * @param src
	 * @param dest
	 * @return
	 */
	@Override
	public Move move(int gameState, Spot src, Spot dest) {
		Move move = null;
		Piece piece = null;

		if (gameState == Game.STATE_PLACE) {
			piece = getUnplacedPiece();
		} else {
			piece = src.getPiece();
		}

		switch (gameState) {
			case Game.STATE_PLACE:
				move = new Place();
				break;
			case Game.STATE_REMOVE:
				move = new Remove();
				break;
			case Game.STATE_SLIDE:
				move = new Slide();
				break;
			case Game.STATE_FLY:
				move = new Fly();
				break;
		}

		if (move != null && move.move(this, piece, src, dest)) {
			return move;
		} else {
			return null;
		}
	}
}

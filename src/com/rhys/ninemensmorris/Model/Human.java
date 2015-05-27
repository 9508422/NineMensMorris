package com.rhys.ninemensmorris.Model;

import com.rhys.ninemensmorris.Controller.Game;

/**
 * @author  Rhys Gevaux
 * @version 2015.05.27
 */
public class Human extends Player {
    public Human(String name) {
        super(name);
    }

    /**
     *
     * @param gameState
     * @param src
     * @param dest
     * @return
     */
    @Override
    public Move move(int gameState, Spot src, Spot dest) {
        Move move = null;
        Move temp;

        switch (gameState) {
            case Game.STATE_PLACE:
                temp = new Place();
                if (temp.move(this, getUnplacedPiece(), src, dest)) {
                    move = temp;
                }
                break;
            case Game.STATE_REMOVE:
                temp = new Remove();
                if (temp.move(this, dest.getPiece(), src, dest)) {
                    move = temp;
                }
                break;
            case Game.STATE_SLIDE:
                temp = new Slide();
                if (temp.move(this, src.getPiece(), src, dest)) {
                    move = temp;
                }
                break;
            case Game.STATE_FLY:
                temp = new Fly();
                if (temp.move(this, src.getPiece(), src, dest)) {
                    move = temp;
                }
                break;
        }

        return move;
    }
}

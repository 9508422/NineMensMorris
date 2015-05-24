package com.rhys.ninemensmorris.Model;

/**
 * Created by Rhys on 14/05/2015.
 */
public interface Move {
    /**
     *
     */
    void undo();

    /**
     *
     * @return
     */
    Player getPlayer();

    /**
     *
     * @return
     */
    Spot getDest();
}

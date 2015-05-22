package com.rhys.ninemensmorris.Model;

/**
 * Created by Rhys on 14/05/2015.
 */
public class Piece {
    private final Player player;
    private Spot spot;

    public Piece(Player player) {
        this.player = player;
        this.spot = null;
    }

    public Player getPlayer() {
        return player;
    }

    public Spot getSpot() {
        return spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public void removeSpot() {
        spot = null;
    }

    @Override
    public String toString() {
        return player.toString("colour");
    }

    public boolean equals(Piece piece) {
        return this.player.equals(piece.player);
    }
}

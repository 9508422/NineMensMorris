package com.rhys.ninemensmorris.Model;

/**
 * Created by Rhys on 14/05/2015.
 */
public class Piece {
    private Spot spot;
    private Player player;

    public Piece (Player player) {
        this.player = player;
        this.spot = null;
    }

    public Piece(Player player, Spot spot) {
        this.player = player;
        this.spot = spot;
    }

    public void setSpot(Spot spot) {
        this.spot = spot;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Spot getSpot() {
        return spot;
    }

    @Override
    public String toString() {
        return player.toString("colour");
    }

    public boolean equals(Piece piece) {
        return this.player.equals(piece.player);
    }
}

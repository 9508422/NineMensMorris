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

    @Override
    public String toString() {
        return player.toString("colour");
    }

    public boolean equals(Piece piece) {
        return player.equals(piece.getPlayer());
    }

    public boolean place(Spot dest) {
        if (!dest.hasPiece()) {
            dest.setPiece(this);
            spot = dest;
            return true;
        } else {
            return false;
        }
    }

    public boolean remove() {
        if (spot != null) {
            spot.removePiece();
            spot = null;
        }
        return true;
    }

    public boolean slide(Spot dest) {
        if (spot.hasNeighbour(dest)) {
            spot.removePiece();
            spot = dest;
            spot.setPiece(this);
            return true;
        } else {
            return false;
        }
    }

    public boolean fly(Spot dest) {
        spot.removePiece();
        spot = dest;
        spot.setPiece(this);
        return true;
    }
}

package com.rhys.ninemensmorris.Model;

/**
 * Created by Rhys on 14/05/2015.
 */
public abstract class Move {
    private final Player player;
    private final Piece piece;
    private final Spot source;
    private final Spot dest;

    Move(Player player, Piece piece, Spot source, Spot dest) {
        this.player = player;
        this.piece = piece;
        this.source = source;
        this.dest = dest;
    }

    public abstract void undo();

    public Player getPlayer() {
        return player;
    }

    Piece getPiece() {
        return piece;
    }

    public Spot getDest() {
        return dest;
    }

    Spot getSource() {
        return source;
    }
}

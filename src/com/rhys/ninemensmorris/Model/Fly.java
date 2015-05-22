package com.rhys.ninemensmorris.Model;

/**
 * Created by Rhys on 20/05/2015.
 */
public class Fly extends Move {
    public Fly(Player player, Spot source, Spot dest) {
        super(player, source.getPiece(), source, dest);

        source.getPiece().setSpot(dest);
        dest.setPiece(source.getPiece());
        source.removePiece();
    }

    @Override
    public void undo() {
        super.getSource().setPiece(super.getPiece());
        super.getDest().removePiece();
        super.getPiece().setSpot(super.getSource());
    }
}

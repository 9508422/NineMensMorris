package com.rhys.ninemensmorris.Model;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Rhys on 14/05/2015.
 */
public class Spot {
    private Piece piece;
    private String coord;

    public Spot(String coord) {
        this.piece = null;
        this.coord = coord;
    }

    public boolean hasPiece() {
        return !(piece == null);
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    @Override
    public String toString() {
        String out = "";
        Set<String> doubleRight = new HashSet<String>();
        doubleRight.add("a1");
        doubleRight.add("a4");
        doubleRight.add("g1");
        doubleRight.add("g4");
        Set<String> singleRight = new HashSet<String>();
        singleRight.add("b2");
        singleRight.add("b4");
        singleRight.add("f2");
        singleRight.add("f4");
        singleRight.add("d3");
        Set<String> end = new HashSet<String>();
        end.add("a7");
        end.add("b6");
        end.add("c5");
        end.add("d7");
        end.add("e5");
        end.add("f6");
        end.add("g7");
        Set<String> doubleLeft = new HashSet<String>();
        doubleLeft.add("c3");
        doubleLeft.add("e3");
        Set<String> singleLeft = new HashSet<String>();
        singleLeft.add("b2");
        singleLeft.add("f2");
        Set<String> singleEndRight = new HashSet<String>();
        singleEndRight.add("c5");
        singleEndRight.add("e5");
        Set<String> lineEnd = new HashSet<String>();
        lineEnd.add("b6");
        lineEnd.add("c5");
        lineEnd.add("e5");
        lineEnd.add("f6");

        if (piece == null) {
            out += "O";
        } else {
            piece.toString();
        }
        if (doubleRight.contains(coord)) {
            out += "--";
        } else if (singleRight.contains(coord)) {
            out += "-";
        }
        if (doubleLeft.contains(coord)) {
            out = "||" + out;
        } else if (singleLeft.contains(coord)) {
            out = "|" + out;
        }
        if (singleEndRight.contains(coord)) {
            out += "|";
        }
        if (lineEnd.contains(coord)) {
            out += "|";
        }
        if (end.contains(coord)) {
            out += "\n";
        }
        return out;
    }
}

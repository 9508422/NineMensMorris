package com.rhys.ninemensmorris.Model;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Rhys on 14/05/2015.
 */
public class Board {
    private Map<String, Spot> spotMap;
    private String[] possibleSpots;
    private Set<Set<Spot>> possibleMills;

    public Board() {
        spotMap = new HashMap<String, Spot>();
        possibleSpots = new String[]{
                "a7",          "d7",          "g7",
                     "b6",     "d6",     "f6",
                          "c5","d5","e5",
                "a4","b4","c4",     "e4","f4","g4",
                          "c3","d3","e3",
                     "b2",     "d2",     "f2",
                "a1",          "d1",          "g1"
        };
        for (String coord : possibleSpots) {
            spotMap.put(coord, new Spot(coord));
        }

        getSpot("a1").setNeighbours(new Spot[]{getSpot("a4"), getSpot("d1")});
        getSpot("a4").setNeighbours(new Spot[]{getSpot("a1"), getSpot("a7"), getSpot("b4")});
        getSpot("a7").setNeighbours(new Spot[]{getSpot("a4"), getSpot("d7")});
        getSpot("b2").setNeighbours(new Spot[]{getSpot("b4"), getSpot("d2")});
        getSpot("b4").setNeighbours(new Spot[]{getSpot("a4"), getSpot("b2"), getSpot("b6"), getSpot("c4")});
        getSpot("b6").setNeighbours(new Spot[]{getSpot("b4"), getSpot("d6")});
        getSpot("c3").setNeighbours(new Spot[]{getSpot("c4"), getSpot("d3")});
        getSpot("c4").setNeighbours(new Spot[]{getSpot("b4"), getSpot("c3"), getSpot("c5")});
        getSpot("c5").setNeighbours(new Spot[]{getSpot("c4"), getSpot("d5")});
        getSpot("d1").setNeighbours(new Spot[]{getSpot("a1"), getSpot("d2"), getSpot("g1")});
        getSpot("d2").setNeighbours(new Spot[]{getSpot("b2"), getSpot("d1"), getSpot("d3"), getSpot("g1")});
        getSpot("d3").setNeighbours(new Spot[]{getSpot("c3"), getSpot("d2"), getSpot("e3")});
        getSpot("d5").setNeighbours(new Spot[]{getSpot("c5"), getSpot("d6"), getSpot("e5")});
        getSpot("d6").setNeighbours(new Spot[]{getSpot("b6"), getSpot("d5"), getSpot("d7"), getSpot("f6")});
        getSpot("d7").setNeighbours(new Spot[]{getSpot("a7"), getSpot("d6"), getSpot("g7")});
        getSpot("e3").setNeighbours(new Spot[]{getSpot("d3"), getSpot("e4")});
        getSpot("e4").setNeighbours(new Spot[]{getSpot("e3"), getSpot("e5"), getSpot("f4")});
        getSpot("e5").setNeighbours(new Spot[]{getSpot("d5"), getSpot("e4")});
        getSpot("f2").setNeighbours(new Spot[]{getSpot("d2"), getSpot("f4")});
        getSpot("f4").setNeighbours(new Spot[]{getSpot("e4"), getSpot("f2"), getSpot("f6"), getSpot("g4")});
        getSpot("f6").setNeighbours(new Spot[]{getSpot("d6"), getSpot("f4")});
        getSpot("g1").setNeighbours(new Spot[]{getSpot("d1"), getSpot("g4")});
        getSpot("g4").setNeighbours(new Spot[]{getSpot("f4"), getSpot("g1"), getSpot("g7")});
        getSpot("g7").setNeighbours(new Spot[]{getSpot("d7"), getSpot("g4")});

        String[][] possibleMillsArray = new String[][]{
                {"a1", "a4", "a7"},
                {"a1", "d1", "g1"},
                {"a4", "b4", "c4"},
                {"a7", "d7", "g7"},
                {"b2", "b4", "b6"},
                {"b2", "d2", "f2"},
                {"b6", "d6", "f6"},
                {"c3", "c4", "c5"},
                {"c3", "d3", "e3"},
                {"c5", "d5", "e5"},
                {"d1", "d2", "d3"},
                {"d5", "d6", "d7"},
                {"e3", "e4", "e5"},
                {"e4", "f4", "g4"},
                {"f2", "f4", "f6"},
                {"g1", "g4", "g7"}
        };

        possibleMills = new HashSet<Set<Spot>>();

        for (String[] possibleMillArray : possibleMillsArray) {
            Set<Spot> possibleMill = new HashSet<Spot>();
            for (String coord : possibleMillArray) {
                possibleMill.add(getSpot(coord));
            }
            possibleMills.add(possibleMill);
        }
    }

    public Spot getSpot(String coord) {
        return spotMap.get(coord);
    }

    public boolean spotExists(String coord) {
        for (int i = 0; i < possibleSpots.length; i++) {
            if (possibleSpots[i].equals(coord) || coord == null) {
                return true;
            }
        }
        return false;
    }

    public boolean wasMillCreated(Spot dest) {
        int count = 0;
        for (Set<Spot> mill : possibleMills) {
            if (mill.contains(dest)) {
                for (Spot spot : mill) {
                    if (spot.hasPiece() && spot.getPiece().equals(dest.getPiece())) {
                        count++;
                    }
                }
                if (count == 3) {
                    return true;
                }
                count = 0;
            }
        }
        return false;
    }
}
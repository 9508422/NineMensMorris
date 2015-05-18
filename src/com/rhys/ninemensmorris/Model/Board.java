package com.rhys.ninemensmorris.Model;



import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rhys on 14/05/2015.
 */
public class Board {
    private Map<String, Spot> spotMap;
    private String[] possibleSpots;
    private Spot[][] possibleMills;

    public Board() {
        spotMap = new HashMap<String, Spot>();
        possibleSpots = new String[] {
                "g1",             "g4",             "g7",
                      "f2",       "f4",       "f6",
                            "e3", "e4", "e5",
                "d1", "d2", "d3",       "d5", "d6", "d7",
                            "c3", "c4", "c5",
                      "b2",       "b4",       "b6",
                "a1",             "a4",             "a7"
        };
        for (String coord : possibleSpots) {
            spotMap.put(coord, new Spot(coord));
        }
        possibleMills = new Spot[][] {
                {getSpot("a1"), getSpot("a4"), getSpot("a7")},
                {getSpot("a1"), getSpot("d1"), getSpot("g1")},
                {getSpot("a4"), getSpot("b4"), getSpot("c4")},
                {getSpot("a7"), getSpot("d7"), getSpot("g7")},
                {getSpot("b2"), getSpot("b4"), getSpot("b6")},
                {getSpot("b2"), getSpot("d2"), getSpot("f2")},
                {getSpot("b6"), getSpot("d6"), getSpot("f6")},
                {getSpot("c3"), getSpot("c4"), getSpot("c5")},
                {getSpot("c3"), getSpot("d3"), getSpot("e3")},
                {getSpot("c5"), getSpot("d5"), getSpot("e5")},
                {getSpot("d1"), getSpot("d2"), getSpot("d3")},
                {getSpot("d5"), getSpot("d6"), getSpot("d7")},
                {getSpot("e3"), getSpot("e4"), getSpot("e5")},
                {getSpot("e4"), getSpot("f4"), getSpot("g4")},
                {getSpot("f2"), getSpot("f4"), getSpot("f6")},
                {getSpot("g1"), getSpot("g4"), getSpot("g7")}
        };
    }

    public Spot getSpot(String coord) {
        return spotMap.get(coord);
    }

    public String[] getPossibleSpots() {
        return possibleSpots;
    }
}
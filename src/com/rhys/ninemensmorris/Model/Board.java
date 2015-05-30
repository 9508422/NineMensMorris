package com.rhys.ninemensmorris.Model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Rhys Gevaux
 * @version 2015.05.27
 */
public class Board {
	private static final Board INSTANCE = new Board();

	private final Map<String, Spot> spotMap;
	private final Map<Spot, Spot[][]> possibleMills;

	private final Set<Spot[]> mills;

	/**
	 * Board constructor:
	 * Creates a Hash Map of Spots for easy spot getting from input
	 * Statically sets all the neighbours of each Spot
	 * Statically stores all possible Mills
	 * Creates new Hash Set to hold all current Mills
	 */
	private Board() {
		spotMap = new HashMap<String, Spot>();
		String[] possibleSpots = new String[]{
				"a7",             "d7",             "g7",
				      "b6",       "d6",       "f6",
				            "c5", "d5", "e5",
				"a4", "b4", "c4",       "e4", "f4", "g4",
				            "c3", "d3", "e3",
				      "b2",       "d2",       "f2",
				"a1",             "d1",             "g1"
		};
		for (String coord : possibleSpots) {
			spotMap.put(coord, new Spot());
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

		possibleMills = new HashMap<Spot, Spot[][]>();

		possibleMills.put(getSpot("a1"), new Spot[][]{
				{getSpot("a1"), getSpot("a4"), getSpot("a7")},
				{getSpot("a1"), getSpot("d1"), getSpot("g1")}
		});
		possibleMills.put(getSpot("a4"), new Spot[][]{
				{getSpot("a1"), getSpot("a4"), getSpot("a7")},
				{getSpot("a4"), getSpot("b4"), getSpot("c4")}
		});
		possibleMills.put(getSpot("a7"), new Spot[][]{
				{getSpot("a1"), getSpot("a4"), getSpot("a7")},
				{getSpot("a7"), getSpot("d7"), getSpot("g7")}
		});
		possibleMills.put(getSpot("b2"), new Spot[][]{
				{getSpot("b2"), getSpot("b4"), getSpot("b6")},
				{getSpot("b2"), getSpot("d2"), getSpot("f2")}
		});
		possibleMills.put(getSpot("b4"), new Spot[][]{
				{getSpot("a4"), getSpot("b4"), getSpot("c4")},
				{getSpot("b2"), getSpot("b4"), getSpot("b6")}
		});
		possibleMills.put(getSpot("b6"), new Spot[][]{
				{getSpot("b2"), getSpot("b4"), getSpot("b6")},
				{getSpot("b6"), getSpot("d6"), getSpot("f6")}
		});
		possibleMills.put(getSpot("c3"), new Spot[][]{
				{getSpot("c3"), getSpot("c4"), getSpot("c5")},
				{getSpot("c3"), getSpot("d3"), getSpot("e3")}
		});
		possibleMills.put(getSpot("c4"), new Spot[][]{
				{getSpot("a4"), getSpot("b4"), getSpot("c4")},
				{getSpot("c3"), getSpot("c4"), getSpot("c5")}
		});
		possibleMills.put(getSpot("c5"), new Spot[][]{
				{getSpot("c3"), getSpot("c4"), getSpot("c5")},
				{getSpot("c5"), getSpot("d5"), getSpot("e5")}
		});
		possibleMills.put(getSpot("d1"), new Spot[][]{
				{getSpot("a1"), getSpot("d1"), getSpot("g1")},
				{getSpot("d1"), getSpot("d2"), getSpot("d3")}
		});
		possibleMills.put(getSpot("d2"), new Spot[][]{
				{getSpot("b2"), getSpot("d2"), getSpot("f2")},
				{getSpot("d1"), getSpot("d2"), getSpot("d3")}
		});
		possibleMills.put(getSpot("d3"), new Spot[][]{
				{getSpot("c3"), getSpot("d3"), getSpot("e3")},
				{getSpot("d1"), getSpot("d2"), getSpot("d3")}
		});
		possibleMills.put(getSpot("d5"), new Spot[][]{
				{getSpot("c5"), getSpot("d5"), getSpot("e5")},
				{getSpot("d5"), getSpot("d6"), getSpot("d7")}
		});
		possibleMills.put(getSpot("d6"), new Spot[][]{
				{getSpot("b6"), getSpot("d6"), getSpot("f6")},
				{getSpot("d5"), getSpot("d6"), getSpot("d7")}
		});
		possibleMills.put(getSpot("d7"), new Spot[][]{
				{getSpot("a7"), getSpot("d7"), getSpot("g7")},
				{getSpot("d5"), getSpot("d6"), getSpot("d7")}
		});
		possibleMills.put(getSpot("e3"), new Spot[][]{
				{getSpot("c3"), getSpot("d3"), getSpot("e3")},
				{getSpot("e3"), getSpot("e4"), getSpot("e5")}
		});
		possibleMills.put(getSpot("e4"), new Spot[][]{
				{getSpot("e3"), getSpot("e4"), getSpot("e5")},
				{getSpot("e4"), getSpot("f4"), getSpot("g4")}
		});
		possibleMills.put(getSpot("e5"), new Spot[][]{
				{getSpot("c5"), getSpot("d5"), getSpot("e5")},
				{getSpot("e3"), getSpot("e4"), getSpot("e5")}
		});
		possibleMills.put(getSpot("f2"), new Spot[][]{
				{getSpot("b2"), getSpot("d2"), getSpot("f2")},
				{getSpot("f2"), getSpot("f4"), getSpot("f6")}
		});
		possibleMills.put(getSpot("f4"), new Spot[][]{
				{getSpot("e4"), getSpot("f4"), getSpot("g4")},
				{getSpot("f2"), getSpot("f4"), getSpot("f6")}
		});
		possibleMills.put(getSpot("f6"), new Spot[][]{
				{getSpot("b6"), getSpot("d6"), getSpot("f6")},
				{getSpot("f2"), getSpot("f4"), getSpot("f6")}
		});
		possibleMills.put(getSpot("g1"), new Spot[][]{
				{getSpot("a1"), getSpot("d1"), getSpot("g1")},
				{getSpot("g1"), getSpot("g4"), getSpot("g7")}
		});
		possibleMills.put(getSpot("g4"), new Spot[][]{
				{getSpot("e4"), getSpot("f4"), getSpot("g4")},
				{getSpot("g1"), getSpot("g4"), getSpot("g7")}
		});
		possibleMills.put(getSpot("g7"), new Spot[][]{
				{getSpot("a7"), getSpot("d7"), getSpot("g7")},
				{getSpot("g1"), getSpot("g4"), getSpot("g7")}
		});

		mills = new HashSet<Spot[]>();
	}

	/**
	 * @param coord coordinate of desired spot
	 * @return the spot connected to given coordinate
	 */
	public Spot getSpot(String coord) {
		return spotMap.get(coord);
	}

	/**
	 * @return instance of Board
	 */
	public static Board getInstance() {
		return INSTANCE;
	}

	/**
	 * @param coords coordinates of Spots to check for their existence
	 * @return if the Board has Spots at all the given coordinates
	 */
	public boolean hasSpots(String[] coords) {
		for (String coord : coords) {
			if (!hasSpot(coord)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param coord coordinate of the Spot to check for its existence
	 * @return if the Board has a Spot at the given coordinate
	 */
	public boolean hasSpot(String coord) {
		for (String possibleSpot : spotMap.keySet()) {
			if (possibleSpot.equals(coord)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return if a Mill was created
	 */
	public boolean updateMills() {
		boolean millCreated = false;

		for (Spot spot : possibleMills.keySet()) {
			if (spot.hasPiece()) {
				spot.getPiece().setInMill(false);
			}
			for (Spot[] possibleMill : possibleMills.get(spot)) {
				if (allHavePieces(possibleMill) && allPiecesEqual(possibleMill)) {
					spot.getPiece().setInMill(true);
					if (!mills.contains(possibleMill)) {
						mills.add(possibleMill);
						millCreated = true;
					}
				} else {
					mills.remove(possibleMill);
				}
			}
		}

		return millCreated;
	}

	/**
	 * @param spots the Spots to check for if they have Pieces on them
	 * @return if all the Spots have Pieces on them
	 */
	private boolean allHavePieces(Spot[] spots) {
		for (Spot spot : spots) {
			if (!spot.hasPiece()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @param spots the Spots to check if all the Pieces on them belong to the same Player
	 * @return if all the Spots have Pieces belonging to the same Player
	 */
	private boolean allPiecesEqual(Spot[] spots) {
		for (int i = 1; i < spots.length; i++) {
			if (!spots[i].getPiece().equals(spots[i - 1].getPiece())) {
				return false;
			}
		}
		return true;
	}
}
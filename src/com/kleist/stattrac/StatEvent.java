package com.kleist.stattrac;

import java.util.EnumMap;

public enum StatEvent {
	INCOMPLETE, CATCH, FIRSTDOWN, TOUCHDOWN, EXTRAPOINT, TWOPOINTCONVERSION, SAFETY, SACK, SACKED, INT_THROWN, INT_CAUGHT;
	
	public static Boolean defensiveStat(StatEvent event) {
		switch (event) {
		case INT_CAUGHT:
		case SACK:
			return true;
		default:
			return false;
		}
	}

	private static final EnumMap<StatEvent, Integer> pointsPerEvent_ = initPointsPerEvent();
	
	private static EnumMap<StatEvent, Integer> initPointsPerEvent() {
		EnumMap<StatEvent, Integer> pointMap = new EnumMap<StatEvent, Integer>(StatEvent.class);
		for (StatEvent event : StatEvent.values()) {
			pointMap.put(event, 0);
		}
		pointMap.put(StatEvent.EXTRAPOINT, 1);
		pointMap.put(StatEvent.SAFETY, 2);
		pointMap.put(StatEvent.TOUCHDOWN, 6);
		pointMap.put(StatEvent.TWOPOINTCONVERSION, 2);
		return pointMap;
	}
	
	public int getPoints() {
		return pointsPerEvent_.get(this);
	}

	static boolean isTurnover(StatEvent event) {
		switch (event) {
		case EXTRAPOINT:
		case INT_CAUGHT:
		case INT_THROWN:
			return true;
		}
		return false;
	}
}

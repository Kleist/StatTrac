package com.kleist.stattrac;

import java.util.EnumMap;


public class PointCounter {
	private StatCounter statCounter_;
	private final EnumMap<StatEvent, Integer> pointsPerEvent_ = initPointsPerEvent();

	public PointCounter(StatCounter sc) {
		statCounter_ = sc;
	}

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
		int points = 0;
		for (StatEvent event : StatEvent.values()) {
			points += pointsPerEvent_.get(event) * statCounter_.getEventCount(event);
		}
		return points;
	}
}

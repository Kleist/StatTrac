package com.kleist.stattrac;

public class PointCounter {
	private StatCounter statCounter_;

	public PointCounter(StatCounter sc) {
		statCounter_ = sc;
	}

	public int getPoints() {
		int points = 0;
		for (StatEvent event : StatEvent.values()) {
			points += event.getPoints() * statCounter_.getEventCount(event);
		}
		return points;
	}
}

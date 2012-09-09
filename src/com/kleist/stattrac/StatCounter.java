package com.kleist.stattrac;

import java.util.EnumMap;

public class StatCounter {
	public EnumMap<StatEvent, Integer> stats_;

	public StatCounter() {
		stats_ = new EnumMap<StatEvent, Integer>(StatEvent.class);
		for (StatEvent event : StatEvent.values()) {
			stats_.put(event, 0);
		}
	}

	public void addEvent(StatEvent event) {
		stats_.put(event, 1 + stats_.get(event));
	}
	
	public int getEventCount(StatEvent event) {
		return stats_.get(event);
	}
}
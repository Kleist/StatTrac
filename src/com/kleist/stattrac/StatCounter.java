package com.kleist.stattrac;

import java.util.EnumMap;

public class StatCounter {
	private EnumMap<StatEvent, Integer> stats_;
	private Player player_;

	public StatCounter(Player player) {
		stats_ = new EnumMap<StatEvent, Integer>(StatEvent.class);
		for (StatEvent event : StatEvent.values()) {
			stats_.put(event, 0);
		}
		player_ = player;
	}

	public void addEvent(StatEvent event) {
		stats_.put(event, 1 + stats_.get(event));
	}
	
	public int getEventCount(StatEvent event) {
		return stats_.get(event);
	}

	public Player getPlayer() {
		return player_;
	}
}
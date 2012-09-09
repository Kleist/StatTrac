package com.kleist.stattrac;

import java.util.AbstractList;
import java.util.Vector;

public final class Team {

	private String name_;
	private Vector<Player> players_;

	public Team(String name) {
		name_ = name;
		players_ = new Vector<Player>();
	}

	public String getName() {
		return name_;
	}

	public AbstractList<Player> getPlayers() {
		return players_;
	}

	public void addPlayer(Player p) {
		players_.add(p);
	}
}

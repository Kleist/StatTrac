package com.kleist.stattrac;

public class Player {
	private String name_;
	private int number_;
	private StatCounter statCounter_;

	public Player(String name, int number) {
		name_ = name;
		number_ = number;
	}

	public String getName() {
		return name_;
	}
	
	public int getNumber() {
		return number_;
	}

	public StatCounter getStatCounter() {
		return statCounter_;
	}

	public void addEvent(StatEvent event) {
		statCounter_.addEvent(event);
	}

	public void setStatCounter(StatCounter statCounter) {
		statCounter_ = statCounter;
	}
}

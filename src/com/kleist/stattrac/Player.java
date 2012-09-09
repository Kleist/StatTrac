package com.kleist.stattrac;

public final class Player {
	private String name_;
	private int number_;

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
}

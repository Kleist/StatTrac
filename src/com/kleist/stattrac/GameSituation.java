package com.kleist.stattrac;

public class GameSituation {
	public Possession possession_ = Possession.NOT_SET;

	public GameSituation() {
	}
	
	public void setPossession(Possession poss) {
		possession_ = poss;
	}

	public Possession getPossession() {
		return possession_;
	}

	public void turnover() throws Exception {
		switch (possession_) {
		case HOME:
			possession_ = Possession.AWAY;
			break;
		case AWAY:
			possession_ = Possession.HOME;
			break;
		case NOT_SET:
			throw new Exception("Cannot change possession before possession is set!");
		}
	}
}
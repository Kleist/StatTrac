package com.kleist.stattrac;


public class GameStats {

	private Possession possessingTeam_ = Possession.NOT_SET;

	public GameScore score() {
		return new GameScore();
	}

	public Possession getPossession() {
		return possessingTeam_;
	}

	public void setPosession(Possession poss) {
		possessingTeam_ = poss;
	}

	public void turnover() throws Exception {
		switch (possessingTeam_) {
		case HOME:
			possessingTeam_ = Possession.AWAY;
			break;
		case AWAY:
			possessingTeam_ = Possession.HOME;
			break;
		case NOT_SET:
			throw new Exception("Cannot change possession before possession is set!");
		}
	}

	public void addEvent(StatEvent event, Player attacker, Player defender) throws Exception {
		attacker.addEvent(event);
		if (defender!= null) defender.addEventAgainst(event);
		
		if (isTurnover(event)) {
			turnover();
		}
	}

	private static boolean isTurnover(StatEvent event) {
		switch (event) {
		case EXTRAPOINT:
		case INT_CAUGHT:
		case INT_THROWN:
			return true;
		}
		return false;
	}

}

package com.kleist.stattrac;


public class GameStats {
	public GameSituation gameSituation_ = new GameSituation();

	public GameScore score() {
		return new GameScore();
	}

	public void addEvent(StatEvent event, Player attacker, Player defender) throws Exception {
		attacker.addEvent(event);
		if (defender!= null) defender.addEventAgainst(event);
		
		if (StatEvent.isTurnover(event)) {
			gameSituation_.turnover();
		}
	}

	public void setGameSituation(GameSituation gameSituation) {
		gameSituation_ = gameSituation;
	}

}

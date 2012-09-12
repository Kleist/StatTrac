package com.kleist.stattrac;

public class GameSituation {
	private static final int SECONDS_PER_MINUTE = 60;
	private static final long MILLISECONDS_PER_SECOND = 1000;
	private static final long MILLISECONDS_PER_MINUTE = SECONDS_PER_MINUTE*MILLISECONDS_PER_SECOND;
	public Possession possession_ = Possession.NOT_SET;
	private WallClockTimer wallClock_;

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

	public void setClock(WallClockTimer clock) {
		wallClock_ = clock;		
	}

	public void startGame() {
		wallClock_.resetClock();
	}

	public String getGameClockString() {
		long millis = wallClock_.getMilliSecondsSinceReset();
		int minutes = (int) (millis/MILLISECONDS_PER_MINUTE);
		int seconds = (int) (millis/MILLISECONDS_PER_SECOND)%SECONDS_PER_MINUTE;
		int tenthsOfASecond = (int)(millis/ (MILLISECONDS_PER_SECOND/10)) % 10;
		return String.format("%02d:%02d.%01d", minutes, seconds, tenthsOfASecond);
	}
}
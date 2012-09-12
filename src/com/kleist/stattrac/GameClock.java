package com.kleist.stattrac;

public class GameClock {
	private static final int SECONDS_PER_MINUTE = 60;
	private static final long MILLISECONDS_PER_SECOND = 1000;
	private static final long MILLISECONDS_PER_MINUTE = SECONDS_PER_MINUTE*MILLISECONDS_PER_SECOND;

	private WallClockTimer wallClock_;

	void resetClock() {
		wallClock_.resetClock();
	}

	public String toString() {
		long millis = wallClock_.getMilliSecondsSinceReset();
		int minutes = (int) (millis/GameClock.MILLISECONDS_PER_MINUTE);
		int seconds = (int) (millis/GameClock.MILLISECONDS_PER_SECOND)%GameClock.SECONDS_PER_MINUTE;
		int tenthsOfASecond = (int)(millis/ (GameClock.MILLISECONDS_PER_SECOND/10)) % 10;
		return String.format("%02d:%02d.%01d", minutes, seconds, tenthsOfASecond);
	}
	
	public void setClock(WallClockTimer clock) {
		wallClock_ = clock;
	}
}
package com.kleist.stattrac;

public class GameClock {
	private static final int SECONDS_PER_MINUTE = 60;
	private static final long MILLISECONDS_PER_SECOND = 1000;
	private static final long MILLISECONDS_PER_MINUTE = SECONDS_PER_MINUTE*MILLISECONDS_PER_SECOND;
	private static final long MINUTES_PER_HALF = 20;

	private WallClockTimer wallClock_;
	private long pauseTime_;
	private boolean paused_;

	void resetClock() {
		wallClock_.resetClock();
	}

	public String getString() {
		long millis = getMillisPlayed();
		int minutes = (int) (millis/GameClock.MILLISECONDS_PER_MINUTE);
		int seconds = (int) (millis/GameClock.MILLISECONDS_PER_SECOND)%GameClock.SECONDS_PER_MINUTE;
		int tenthsOfASecond = (int)(millis/ (GameClock.MILLISECONDS_PER_SECOND/10)) % 10;
		return String.format("%02d:%02d.%01d", minutes, seconds, tenthsOfASecond);
	}

	private long getMillisPlayed() {
		if (paused_) {
			return pauseTime_;
		}
		else {
			return MILLISECONDS_PER_MINUTE*MINUTES_PER_HALF - wallClock_.getMilliSecondsSinceReset();
		}
	}
	
	public void setClock(WallClockTimer clock) {
		wallClock_ = clock;
	}

	public void pause() {
		pauseTime_ = getMillisPlayed();
		paused_ = true;
	}
}
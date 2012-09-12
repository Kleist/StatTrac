package com.kleist.stattrac;

public class GameClock {
	private static final int SECONDS_PER_MINUTE = 60;
	private static final long MILLIS_PER_SECOND = 1000;
	private static final long MINUTES_PER_HALF = 20;
	private static final long MILLIS_PER_MINUTE = SECONDS_PER_MINUTE*MILLIS_PER_SECOND;
	private static final long MILLIS_PER_HALF = MILLIS_PER_MINUTE*MINUTES_PER_HALF;

	private WallClockTimer wallClock_;
	private long pauseTime_;
	private boolean paused_;
	private long baseTime_;

	void resetClock() {
		wallClock_.resetClock();
	}

	public String getString() {
		long millis = getMillisLeft();
		int minutes = (int) (millis/GameClock.MILLIS_PER_MINUTE);
		int seconds = (int) (millis/GameClock.MILLIS_PER_SECOND)%GameClock.SECONDS_PER_MINUTE;
		int tenthsOfASecond = (int)(millis/ (GameClock.MILLIS_PER_SECOND/10)) % 10;
		return String.format("%02d:%02d.%01d", minutes, seconds, tenthsOfASecond);
	}

	private long getMillisLeft() {
		if (paused_) return pauseTime_;
		else return MILLIS_PER_HALF - wallClock_.getMilliSecondsSinceReset() + baseTime_;
	}
	
	public void setClock(WallClockTimer clock) {
		wallClock_ = clock;
	}

	public void pause() {
		pauseTime_ = getMillisLeft();
		paused_ = true;
	}

	public void start() {
		baseTime_ = wallClock_.getMilliSecondsSinceReset();
	}
}
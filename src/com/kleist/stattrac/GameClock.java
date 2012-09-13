package com.kleist.stattrac;

public class GameClock {
	public static final int SECONDS_PER_MINUTE = 60;
	public static final long MILLIS_PER_SECOND = 1000;
	public static final long MINUTES_PER_HALF = 20;
	public static final long MILLIS_PER_MINUTE = SECONDS_PER_MINUTE*MILLIS_PER_SECOND;
	public static final long MILLIS_PER_HALF = MILLIS_PER_MINUTE*MINUTES_PER_HALF;

	private WallClockTimer wallClock_;
	private GameClockFormatter formatter_;

	private boolean stopped_;
	private long startTime_;
	private long playedTimeUntilLastStart_;

	public GameClock() {
		stopped_ = true;
	}
	
	public void reset() {
		playedTimeUntilLastStart_ = 0;
		stopped_ = true;
	}

	public String getString() {
		return formatter_.formatTime(getMillisLeft());
	}

	private long getMillisPlayed() {
		if (stopped_) return playedTimeUntilLastStart_;
		else return playedTimeUntilLastStart_ + wallClock_.getMillis() - startTime_;
	}
	
	public long getMillisLeft() {
		return MILLIS_PER_HALF - getMillisPlayed();
	}
	
	public void setClock(WallClockTimer clock) {
		wallClock_ = clock;
	}

	public void stop() {
		playedTimeUntilLastStart_ += wallClock_.getMillis()-startTime_;
		stopped_ = true;
	}

	public void start() {
		startTime_ = wallClock_.getMillis();
		stopped_ = false;
	}

	public void setFormatter(GameClockFormatter formatter) {
		formatter_ = formatter;
	}
}
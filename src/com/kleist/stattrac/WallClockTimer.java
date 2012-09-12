package com.kleist.stattrac;

public class WallClockTimer {
	private long baseTime_;

	public WallClockTimer() {
	}
	
	public void resetClock() {
		baseTime_ = System.currentTimeMillis();
	}

	public long getMilliSecondsSinceReset() {
		return System.currentTimeMillis() - baseTime_;
	}
}

package com.kleist.stattrac;

public class WallClockTimer {
	public interface TimeSource {
		public abstract long milliseconds();
	}
	private TimeSource timeSource_;
	private long baseTime_;

	public WallClockTimer() {
		timeSource_ = new TimeSource() {
			public long milliseconds() {
				return System.currentTimeMillis();				
			}
		};
	}
	
	public void resetClock() {
		baseTime_ = timeSource_.milliseconds();
	}

	public void setTimeSource(TimeSource timeSource) {
		timeSource_ = timeSource;
	}

	public long getMilliSecondsSinceReset() {
		return timeSource_.milliseconds() - baseTime_;
	}
}

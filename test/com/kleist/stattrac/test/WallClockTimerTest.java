package com.kleist.stattrac.test;

import static org.junit.Assert.*;

import org.junit.*;

import com.kleist.stattrac.WallClockTimer;


public class WallClockTimerTest {
	private WallClockTimer clock;
	protected long timeInMillis;
	
	@Before
	public void setUp() {
		clock = new WallClockTimer();
		clock.setTimeSource(new WallClockTimer.TimeSource(){
			public long milliseconds() {
				return timeInMillis;
			}
		});
	}
	
	@Test
	public void returnsZeroAfterReset() {
		clock.resetClock();
		assertEquals(0, clock.getMilliSecondsSinceReset());
	}

	@Test
	public void returnsIncrementAfterResetAndChange() {
		timeInMillis = 10;
		clock.resetClock();
		timeInMillis = 20;
		assertEquals(10, clock.getMilliSecondsSinceReset());
	}
}

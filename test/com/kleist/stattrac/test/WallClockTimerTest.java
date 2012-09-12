package com.kleist.stattrac.test;

import static org.junit.Assert.*;

import org.junit.*;

import com.kleist.stattrac.WallClockTimer;


public class WallClockTimerTest {
	@Test
	public void timeIncreases() throws InterruptedException {
		WallClockTimer timer = new WallClockTimer();
		long before = timer.getMillis();
		Thread.sleep(100);
		long after = timer.getMillis();
		assertTrue(after>before);
	}
}

package com.kleist.stattrac.test;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import com.kleist.stattrac.GameClockFormatter;


public class GameClockFormatterTest {
	private GameClockFormatter formatter;

	@Before
	public void setUp() {
		formatter = new GameClockFormatter();
	}

	@Test
	public void resetClockFormat() {
		assertEquals("20:00.0", formatter.formatTime(20*60*1000));
	}

	@Test
	public void formatsMinutesSecondsAndTenths() {
		assertEquals("20:00.0", formatter.formatTime(20*60*1000));
		assertEquals("01:02.3", formatter.formatTime(300 + 1000*(2+60*1)));
	}
}
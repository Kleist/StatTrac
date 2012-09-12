package com.kleist.stattrac.test;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.*;

import com.kleist.stattrac.GameClock;
import com.kleist.stattrac.GameClockFormatter;
import com.kleist.stattrac.WallClockTimer;


public class GameClockFormatterTest {
	private GameClock gameClock;
	private GameClockFormatter formatter;
	private WallClockTimer clock;

	@Before
	public void setUp() {
		clock = EasyMock.createMock(WallClockTimer.class);
		gameClock = new GameClock();
		gameClock.setClock(clock);
		formatter = new GameClockFormatter();
	}

	public GameClockFormatterTest(long millisLeft) {
		gameClock = new GameClock();
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
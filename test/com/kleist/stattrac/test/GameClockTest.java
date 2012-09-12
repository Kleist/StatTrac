package com.kleist.stattrac.test;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.*;

import com.kleist.stattrac.GameClock;
import com.kleist.stattrac.WallClockTimer;


public class GameClockTest {
	private GameClock gameClock;
	private WallClockTimer clock;

	@Before
	public void setUp() {
		clock = EasyMock.createMock(WallClockTimer.class);
		gameClock = new GameClock();
		gameClock.setClock(clock);
	}
	
	public GameClockTest() {
		gameClock = new GameClock();
	}
	
	@Test
	public void resetClockFormat() {
		assertEquals("20:00.0", gameClock.getString());
	}

	@Test
	public void clockCountsDown() throws Exception {
		EasyMock.expect(clock.getMilliSecondsSinceReset()).andReturn((long) 20000);
		EasyMock.replay(clock);
		assertEquals("19:40.0", gameClock.getString());
	}

	@Test
	public void clockCountsMinutesSecondsAndTenths() throws Exception {
		EasyMock.expect(clock.getMilliSecondsSinceReset()).andReturn((long) 61700);
		EasyMock.replay(clock);
		assertEquals("18:58.3", gameClock.getString());
	}
	
}

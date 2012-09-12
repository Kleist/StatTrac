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
	public void clockCountsDownWhenStarted() {
		EasyMock.expect(clock.getMilliSecondsSinceReset()).andReturn((long) 10000);
		EasyMock.expect(clock.getMilliSecondsSinceReset()).andReturn((long) 20000);
		EasyMock.replay(clock);
		gameClock.start();
		assertEquals("19:50.0", gameClock.getString());
	}

	@Test
	public void clockCountsMinutesSecondsAndTenths() {
		EasyMock.expect(clock.getMilliSecondsSinceReset()).andReturn((long) 61700);
		EasyMock.replay(clock);
		assertEquals("18:58.3", gameClock.getString());
	}

	@Test
	public void pauseClock(){
		EasyMock.expect(clock.getMilliSecondsSinceReset()).andReturn((long) 1000);
		EasyMock.expect(clock.getMilliSecondsSinceReset()).andReturn((long) 2000);
		EasyMock.expect(clock.getMilliSecondsSinceReset()).andReturn((long) 3000);
		EasyMock.replay(clock);
		gameClock.pause();
		assertEquals("19:59.0", gameClock.getString());
		assertEquals("19:59.0", gameClock.getString());
	}
}

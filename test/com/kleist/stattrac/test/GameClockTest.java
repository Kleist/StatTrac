package com.kleist.stattrac.test;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.*;

import com.kleist.stattrac.GameClock;
import com.kleist.stattrac.GameSituation;
import com.kleist.stattrac.Possession;
import com.kleist.stattrac.WallClockTimer;


public class GameClockTest {
	private GameClock gameClock;
	private GameSituation gameSituation;
	private WallClockTimer clock;

	@Before
	public void setUp() {
		gameSituation = new GameSituation();
		clock = EasyMock.createMock(WallClockTimer.class);
		gameSituation.gameClock_.setClock(clock);
		gameClock = gameSituation.gameClock_;
	}
	
	public GameClockTest() {
		gameClock = new GameClock();
	}
	
	@Test
	public void resetClockFormat() {
		assertEquals("00:00.0", gameClock.toString());
	}

	@Test
	public void clockDoesntStopOnTurnoverBefore2Min() throws Exception {
		EasyMock.expect(clock.getMilliSecondsSinceReset()).andReturn((long) 20000);
		EasyMock.replay(clock);
		assertEquals("00:20.0", gameClock.toString());
	}

	@Test
	public void clockCountsMinutesSecondsAndTenths() throws Exception {
		EasyMock.expect(clock.getMilliSecondsSinceReset()).andReturn((long) 61700);
		EasyMock.replay(clock);
		assertEquals("01:01.7", gameClock.toString());
	}
	
}

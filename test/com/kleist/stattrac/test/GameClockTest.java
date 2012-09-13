package com.kleist.stattrac.test;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.*;

import com.kleist.stattrac.GameClock;
import com.kleist.stattrac.GameClockFormatter;
import com.kleist.stattrac.WallClockTimer;


public class GameClockTest {
	private GameClock gameClock;
	private GameClockFormatter formatter;
	private WallClockTimer clock;

	@Before
	public void setUp() {
		clock = EasyMock.createMock(WallClockTimer.class);
		formatter = EasyMock.createMock(GameClockFormatter.class);
		gameClock = new GameClock();
		gameClock.setClock(clock);
		gameClock.setFormatter(formatter);
	}
	
	private void mockOneSecondPerCall() {
		setMockCallTimeDiff(GameClock.MILLIS_PER_SECOND);
	}

	private void setMockCallTimeDiff(long millisPerCall) {
		for (int i=0; i<5; i++) {
			EasyMock.expect(clock.getMillis()).andReturn((long) i*millisPerCall);
		}
		EasyMock.replay(clock);
	}
	
	public GameClockTest() {
		gameClock = new GameClock();
	}

	@Test
	public void isStoppedOnConstruction() {
		EasyMock.expect(clock.getMillis()).andReturn((long) 1000);
		EasyMock.replay(clock);
		assertEquals(GameClock.MILLIS_PER_HALF, gameClock.getMillisLeft());
	}
	
	@Test
	public void clockCountsDownWhenStarted() {
		setMockCallTimeDiff(20*GameClock.MILLIS_PER_SECOND);
		gameClock.start();
		assertEquals(GameClock.MILLIS_PER_HALF-20000, gameClock.getMillisLeft());
	}

	@Test
	public void stopClock() {
		mockOneSecondPerCall();
		gameClock.start();
		gameClock.stop();
		assertEquals(GameClock.MILLIS_PER_HALF-1000, gameClock.getMillisLeft());
		assertEquals(GameClock.MILLIS_PER_HALF-1000, gameClock.getMillisLeft());
	}
	
	@Test
	public void startAndStopClock() {
		mockOneSecondPerCall();
		gameClock.start();
		gameClock.stop();
		assertEquals(GameClock.MILLIS_PER_HALF-1000, gameClock.getMillisLeft());
		gameClock.start();
		assertEquals(GameClock.MILLIS_PER_HALF-2000, gameClock.getMillisLeft());
		gameClock.stop();
		assertEquals(GameClock.MILLIS_PER_HALF-3000, gameClock.getMillisLeft());
	}

	@Test 
	public void getStringCallsFormatter() {
		mockOneSecondPerCall();
		EasyMock.expect(formatter.formatTime(GameClock.MILLIS_PER_HALF-1000)).andReturn("abcd");
		EasyMock.replay(formatter);
		gameClock.start();
		gameClock.stop();
		assertEquals("abcd", gameClock.getString());
	}
	
	@Test
	public void resetClockAlsoStopsClock() {
		mockOneSecondPerCall();
		gameClock.start();
		gameClock.reset();
		assertEquals(GameClock.MILLIS_PER_HALF-0, gameClock.getMillisLeft());
		assertEquals(GameClock.MILLIS_PER_HALF-0, gameClock.getMillisLeft());
	}
}

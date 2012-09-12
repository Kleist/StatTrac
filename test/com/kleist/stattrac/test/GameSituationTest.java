package com.kleist.stattrac.test;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.kleist.stattrac.GameSituation;
import com.kleist.stattrac.Possession;
import com.kleist.stattrac.WallClockTimer;

public class GameSituationTest {
	@Rule
	public ExpectedException exception = ExpectedException.none();

	private GameSituation gameSituation;
	private WallClockTimer clock;

	@Before
	public void setUp() {
		gameSituation = new GameSituation();
		clock = EasyMock.createMock(WallClockTimer.class);
		gameSituation.setClock(clock);
	}
	
	@Test
	public void posessionIsNotSetByDefault() {
		assertEquals(Possession.NOT_SET, gameSituation.getPossession());
	}
	
	@Test
	public void canSetPossessingTeam() { 
		gameSituation.setPossession(Possession.HOME);
		assertEquals(Possession.HOME, gameSituation.getPossession());
		gameSituation.setPossession(Possession.AWAY);
		assertEquals(Possession.AWAY, gameSituation.getPossession());
	}
	
	@Test 
	public void possessionChangesOnTurnover() throws Exception {
		gameSituation.setPossession(Possession.HOME);
		gameSituation.turnover();
		assertEquals(Possession.AWAY, gameSituation.getPossession());
		gameSituation.turnover();
		assertEquals(Possession.HOME, gameSituation.getPossession());		
	}
	
	@Test
	public void turnoverBeforePossessionIsSetThrows() throws Exception {
		exception.expect(Exception.class);
		exception.expectMessage("Cannot change possession before possession is set!");
		gameSituation.turnover();
	}
	
	@Test
	public void resetClockOnGameStart() {
		clock.resetClock();
		EasyMock.expect(clock.getMilliSecondsSinceReset()).andReturn((long) 0);
		EasyMock.replay(clock);

		gameSituation.startGame();
		assertEquals("00:00.0", gameSituation.getGameClockString());
	}

	@Test
	public void clockDoesntStopOnTurnoverBefore2Min() throws Exception {
		clock.resetClock();
		EasyMock.expect(clock.getMilliSecondsSinceReset()).andReturn((long) 20000);
		EasyMock.replay(clock);

		gameSituation.setPossession(Possession.HOME);
		gameSituation.startGame();
		gameSituation.turnover();
		assertEquals("00:20.0", gameSituation.getGameClockString());
	}

	@Test
	public void clockCountsMinutesAndSeconds() throws Exception {
		clock.resetClock();
		EasyMock.expect(clock.getMilliSecondsSinceReset()).andReturn((long) 61000);
		EasyMock.replay(clock);

		gameSituation.setPossession(Possession.HOME);
		gameSituation.startGame();
		gameSituation.turnover();
		assertEquals("01:01.0", gameSituation.getGameClockString());
	}
}

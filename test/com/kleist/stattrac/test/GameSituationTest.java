package com.kleist.stattrac.test;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static com.kleist.stattrac.test.Assertions.*;

import com.kleist.stattrac.GameClock;
import com.kleist.stattrac.GameSituation;
import com.kleist.stattrac.Possession;

public class GameSituationTest {
	@Rule
	public ExpectedException exception = ExpectedException.none();

	private GameSituation gameSituation;
	private GameClock gameClock;

	@Before
	public void setUp() {
		gameSituation = new GameSituation();
		gameClock = EasyMock.createMock(GameClock.class);
		gameSituation.setGameClock(gameClock);
		gameSituation.setPossession(Possession.HOME);
	}
	
	@Test
	public void posessionIsNotSetByDefault() {
		gameSituation = new GameSituation();
		assertEquals(Possession.NOT_SET, gameSituation.getPossession());
	}
	
	@Test
	public void canSetPossessingTeam() { 
		gameSituation.setPossession(Possession.AWAY);
		assertEquals(Possession.AWAY, gameSituation.getPossession());
		gameSituation.setPossession(Possession.HOME);
		assertEquals(Possession.HOME, gameSituation.getPossession());
	}
	
	@Test 
	public void possessionChangesOnTurnover() throws Exception {
		assertEquals(Possession.HOME, gameSituation.getPossession());		
		gameSituation.turnover();
		assertEquals(Possession.AWAY, gameSituation.getPossession());
		gameSituation.turnover();
		assertEquals(Possession.HOME, gameSituation.getPossession());		
	}
	
	@Test
	public void turnoverBeforePossessionIsSetThrows() throws Exception {
		exception.expect(Exception.class);
		exception.expectMessage("Cannot change possession before possession is set!");
		gameSituation = new GameSituation();
		gameSituation.turnover();
	}
	
	@Test
	public void turnoverWithin2MinTriggersClockStop() throws Exception {
		EasyMock.expect(gameClock.getMillisLeft()).andReturn((long) (1.5*GameClock.MILLIS_PER_MINUTE));
		gameClock.stop();
		EasyMock.replay(gameClock);
		gameSituation.turnover();
	}
	
	@Test
	public void scoreBoardContainsGameClockWhenZero() {
		EasyMock.expect(gameClock.getString()).andReturn("00:00.0");
		EasyMock.replay(gameClock);
		assertTrue(gameSituation.getScoreBoardString().contains("00:00.0"));
	}

	@Test
	public void scoreBoardContainsGameClockWhenNonZero() {
		EasyMock.expect(gameClock.getString()).andReturn("19:10.0");
		EasyMock.replay(gameClock);
		assertStringContains(gameSituation.getScoreBoardString(), "19:10.0");
	}
}

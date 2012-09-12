package com.kleist.stattrac.test;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
	public void scoreBoardContainsGameClock() {
		EasyMock.expect(gameClock.getString()).andReturn("00:00.0");
		EasyMock.replay(gameClock);
		assertTrue(gameSituation.getScoreBoardString().contains("00:00.0"));
	}
}

package com.kleist.stattrac.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.kleist.stattrac.GameSituation;
import com.kleist.stattrac.Possession;

public class GameSituationTest {
	@Rule
	public ExpectedException exception = ExpectedException.none();

	private GameSituation gameSituation;

	@Before
	public void setUp() {
		gameSituation = new GameSituation();
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
}

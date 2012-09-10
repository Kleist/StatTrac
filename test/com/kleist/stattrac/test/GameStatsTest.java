package com.kleist.stattrac.test;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.*;
import org.junit.rules.ExpectedException;

import com.kleist.stattrac.GameStats;
import com.kleist.stattrac.Player;
import com.kleist.stattrac.Possession;
import com.kleist.stattrac.StatEvent;

public class GameStatsTest {
	private GameStats gameStats;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Before
	public void setUp() {
		gameStats = new GameStats();
	}

	@Test
	public void scoreStartsAtZero() {
		assertEquals(0, gameStats.score().home);
		assertEquals(0, gameStats.score().away);
	}
	
	@Test
	public void posessionIsNotSetByDefault() {
		assertEquals(Possession.NOT_SET, gameStats.getPossession());
	}
	
	@Test
	public void canSetPossessingTeam() { 
		gameStats.setPosession(Possession.HOME);
		assertEquals(Possession.HOME, gameStats.getPossession());
		gameStats.setPosession(Possession.AWAY);
		assertEquals(Possession.AWAY, gameStats.getPossession());
	}
	
	@Test 
	public void possessionChangesOnTurnover() throws Exception {
		gameStats.setPosession(Possession.HOME);
		gameStats.turnover();
		assertEquals(Possession.AWAY, gameStats.getPossession());
		gameStats.turnover();
		assertEquals(Possession.HOME, gameStats.getPossession());		
	}
	
	@Test
	public void turnoverBeforePossessionIsSetThrows() throws Exception {
		exception.expect(Exception.class);
		exception.expectMessage("Cannot change possession before possession is set!");
		gameStats.turnover();
	}
	
	@Test
	public void possessionChangesOnExtrapoint() throws Exception {
		gameStats.setPosession(Possession.HOME);
		gameStats.addEvent(StatEvent.EXTRAPOINT, EasyMock.createMock(Player.class), null);
		assertEquals(Possession.AWAY, gameStats.getPossession());
	}

	@Test
	public void possessionDoesntChangeOn1stDown() throws Exception {
		gameStats.setPosession(Possession.AWAY);
		gameStats.addEvent(StatEvent.FIRSTDOWN, EasyMock.createMock(Player.class), null);
		assertEquals(Possession.AWAY, gameStats.getPossession());
	}
	
	@Test
	public void passesAllEventsOnToAttacker() throws Exception {
		gameStats.setPosession(Possession.HOME);
		Player attacker = EasyMock.createMock(Player.class);
		for (StatEvent event : StatEvent.values()) {
			if (!StatEvent.defensiveStat(event)) {
				EasyMock.reset(attacker);
				attacker.addEvent(event);
				EasyMock.replay(attacker);
				gameStats.addEvent(event, attacker, null);
				EasyMock.verify(attacker);
			}
		}
	}
}

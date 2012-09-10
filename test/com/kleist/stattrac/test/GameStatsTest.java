package com.kleist.stattrac.test;

import static org.junit.Assert.assertEquals;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.kleist.stattrac.GameSituation;
import com.kleist.stattrac.GameStats;
import com.kleist.stattrac.Player;
import com.kleist.stattrac.Possession;
import com.kleist.stattrac.StatEvent;

public class GameStatsTest {
	private GameStats gameStats;
	private GameSituation gameSituation;
	
	@Before
	public void setUp() {
		gameStats = new GameStats();
		gameSituation = EasyMock.createMock(GameSituation.class);
		gameStats.setGameSituation(gameSituation);
	}

	@Test
	public void scoreStartsAtZero() {
		assertEquals(0, gameStats.score().home);
		assertEquals(0, gameStats.score().away);
	}
	
	@Test
	public void extrapointCausesTurnover() throws Exception {
		gameStats.addEvent(StatEvent.EXTRAPOINT, EasyMock.createMock(Player.class), null);
	}

	@Test
	public void firstdownDoesntCausesTurnover() throws Exception {
		gameStats.addEvent(StatEvent.FIRSTDOWN, EasyMock.createMock(Player.class), null);
	}

	@Test
	public void passesAllEventsOnToAttacker() throws Exception {
		gameSituation.setPossession(Possession.HOME);
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

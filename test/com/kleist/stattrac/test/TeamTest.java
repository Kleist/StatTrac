package com.kleist.stattrac.test;

import static org.junit.Assert.*;

import java.util.AbstractList;

import org.junit.Test;

import com.kleist.stattrac.Player;
import com.kleist.stattrac.Team;

public class TeamTest {
	@Test
	public void hasGivenName() {
		Team t = new Team("Nyhavn");
		assertEquals("Nyhavn", t.getName());
	}

	@Test
	public void startWithZeroPlayers() {
		Team t = new Team("Nyhavn");
		assertEquals(0, t.getPlayers().size());
	}

	@Test
	public void canAddPlayer() {
		Team t = new Team("Nyhavn");
		Player p = new Player("Kleist", 7);
		t.addPlayer(p);
		assertEquals(1, t.getPlayers().size());
	}

	@Test
	public void canAddPlayers() {
		Team t = new Team("Nyhavn");
		
		Player p1 = new Player("Kleist", 7);
		t.addPlayer(p1);
		Player p2 = new Player("Bugge", 64);
		t.addPlayer(p2);
		
		AbstractList<Player> players = t.getPlayers();
		assertEquals(2, players.size());
		assertTrue(players.contains(p1));		
		assertTrue(players.contains(p2));
	}
}

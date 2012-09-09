package com.kleist.stattrac.test;
import static org.junit.Assert.*;

import org.junit.Test;

import com.kleist.stattrac.Player;

public class PlayerTest {
	@Test public void hasGivenName() {
		Player p = new Player("Kleist", 7);
		assertEquals("Kleist", p.getName());
	}

	@Test public void hasGivenNumber() {
		Player p = new Player("Kleist", 7);
		assertEquals(7, p.getNumber());
	}
}

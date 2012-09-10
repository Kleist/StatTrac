package com.kleist.stattrac.test;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.kleist.stattrac.Player;
import com.kleist.stattrac.StatCounter;
import com.kleist.stattrac.StatEvent;

public class PlayerTest {
	private Player player;
	private StatCounter statCounterMock;
	@Before
	public void setUp() {
		player = new Player("Kleist", 7);
		statCounterMock = EasyMock.createMock(StatCounter.class);
		player.setStatCounter(statCounterMock);	
	}

	@Test
	public void hasGivenName() {
		assertEquals("Kleist", player.getName());
	}

	@Test
	public void hasGivenNumber() {
		assertEquals(7, player.getNumber());
	}

	@Test
	public void hasStatCounter() {
		assertNotNull(player.getStatCounter());
	}
	
	@Test
	public void forwardsEventsToStatCounter() {
		statCounterMock.addEvent(StatEvent.EXTRAPOINT);
		EasyMock.expectLastCall();
		EasyMock.replay(statCounterMock);
		player.addEvent(StatEvent.EXTRAPOINT);
		EasyMock.verify(statCounterMock);
	}
}

package com.kleist.stattrac.test;

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.kleist.stattrac.Player;
import com.kleist.stattrac.StatCounter;
import com.kleist.stattrac.StatEvent;

public class StatCounterTest {
	StatCounter statCounter_;

	@Before
	public void setUp() {
		statCounter_ = new StatCounter(EasyMock.createMock(Player.class));
	}

	@Test
	public void hasPlayerReference() {
		assertNotNull(statCounter_.getPlayer());
	}
	
	@Test
	public void countsIncompletions() {
		assertEquals(0, statCounter_.getEventCount(StatEvent.INCOMPLETE));
		statCounter_.addEvent(StatEvent.INCOMPLETE);
		assertEquals(1, statCounter_.getEventCount(StatEvent.INCOMPLETE));
		statCounter_.addEvent(StatEvent.INCOMPLETE);
		assertEquals(2, statCounter_.getEventCount(StatEvent.INCOMPLETE));
	}

	@Test
	public void countsTouchdowns() {
		assertEquals(0, statCounter_.getEventCount(StatEvent.TOUCHDOWN));
		statCounter_.addEvent(StatEvent.TOUCHDOWN);
		assertEquals(1, statCounter_.getEventCount(StatEvent.TOUCHDOWN));
		statCounter_.addEvent(StatEvent.TOUCHDOWN);
		assertEquals(2, statCounter_.getEventCount(StatEvent.TOUCHDOWN));
	}

	@Test
	public void countsExtrapoints() {
		assertEquals(0, statCounter_.getEventCount(StatEvent.EXTRAPOINT));
		statCounter_.addEvent(StatEvent.EXTRAPOINT);
		assertEquals(1, statCounter_.getEventCount(StatEvent.EXTRAPOINT));
		statCounter_.addEvent(StatEvent.EXTRAPOINT);
		assertEquals(2, statCounter_.getEventCount(StatEvent.EXTRAPOINT));
	}

	@Test
	public void countsTwoPointConversions() {
		assertEquals(0, statCounter_.getEventCount(StatEvent.TWOPOINTCONVERSION));
		statCounter_.addEvent(StatEvent.TWOPOINTCONVERSION);
		assertEquals(1, statCounter_.getEventCount(StatEvent.TWOPOINTCONVERSION));
	}

	@Test
	public void countsSafeties() {
		assertEquals(0, statCounter_.getEventCount(StatEvent.SAFETY));
		statCounter_.addEvent(StatEvent.SAFETY);
		assertEquals(1, statCounter_.getEventCount(StatEvent.SAFETY));
		statCounter_.addEvent(StatEvent.SAFETY);
		assertEquals(2, statCounter_.getEventCount(StatEvent.SAFETY));
	}
}

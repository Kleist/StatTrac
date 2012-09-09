package com.kleist.stattrac.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.kleist.stattrac.StatCounter;
import com.kleist.stattrac.StatEvent;

public class StatCounterTest {
	StatCounter sc_;

	@Before
	public void setUp() {
		sc_ = new StatCounter();
	}

	@Test
	public void countsIncompletions() {
		assertEquals(0, sc_.getEventCount(StatEvent.INCOMPLETE));
		sc_.addEvent(StatEvent.INCOMPLETE);
		assertEquals(1, sc_.getEventCount(StatEvent.INCOMPLETE));
		sc_.addEvent(StatEvent.INCOMPLETE);
		assertEquals(2, sc_.getEventCount(StatEvent.INCOMPLETE));
	}

	@Test
	public void countsTouchdowns() {
		assertEquals(0, sc_.getEventCount(StatEvent.TOUCHDOWN));
		sc_.addEvent(StatEvent.TOUCHDOWN);
		assertEquals(1, sc_.getEventCount(StatEvent.TOUCHDOWN));
		sc_.addEvent(StatEvent.TOUCHDOWN);
		assertEquals(2, sc_.getEventCount(StatEvent.TOUCHDOWN));
	}

	@Test
	public void countsExtrapoints() {
		assertEquals(0, sc_.getEventCount(StatEvent.EXTRAPOINT));
		sc_.addEvent(StatEvent.EXTRAPOINT);
		assertEquals(1, sc_.getEventCount(StatEvent.EXTRAPOINT));
		sc_.addEvent(StatEvent.EXTRAPOINT);
		assertEquals(2, sc_.getEventCount(StatEvent.EXTRAPOINT));
	}

	@Test
	public void countsTwoPointConversions() {
		assertEquals(0, sc_.getEventCount(StatEvent.TWOPOINTCONVERSION));
		sc_.addEvent(StatEvent.TWOPOINTCONVERSION);
		assertEquals(1, sc_.getEventCount(StatEvent.TWOPOINTCONVERSION));
	}

	@Test
	public void countsSafeties() {
		assertEquals(0, sc_.getEventCount(StatEvent.SAFETY));
		sc_.addEvent(StatEvent.SAFETY);
		assertEquals(1, sc_.getEventCount(StatEvent.SAFETY));
		sc_.addEvent(StatEvent.SAFETY);
		assertEquals(2, sc_.getEventCount(StatEvent.SAFETY));
	}
}

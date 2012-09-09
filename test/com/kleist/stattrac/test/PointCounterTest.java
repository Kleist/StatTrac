package com.kleist.stattrac.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.kleist.stattrac.PointCounter;
import com.kleist.stattrac.StatCounter;
import com.kleist.stattrac.StatEvent;


public class PointCounterTest {
	private PointCounter pointCounter;
	private StatCounter statCounter;

	@Before 
	public void setUp() {
		statCounter = new StatCounter();
		pointCounter = new PointCounter(statCounter);
	}
	
	@Test
	public void pointsStartAtZero() {
		assertEquals(0, pointCounter.getPoints());
	}

	@Test
	public void countsTDPoints() {
		statCounter.addEvent(StatEvent.TOUCHDOWN);
		assertEquals(6, pointCounter.getPoints());
	}

	@Test
	public void countsXPPoints() {
		statCounter.addEvent(StatEvent.EXTRAPOINT);
		assertEquals(1, pointCounter.getPoints());
	}

	@Test
	public void counts2PtPoints() {
		statCounter.addEvent(StatEvent.TWOPOINTCONVERSION);
		assertEquals(2, pointCounter.getPoints());
	
	}
	
	@Test
	public void countsSafetyPoints() {
		statCounter.addEvent(StatEvent.SAFETY);
		assertEquals(2, pointCounter.getPoints());
	}
	
	@Test
	public void accumulatesPoints() {
		statCounter.addEvent(StatEvent.TOUCHDOWN);
		statCounter.addEvent(StatEvent.TOUCHDOWN);
		assertEquals(12, pointCounter.getPoints());
	}
}

package com.kleist.stattrac.test;

import static org.junit.Assert.assertTrue;

public class Assertions {
	public static void assertStringContains(String haystack, String needle) {
		boolean result = haystack.contains(needle);		
		assertTrue(String.format("Testing that '%s' contains '%s'", haystack, needle), result);
	}
}

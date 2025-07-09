package com.junit.test;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculateTest {
	
	Calculate ca = new Calculate();
	int sum = ca.sum(2, 8);
	int testSum= 10;
	
	@Test
	public void testSum(){
		System.out.println("@Test sum():" + sum + " = " + testSum);
		assertEquals(sum, testSum);
	}

}

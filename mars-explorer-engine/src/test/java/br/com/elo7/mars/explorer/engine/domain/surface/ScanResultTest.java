/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elo7.mars.explorer.engine.domain.surface;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author p-poliveira
 */
public class ScanResultTest {
	
	public ScanResultTest() {
	}

	@Test
	public void testValues() {
		System.out.println("values");
		ScanResult[] expResult = null;
		ScanResult[] result = ScanResult.values();
		assertArrayEquals(expResult, result);
		fail("The test case is a prototype.");
	}

	@Test
	public void testValueOf() {
		System.out.println("valueOf");
		String name = "";
		ScanResult expResult = null;
		ScanResult result = ScanResult.valueOf(name);
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}

	@Test
	public void testGetMessage() {
		System.out.println("getMessage");
		ScanResult instance = null;
		String expResult = "";
		String result = instance.getMessage();
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}
	
}

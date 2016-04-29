/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elo7.mars.explorer.engine.domain.explorer;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author p-poliveira
 */
public class ExplorerPositionTest {
	
	public ExplorerPositionTest() {
	}

	@Test
	public void testGetxAxis() {
		System.out.println("getxAxis");
		ExplorerPosition instance = null;
		int expResult = 0;
		int result = instance.getxAxis();
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}

	@Test
	public void testGetyAxis() {
		System.out.println("getyAxis");
		ExplorerPosition instance = null;
		int expResult = 0;
		int result = instance.getyAxis();
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}

	@Test
	public void testGetDirection() {
		System.out.println("getDirection");
		ExplorerPosition instance = null;
		Direction expResult = null;
		Direction result = instance.getDirection();
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}
	
}

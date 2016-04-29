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
public class SurfaceFactoryTest {
	
	public SurfaceFactoryTest() {
	}

	@Test
	public void testCreate() {
		System.out.println("create");
		String input = "";
		SurfaceFactory instance = new SurfaceFactory();
		Surface expResult = null;
		Surface result = instance.create(input);
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}
	
}

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
public class ExplorerFactoryTest {
	
	public ExplorerFactoryTest() {
	}

	@Test
	public void testCreate() {
		System.out.println("create");
		String input = "";
		ExplorerFactory instance = new ExplorerFactory();
		Explorer expResult = null;
		Explorer result = instance.create(input);
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}
	
}

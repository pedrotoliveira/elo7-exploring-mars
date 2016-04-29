/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elo7.mars.explorer.engine.application;

import java.util.Collection;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author p-poliveira
 */
public class SurfaceScanEngineTest {
	
	public SurfaceScanEngineTest() {
	}

	@Test
	public void testProcess() {
		System.out.println("process");
		Collection<String> inputs = null;
		SurfaceScanEngine instance = new SurfaceScanEngine();
		Collection<String> expResult = null;
		Collection<String> result = instance.process(inputs);
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}
	
}

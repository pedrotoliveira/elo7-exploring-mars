/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elo7.mars.explorer.engine.domain.surface;

import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import java.util.Collection;
import java.util.UUID;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author p-poliveira
 */
public class PlateuTest {
	
	public PlateuTest() {
	}

	@Test
	public void testGetId() {
		System.out.println("getId");
		Plateu instance = null;
		UUID expResult = null;
		UUID result = instance.getId();
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}

	@Test
	public void testDeployExplorer() {
		System.out.println("deployExplorer");
		Explorer explorer = null;
		Plateu instance = null;
		Explorer expResult = null;
		Explorer result = instance.deployExplorer(explorer);
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}

	@Test
	public void testMoveExplorerTo() {
		System.out.println("moveExplorerTo");
		UUID explorerId = null;
		ExplorerPosition newPosition = null;
		Plateu instance = null;
		Explorer expResult = null;
		Explorer result = instance.moveExplorerTo(explorerId, newPosition);
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}

	@Test
	public void testScan() {
		System.out.println("scan");
		ExplorerPosition position = null;
		Plateu instance = null;
		ScanResult expResult = null;
		ScanResult result = instance.scan(position);
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}

	@Test
	public void testGetDeployedExplorers() {
		System.out.println("getDeployedExplorers");
		Plateu instance = null;
		Collection<Explorer> expResult = null;
		Collection<ExplorerPosition> result = instance.getExplorerPositions();
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}
	
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.elo7.mars.explorer.engine.domain.action;

import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author p-poliveira
 */
public class TurnLeftTest {
	
	public TurnLeftTest() {
	}

	@Test
	public void testExecute() {
		System.out.println("execute");
		ExplorerPosition currentPosition = null;
		TurnLeft instance = new TurnLeft();
		ExplorerPosition expResult = null;
		ExplorerPosition result = instance.execute(currentPosition);
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}
	
}

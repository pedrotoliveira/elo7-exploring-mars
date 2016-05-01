package br.com.elo7.mars.explorer.engine.domain.action;

import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author pedrotoliveira
 */
public class MoveFowardTest {
	
	public MoveFowardTest() {
	}

	@Test
	public void testExecute() {
		System.out.println("execute");
		ExplorerPosition currentPosition = null;
		MoveFoward instance = new MoveFoward();
		ExplorerPosition expResult = null;
		ExplorerPosition result = instance.execute(currentPosition);
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}	
}

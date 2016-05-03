package br.com.elo7.mars.explorer.engine.domain.action;

import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.*;

/**
 *
 * @author pedrotoliveira
 */
@RunWith(value = Parameterized.class)
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

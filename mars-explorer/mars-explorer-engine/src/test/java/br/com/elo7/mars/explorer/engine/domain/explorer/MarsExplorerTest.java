package br.com.elo7.mars.explorer.engine.domain.explorer;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pedrotoliveira
 */
public class MarsExplorerTest {
	
	public MarsExplorerTest() {
	}

	@Test
	public void testGetId() {
		fail("To Implement");
	}

	@Test
	public void testRegisterInstructions() {
		fail("To Implement");
	}

	@Test
	public void testExcuteInstructions() {
		fail("To Implement");
	}

	@Test
	public void testGetCurrentPosition() {
		System.out.println("getCurrentPosition");
		MarsExplorer instance = null;
		ExplorerPosition expResult = null;
		ExplorerPosition result = instance.getCurrentPosition();
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}
	
}

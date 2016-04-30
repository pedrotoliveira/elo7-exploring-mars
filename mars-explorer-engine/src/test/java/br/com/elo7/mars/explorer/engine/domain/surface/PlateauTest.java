package br.com.elo7.mars.explorer.engine.domain.surface;

import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import java.util.UUID;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author pedrotoliveira
 */
public class PlateauTest {

	public PlateauTest() {
	}

	@Test
	public void testGetId() {
		System.out.println("getId");
		Plateau instance = null;
		UUID expResult = null;
		UUID result = instance.getId();
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}

	@Test
	public void testDeployExplorer() {
		System.out.println("deployExplorer");
		Explorer explorer = null;
		Plateau instance = null;
		Explorer expResult = null;
		Explorer result = instance.deployExplorer(explorer);
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}

	@Test
	public void testScan() {
		System.out.println("scan");
		ExplorerPosition position = null;
		Plateau instance = null;
		ScanResult expResult = null;
		ScanResult result = instance.scan(position);
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}
}

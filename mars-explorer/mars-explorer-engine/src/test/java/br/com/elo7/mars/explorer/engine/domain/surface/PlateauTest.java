package br.com.elo7.mars.explorer.engine.domain.surface;

import static org.hamcrest.CoreMatchers.equalTo;

import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import java.util.UUID;
import org.junit.Test;

import static org.junit.Assert.*;

import br.com.elo7.mars.explorer.engine.test.FixtureTest;

/**
 * Plateau Unit Tests
 *
 * @author pedrotoliveira
 */
public class PlateauTest extends FixtureTest {

	@Test
	public void testGetId() {
        UUID id = UUID.randomUUID();
		assertThat(new Plateau(id, 10, 10).getId(), equalTo(id));
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
		SurfaceScanResult expResult = null;
		SurfaceScanResult result = instance.scan(position);
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}
}

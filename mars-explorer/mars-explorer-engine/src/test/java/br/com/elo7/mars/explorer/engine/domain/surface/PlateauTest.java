package br.com.elo7.mars.explorer.engine.domain.surface;

import br.com.elo7.mars.explorer.engine.domain.explorer.Direction;
import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import br.com.elo7.mars.explorer.engine.test.FixtureTest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.Test;

import static java.util.UUID.randomUUID;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Plateau Unit Tests
 *
 * @author pedrotoliveira
 */
public class PlateauTest extends FixtureTest {

	@Test
	public void testGetId() {
		UUID id = randomUUID();
		assertThat(new Plateau(id, 10, 10).getId(), equalTo(id));
	}

	private Plateau validPlateau() {
		return new Plateau(randomUUID(), 100, 100);
	}
	
	@Test
	public void testScanOutOfBoundary() {
		Plateau plateau = validPlateau();
		ExplorerPosition explorerPosition = new ExplorerPosition(randomInt(101, 1000), randomInt(101, 1000), Direction.NORTH);	
		assertThat(plateau.scan(explorerPosition), equalTo(SurfaceScanResult.OUT_OF_BOUNDARY));
	}
	
	@Test
	public void testScanCollistion() {
		Plateau plateau = validPlateau();		
		ExplorerPosition explorerPosition = new ExplorerPosition(randomInt(10, 50), randomInt(10, 50), Direction.SOUTH);
		
		Explorer explorer = mock(Explorer.class);		
		when(explorer.getCurrentPosition()).thenReturn(explorerPosition);
		
				
		List<Explorer> deployedExplorers = new ArrayList<>();
		deployedExplorers.add(explorer);		
		plateau.setDeployedExplorers(deployedExplorers);
		
		assertThat(plateau.scan(explorerPosition), equalTo(SurfaceScanResult.COLLISION));
	}
	
	@Test
	public void testScanOK() {
		Plateau plateau = validPlateau();		
		ExplorerPosition explorerPosition = new ExplorerPosition(1, 1, Direction.NORTH);	
		assertThat(plateau.scan(explorerPosition), equalTo(SurfaceScanResult.OK));
	}

	@Test
	public void testDeployOneExplorer() {
		Plateau plateau = validPlateau();
		Explorer explorer = mock(Explorer.class);
		ExplorerPosition explorerPosition = new ExplorerPosition(randomInt(10, 50), randomInt(10, 50), Direction.SOUTH);
		when(explorer.getCurrentPosition()).thenReturn(explorerPosition);
		assertThat(plateau.deployExplorer(explorer), equalTo(explorer));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDeployTwoExplorersSamePosition() {
		Plateau plateau = validPlateau();
		ExplorerPosition explorerPosition = new ExplorerPosition(randomInt(10, 50), randomInt(10, 50), Direction.SOUTH);
		
		Explorer explorer1 = mock(Explorer.class);
		Explorer explorer2 = mock(Explorer.class);
		
		when(explorer1.getCurrentPosition()).thenReturn(explorerPosition);
		when(explorer2.getCurrentPosition()).thenReturn(explorerPosition);
		try {
			plateau.deployExplorer(explorer1);
			plateau.deployExplorer(explorer2);
		} catch (Exception ex) {
			assertThat(ex.getMessage(), equalTo("Cannot deploy " + explorer2 + " cause: " + SurfaceScanResult.COLLISION.getMessage()));
			throw ex;
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDeployAlredyDeployedExplorer() {
		Plateau plateau = validPlateau();
		UUID id = randomUUID();
		
		Explorer explorer1 = mock(Explorer.class);
		Explorer explorer2 = mock(Explorer.class);
		
		when(explorer1.getCurrentPosition()).thenReturn(new ExplorerPosition(10, 10, Direction.NORTH));
		when(explorer2.getCurrentPosition()).thenReturn(new ExplorerPosition(10, 10, Direction.EAST));
		
		when(explorer1.getId()).thenReturn(id);
		when(explorer2.getId()).thenReturn(id);
	
		try {
			plateau.deployExplorer(explorer1);
			plateau.deployExplorer(explorer2);
		} catch (Exception ex) {
			assertThat(ex.getMessage(), equalTo("Cannot deploy " + explorer2 + " cause: " + Plateau.ALREDY_DEPLOYED));
			throw ex;
		}
	}
}

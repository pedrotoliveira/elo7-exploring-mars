package br.com.elo7.mars.explorer.engine.domain.surface;

import br.com.elo7.mars.explorer.engine.domain.explorer.Direction;
import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import br.com.elo7.mars.explorer.engine.test.FixtureTest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static java.util.UUID.randomUUID;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Plateau Unit Tests
 *
 * @author pedrotoliveira
 */
@RunWith(value = MockitoJUnitRunner.class)
public class PlateauTest extends FixtureTest {

	@Mock
	private Explorer explorer;
	
	@Test
	public void testGetId() {
		String id = randomUUID().toString();
		assertThat(new Plateau(id, 10, 10).getId(), equalTo(id));
	}

	private Plateau validPlateau() {
		return new Plateau(randomUUID().toString(), 100, 100);
	}
	
	@Test
	public void testScanOutOfBoundary() {
		Plateau plateau = validPlateau();
		ExplorerPosition explorerPosition = new ExplorerPosition(randomInt(101, 1000), randomInt(101, 1000), Direction.NORTH);
		when(explorer.getCurrentPosition()).thenReturn(explorerPosition);
		assertThat(plateau.scan(explorer, explorerPosition), equalTo(SurfaceScanResult.OUT_OF_BOUNDARY));
	}
	
	@Test
	public void testScanCollistion() {
		Plateau plateau = validPlateau();
		
		ExplorerPosition explorerPosition = new ExplorerPosition(randomInt(10, 50), randomInt(10, 50), Direction.SOUTH);	
		
		when(explorer.getId()).thenReturn(UUID.randomUUID().toString());
		when(explorer.getCurrentPosition()).thenReturn(explorerPosition);
		
		Explorer other = mock(Explorer.class);
		when(other.getId()).thenReturn(UUID.randomUUID().toString());
		when(other.getCurrentPosition()).thenReturn(explorerPosition);	
				
		List<Explorer> deployedExplorers = new ArrayList<>();
		deployedExplorers.add(other);
		plateau.setDeployedExplorers(deployedExplorers);
		
		assertThat(plateau.scan(explorer, explorerPosition), equalTo(SurfaceScanResult.COLLISION));
	}
	
	@Test
	public void testScanOK() {
		Plateau plateau = validPlateau();		
		ExplorerPosition explorerPosition = new ExplorerPosition(1, 1, Direction.NORTH);
		when(explorer.getCurrentPosition()).thenReturn(explorerPosition);
		assertThat(plateau.scan(explorer, explorerPosition), equalTo(SurfaceScanResult.OK));
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
		ExplorerPosition position = new ExplorerPosition(randomInt(10, 50), randomInt(10, 50), Direction.SOUTH);
		
		Explorer explorer1 = mock(Explorer.class);
		Explorer explorer2 = mock(Explorer.class);
		
		when(explorer1.getId()).thenReturn(randomUUID().toString());
		when(explorer2.getId()).thenReturn(randomUUID().toString());
		
		when(explorer1.getCurrentPosition()).thenReturn(position);
		when(explorer2.getCurrentPosition()).thenReturn(position);
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
		
		when(explorer1.getId()).thenReturn(id.toString());
		when(explorer2.getId()).thenReturn(id.toString());
	
		try {
			plateau.deployExplorer(explorer1);
			plateau.deployExplorer(explorer2);
		} catch (Exception ex) {
			assertThat(ex.getMessage(), equalTo("Cannot deploy " + explorer2 + " cause: " + Plateau.ALREDY_DEPLOYED));
			throw ex;
		}
	}
}

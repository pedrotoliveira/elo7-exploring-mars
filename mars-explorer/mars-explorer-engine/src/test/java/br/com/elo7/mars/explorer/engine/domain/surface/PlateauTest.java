package br.com.elo7.mars.explorer.engine.domain.surface;

import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.test.FixtureTest;
import java.util.UUID;
import org.junit.Test;

import static java.util.UUID.randomUUID;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

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
		return new Plateau(randomUUID(), randomInt(0, 100), randomInt(0, 100));
	}
	
	@Test
	public void testScan() {
		fail("To Implement");
	}

	@Test
	public void testDeployExplorer() {
		Plateau plateau = validPlateau();
		Explorer explorer = mock(Explorer.class);
		assertThat(plateau.deployExplorer(explorer), equalTo(explorer));
	}
}

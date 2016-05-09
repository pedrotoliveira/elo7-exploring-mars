package br.com.elo7.mars.explorer.engine.domain.surface;

import br.com.elo7.mars.explorer.engine.application.MarsExplorerEngineApplication;
import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.test.FixtureTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.util.UUID.randomUUID;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * SurfaceRepository Tests
 *
 * @author pedrotoliveira
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(MarsExplorerEngineApplication.class)
public class SurfaceRepositoryTest extends FixtureTest {

	@Autowired
	private SurfaceRepository surfaceRepository;

	@Test
	public void testCRUD() {
		Plateau plateau = validPlateau();
		Surface surface = surfaceRepository.save(plateau);
		Surface surfaceFinded = surfaceRepository.findOne(plateau.getMongoId());
		assertThat(surface, equalTo(surfaceFinded));
		
		plateau.setxAxis(randomInt(100, 200));
		surface = surfaceRepository.save(plateau);
		surfaceFinded = surfaceRepository.findOne(plateau.getMongoId());
		assertThat(surface, equalTo(surfaceFinded));
		
		surfaceRepository.delete(plateau.getMongoId());
	}

	private Plateau validPlateau() {
		return new Plateau(randomUUID(), 100, 100);
	}
}

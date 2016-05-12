package br.com.elo7.mars.explorer.engine.bootstrap;

import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import br.com.elo7.mars.explorer.engine.domain.surface.SurfaceRepository;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

/**
 * MarsExplorerEngineApplication Tests
 *
 * @author pedrotoliveira
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(MarsExplorerEngineApplication.class)
public class MarsExplorerEngineApplicationTest {

	@Autowired
	MarsExplorerEngineApplication application;

	@Autowired
	SurfaceRepository repository;

	@Test
	public void testRun() throws Exception {
		application.run(
				"5 5",
				"1 2 N", "LMLMLMLMM",
				"3 3 E", "MMRMMRMRRM");

		List<Surface> surfaces = repository.findAll();
		assertNotNull(surfaces);
		
		System.out.println("==========================");		
		application.run(
				"10 10",
				"1 1 N", "LMLMLMLMM",
				"5 2 E", "MMRMMRMRRMMMMRRMM",
				"6 7 S", "MMMMMMMMLMMMM",
				"4 4 W", "LLMMLMMRM");

		surfaces = repository.findAll();
		assertNotNull(surfaces);
	}
}

package br.com.elo7.mars.explorer.engine.bootstrap;

import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import br.com.elo7.mars.explorer.engine.domain.surface.SurfaceRepository;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


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
		application.run("5 5", "1 2 N", "LMLMLMLMM", "3 3 E", "MMRMMRMRRM", "2 2 S", "MMMMMMMMLMMMMMMMRMMMMMMM");
		List<Surface> surfaces = repository.findAll();
		surfaces.forEach(System.out :: println);
	}
}

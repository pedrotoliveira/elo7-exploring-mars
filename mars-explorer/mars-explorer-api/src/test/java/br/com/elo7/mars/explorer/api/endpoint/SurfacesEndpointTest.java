package br.com.elo7.mars.explorer.api.endpoint;

import br.com.elo7.mars.explorer.api.bootstrap.MarsExplorerApiApplication;
import br.com.elo7.mars.explorer.engine.application.SurfaceScanEngine;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import br.com.elo7.mars.explorer.engine.test.FixtureTest;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import br.com.elo7.mars.explorer.api.service.SurfaceService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Surfaces Endpoint Tests
 *
 * @author pedrotoliveira
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MarsExplorerApiApplication.class)
@WebAppConfiguration
public class SurfacesEndpointTest extends FixtureTest {

    @Autowired
    private SurfaceService surfaceService;
	@Autowired
	private SurfaceScanEngine scanEngine;

	private final List<Surface> surfaces = new ArrayList<>();
	private SurfacesEndpoint endpoint;

	@Before
	public void setUp() {
		this.endpoint = new SurfacesEndpoint();
		this.endpoint.setSurfaceService(surfaceService);

		for (int i = 0; i < 50; i++) {
			Surface s = scanEngine.createSurface(String.format("%d %d", randomInt(0, 100), randomInt(0, 100)));
			surfaces.add(s);
		}
	}

	@After
	public void tearDown() {
		surfaces.forEach((surface) -> surfaceService.delete(surface.getId()));
	}

	@Test
	public void testGet() {
		for (int i = 0; i < 5; i++) {
			Response response = endpoint.get(10, i);
			System.out.println(response);
			assertEquals(200,  response.getStatus());
		}
	}
}

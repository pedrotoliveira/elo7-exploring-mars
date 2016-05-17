package br.com.elo7.mars.explorer.api.endpoint;

import br.com.elo7.mars.explorer.api.bootstrap.MarsExplorerApiApplication;
import br.com.elo7.mars.explorer.api.resource.ExplorerResource;
import br.com.elo7.mars.explorer.api.resource.adapter.ExplorerAdapter;
import br.com.elo7.mars.explorer.api.service.SurfaceService;
import br.com.elo7.mars.explorer.engine.application.SurfaceScanEngine;
import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import br.com.elo7.mars.explorer.engine.test.FixtureTest;
import java.util.ArrayList;
import java.util.Collection;
import javax.ws.rs.core.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.hateoas.Resource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;

/**
 * Explorer Endpoint Tests
 *
 * @author pedrotoliveira
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MarsExplorerApiApplication.class)
@WebAppConfiguration
public class ExplorerEndpointTest extends FixtureTest {

	@Autowired
	private SurfaceService surfaceService;
	@Autowired
	private SurfaceScanEngine scanEngine;
	@Autowired
	private ExplorerAdapter explorerAdapter;

	private Surface surface;
	private ExplorerEndpoint endpoint;

	@Before
	public void setUp() {
		this.endpoint = new ExplorerEndpoint();
		this.surfaceService = Mockito.spy(surfaceService);
		this.endpoint.setSurfaceService(surfaceService);

		this.surface = scanEngine.createSurface(validSurfaceInput());
		this.surface = scanEngine.deployExplorers(surface.getId(), validExplorersInputs());
		this.surface = scanEngine.scan(surface.getId());

	}

	@After
	public void tearDown() {
		scanEngine.deleteSurface(surface.getId());
	}

	@Test
	public void testGet() {
		boolean expand = true;
		Explorer deployed = surface.getDeployedExplorers().iterator().next();
		Resource<ExplorerResource> expected = explorerAdapter.adapt(deployed, expand);
		Response response = endpoint.get(deployed.getId());
		assertThat(response.getStatus(), equalTo(200));
		assertThat(response.getEntity(), equalTo(expected));
		verify(surfaceService).findExplorer(deployed.getId());
	}

	private String validSurfaceInput() {
		return String.format("%d %d", 100, 100);
	}

	private Collection<String> validExplorersInputs() {
		Collection<String> explorersInput = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			explorersInput.add(String.format("%d %d %s", (i * 5), (i * 9), regexValue("[NEWS]")));
			explorersInput.add(regexValue("[MLR]{25}"));
		}
		return explorersInput;
	}
}

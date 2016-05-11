package br.com.elo7.mars.explorer.api.resource.adapter;

import br.com.elo7.mars.explorer.api.bootstrap.MarsExplorerApiApplication;
import br.com.elo7.mars.explorer.api.resource.Dimension;
import br.com.elo7.mars.explorer.api.resource.SurfaceResource;
import br.com.elo7.mars.explorer.engine.application.SurfaceScanEngine;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import br.com.elo7.mars.explorer.engine.test.FixtureTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * Surface Adapter Test
 *
 * @author pedrotoliveira
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MarsExplorerApiApplication.class)
@WebAppConfiguration
public class SurfaceAdapterTest extends FixtureTest {

	@Autowired
	private SurfaceScanEngine scanEngine;
	@Autowired
	private ExplorerAdapter explorerAdapter;

	private Surface surface;
	private SurfaceAdapter adapter;

	@Before
	public void setUp() {
		this.adapter = new SurfaceAdapter();
		this.adapter.setExplorerAdapter(explorerAdapter);
		this.surface = scanEngine.createSurface(validSurfaceInput());
	}

	@Test
	public void testAdaptExpandedResource() {
		SurfaceResource surfaceResource = new SurfaceResource();
		surfaceResource.id(surface.getId());
		surfaceResource.createdDate(surface.getCreatedDate());
		surfaceResource.dimension(new Dimension(surface.getxAxis(), surface.getyAxis()));
		surfaceResource.deployedExplorers(explorerAdapter.map(surface.getDeployedExplorers()));
		Link selfLink = JaxRsLinkBuilder
				.linkTo(surfaceResource.getEndpointClass())
				.slash(surfaceResource.getId())
				.withSelfRel();

		Resource<SurfaceResource> expected = new Resource<>(surfaceResource, selfLink);

		assertThat(adapter.adaptExpandedResource(surface), equalTo(expected));
	}

	@Test
	public void testAdaptResource() {
		fail("The test case is a prototype.");
	}

	@Test
	public void testAdaptAll() {
		fail("The test case is a prototype.");
	}

	@Test
	public void testSetExplorerAdapter() {
		fail("The test case is a prototype.");
	}

	private String validSurfaceInput() {
		return String.format("%d %d", randomInt(0, 100), randomInt(0, 100));
	}
}

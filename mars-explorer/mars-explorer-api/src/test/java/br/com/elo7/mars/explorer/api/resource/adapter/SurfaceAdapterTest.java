package br.com.elo7.mars.explorer.api.resource.adapter;

import br.com.elo7.mars.explorer.api.bootstrap.MarsExplorerApiApplication;
import br.com.elo7.mars.explorer.api.resource.Dimension;
import br.com.elo7.mars.explorer.api.resource.SurfaceResource;
import br.com.elo7.mars.explorer.engine.application.SurfaceScanEngine;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import br.com.elo7.mars.explorer.engine.test.FixtureTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

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
		this.surface = scanEngine.deployExplorers(surface.getId(), validExplorersInputs());
		scanEngine.scan(surface.getId());
	}

	@After
	public void tearDown() {
		scanEngine.deleteSurface(surface.getId());
	}

	@Test
	public void testAdaptExpandedResource() {
		SurfaceResource surfaceResource = map(surface);
		surfaceResource.deployedExplorers(explorerAdapter.adaptAll(surface.getDeployedExplorers()));
		Link selfLink = JaxRsLinkBuilder
				.linkTo(surfaceResource.getEndpointClass())
				.slash(surfaceResource.getId())
				.withRel(surfaceResource.getRel());

		Resource<SurfaceResource> expected = surfaceResource.buildResource(selfLink);
		assertThat(adapter.adaptExpandedResource(surface), equalTo(expected));
	}

	@Test
	public void testAdaptResource() {
		SurfaceResource surfaceResource = map(surface);
		Link selfLink = JaxRsLinkBuilder
				.linkTo(surfaceResource.getEndpointClass())
				.slash(surfaceResource.getId())
				.withRel(surfaceResource.getRel());

		Resource<SurfaceResource> expected = surfaceResource.buildResource(selfLink);
		List<Link> explorersLinks = explorerAdapter.adaptAll(surface.getDeployedExplorers()).getLinks();
		expected.add(explorersLinks);
		assertThat(adapter.adaptResource(surface), equalTo(expected));
	}

	@Test
	public void testAdaptAll() {
		List<SurfaceResource> surfaceResources = new ArrayList<>();
		List<Link> links = new ArrayList<>();
		Collection<Surface> surfaces = new ArrayList<>();
		surfaces.add(surface);

		surfaces.stream().forEach((item) -> {
			SurfaceResource surfaceResource = map(item);
			Link link = JaxRsLinkBuilder
					.linkTo(surfaceResource.getEndpointClass())
					.slash(surfaceResource.getId())
					.withRel(surfaceResource.getRel());

			surfaceResource.deployedExplorers(explorerAdapter.adaptAll(item.getDeployedExplorers()));
			surfaceResources.add(surfaceResource);
			links.add(link);
		});
		
		Resources<SurfaceResource> expected = new Resources<>(surfaceResources, links);
		assertThat(adapter.adaptAll(surfaces), equalTo(expected));
	}

	private SurfaceResource map(Surface surface) {
		return new SurfaceResource()
				.id(surface.getId())
				.dimension(new Dimension(surface.getxAxis(), surface.getyAxis()))
				.createdDate(surface.getCreatedDate());
	}

	private String validSurfaceInput() {
		return String.format("%d %d", 100, 100);
	}

	private Collection<String> validExplorersInputs() {
		Collection<String> explorersInput = new ArrayList<>();
		explorersInput.add(String.format("%d %d %s", 50, 50, regexValue("[NEWS]")));
		explorersInput.add(regexValue("[MLR]{25}"));
		return explorersInput;
	}
}

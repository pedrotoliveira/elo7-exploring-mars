package br.com.elo7.mars.explorer.api.resource.adapter;

import br.com.elo7.mars.explorer.api.bootstrap.MarsExplorerApiApplication;
import br.com.elo7.mars.explorer.api.resource.ExecutionResultResource;
import br.com.elo7.mars.explorer.api.resource.ExplorerResource;
import br.com.elo7.mars.explorer.engine.application.SurfaceScanEngine;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExecutionResult;
import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
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
 * ExplorerAdapter Unit Tests
 *
 * @author pedrotoliveira
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MarsExplorerApiApplication.class)
@WebAppConfiguration
public class ExplorerAdapterTest extends FixtureTest {

	@Autowired
	private SurfaceScanEngine scanEngine;

	private Surface surface;
	private ExplorerAdapter adapter;

	@Before
	public void setUp() {
		this.adapter = new ExplorerAdapter();
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
		Explorer explorer = surface.getDeployedExplorers().iterator().next();
		ExplorerResource explorerResource = map(explorer);
		List<Link> links = buildLinks(explorerResource);
		Resource<ExplorerResource> expected = explorerResource.buildResource(links);
		assertThat(adapter.adaptExpandedResource(explorer), equalTo(expected));
	}

	@Test
	public void testAdaptAll() {
		Collection<Explorer> explorers = surface.getDeployedExplorers();
		List<Link> links = new ArrayList<>();
		List<ExplorerResource> explorerResources = mapCollection(explorers, links);		
		Resources<ExplorerResource> expected = new Resources<>(explorerResources, links);		
		assertThat(adapter.adaptAll(explorers), equalTo(expected));
	}

	private String validSurfaceInput() {
		return String.format("%d %d", 100, 100);
	}

	private Collection<String> validExplorersInputs() {
		Collection<String> explorersInput = new ArrayList<>();
		for (int i = 1; i <= 10; i++) {
			explorersInput.add(String.format("%d %d %s", (i * 3), (i * 7), regexValue("[NEWS]")));
			explorersInput.add(regexValue("[MLR]{25}"));
		}
		return explorersInput;
	}

	private ExplorerResource map(Explorer explorer) {
		return new ExplorerResource()
				.id(explorer.getId())
				.currentPosition(explorer.getCurrentPosition())
				.instructions(explorer.getRegisteredInstructions())
				.executionResults(mapExecutions(explorer.getExecutionResults()));
	}

	private List<ExplorerResource> mapCollection(Collection<Explorer> deployedExplorers, List<Link> links) {
		List<ExplorerResource> explorerResources = new ArrayList<>();
		deployedExplorers.stream().forEach((Explorer explorer) -> {
			ExplorerResource resource = map(explorer);
			links.addAll(buildLinks(resource));
		});
		return explorerResources;
	}
	
	private List<Link> buildLinks(ExplorerResource explorerResource) {
		List<Link> links = new ArrayList<>();
		explorerResource.getRels().forEach((rel) -> {
			Link link = JaxRsLinkBuilder
					.linkTo(explorerResource.getEndpointClass())
					.slash(explorerResource.getId())
					.withRel(rel);
			links.add(link);
		});
		return links;
	}

	private List<ExecutionResultResource> mapExecutions(Collection<ExecutionResult> executionResults) {
		List<ExecutionResultResource> executionResultResources = new ArrayList<>();
		executionResults.stream().forEach((ExecutionResult result) -> {
			ExecutionResultResource executionResultResource = new ExecutionResultResource()
					.startPosition(result.getStartPosition())
					.finalPosition(result.getFinalPosition())
					.status(result.getStatus())
					.instruction(result.getInstructionRepresentation())
					.notifications(result.getNotifications());

			executionResultResources.add(executionResultResource);
		});
		return executionResultResources;
	}
}

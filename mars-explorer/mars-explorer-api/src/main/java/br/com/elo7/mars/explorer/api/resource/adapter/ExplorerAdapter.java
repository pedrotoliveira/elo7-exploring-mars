package br.com.elo7.mars.explorer.api.resource.adapter;

import br.com.elo7.mars.explorer.api.resource.ExecutionResultResource;
import br.com.elo7.mars.explorer.api.resource.ExplorerResource;
import br.com.elo7.mars.explorer.api.resource.SurfaceResource;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExecutionResult;
import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.stereotype.Component;

/**
 * Explorer Adapter
 *
 * @author pedrotoliveira
 */
@Component
public class ExplorerAdapter implements ResourceAdapter<Explorer, ExplorerResource> {

	@Override
	public Resource<ExplorerResource> adaptExpandedResource(Explorer domain) {
		return adaptResource(domain);
	}

	@Override
	public Resource<ExplorerResource> adaptResource(Explorer domain) {
		ExplorerResource explorerResource = map(domain);
		List<Link> links = buildLinks(explorerResource);
		return explorerResource.buildResource(links);
	}

	@Override
	public Resources<ExplorerResource> adaptAll(Collection<Explorer> domainCollection) {
		List<Link> links = new ArrayList<>();
		List<ExplorerResource> explorerResources = mapCollection(domainCollection, links);
		return new Resources<>(explorerResources, links);
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

	private ExplorerResource map(Explorer explorer) {
		return new ExplorerResource()
				.id(explorer.getId())				
				.currentPosition(explorer.getCurrentPosition())
				.instructions(explorer.getRegisteredInstructions())
				.executionResults(mapExecutions(explorer.getExecutionResults()));
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

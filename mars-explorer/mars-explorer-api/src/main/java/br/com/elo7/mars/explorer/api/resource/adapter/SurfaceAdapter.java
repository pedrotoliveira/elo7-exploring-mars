package br.com.elo7.mars.explorer.api.resource.adapter;

import br.com.elo7.mars.explorer.api.resource.Dimension;
import br.com.elo7.mars.explorer.api.resource.SurfaceResource;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.jaxrs.JaxRsLinkBuilder;
import org.springframework.stereotype.Component;

/**
 * Surface Adapter Resource
 *
 * @author pedrotoliveira
 */
@Component
public class SurfaceAdapter implements ResourceAdapter<Surface, SurfaceResource> {

	@Autowired
	private ExplorerAdapter explorerAdapter;

	@Override
	public Resource<SurfaceResource> adaptExpandedResource(Surface domain) {
		SurfaceResource surfaceResource = map(domain);
		surfaceResource.deployedExplorers(explorerAdapter.adaptAll(domain.getDeployedExplorers()));
		List<Link> links = buildLinks(surfaceResource);
		return surfaceResource.buildResource(links);
	}

	@Override
	public Resource<SurfaceResource> adaptResource(Surface domain) {
		SurfaceResource surfaceResource = map(domain);
		List<Link> links = buildLinks(surfaceResource);
		Resource<SurfaceResource> resource = surfaceResource.buildResource(links);
		List<Link> explorersLinks = explorerAdapter.adaptAll(domain.getDeployedExplorers()).getLinks();
		resource.add(explorersLinks);
		return resource;
	}

	@Override
	public Resources<SurfaceResource> adaptAll(Collection<Surface> domainCollection) {
		List<SurfaceResource> surfaceResources = new ArrayList<>();
		List<Link> links = new ArrayList<>();
		domainCollection.stream().forEach((domain) -> {
			SurfaceResource surfaceResource = map(domain);
			links.addAll(buildLinks(surfaceResource));

			surfaceResource.deployedExplorers(explorerAdapter.adaptAll(domain.getDeployedExplorers()));
			surfaceResources.add(surfaceResource);
		});
		return new Resources<>(surfaceResources, links);
	}

	private List<Link> buildLinks(SurfaceResource surfaceResource) {
		List<Link> links = new ArrayList<>();
		surfaceResource.getRels().forEach((rel) -> {
			Link link = JaxRsLinkBuilder
					.linkTo(surfaceResource.getEndpointClass())
					.slash(surfaceResource.getId())
					.withRel(rel);
			links.add(link);
		});
		return links;
	}

	private SurfaceResource map(Surface domain) {
		return new SurfaceResource()
				.id(domain.getId())
				.dimension(new Dimension(domain.getxAxis(), domain.getyAxis()))
				.createdDate(domain.getCreatedDate())
				.explorersPositions(domain.getExplorersPosition());
	}

	public void setExplorerAdapter(ExplorerAdapter explorerAdapter) {
		this.explorerAdapter = explorerAdapter;
	}
}

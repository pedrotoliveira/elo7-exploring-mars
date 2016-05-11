package br.com.elo7.mars.explorer.api.resource.adapter;

import br.com.elo7.mars.explorer.api.resource.Dimension;
import br.com.elo7.mars.explorer.api.resource.ExplorerResource;
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
 * Surface Adapter
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
        surfaceResource.deployedExplorers(explorerAdapter.map(domain.getDeployedExplorers()));
        Link selfLink = JaxRsLinkBuilder
                .linkTo(surfaceResource.getEndpointClass())
                .slash(surfaceResource.getId())
                .withSelfRel();

        return new Resource<>(surfaceResource, selfLink);
    }

    @Override
    public Resource<SurfaceResource> adaptResource(Surface domain) {
        SurfaceResource surfaceResource = map(domain);
        Link selfLink = JaxRsLinkBuilder
                .linkTo(surfaceResource.getEndpointClass())
                .slash(surfaceResource.getId())
                .withSelfRel();

        Resource<SurfaceResource> resource = new Resource<>(surfaceResource, selfLink);
        Resources<ExplorerResource> explorerResources = explorerAdapter.adaptAll(domain.getDeployedExplorers());
        resource.add(explorerResources.getLinks());
        return resource;
    }

    @Override
    public Resources<SurfaceResource> adaptAll(Collection<Surface> domainCollection) {
        List<SurfaceResource> surfaceResources = new ArrayList<>();
        List<Link> links = new ArrayList<>();
        domainCollection.stream().forEach((domain) -> {
            SurfaceResource surfaceResource = map(domain);
            Link link = JaxRsLinkBuilder
                    .linkTo(surfaceResource.getEndpointClass())
                    .slash(surfaceResource.getId())
                    .withRel(surfaceResource.getRel());
						
			surfaceResource.deployedExplorers(explorerAdapter.map(domain.getDeployedExplorers()));
            surfaceResources.add(surfaceResource);
            links.add(link);
        });
        return new Resources<>(surfaceResources, links);
    }

    private SurfaceResource map(Surface domain) {
        return new SurfaceResource()
                .id(domain.getId())
                .dimension(new Dimension(domain.getxAxis(), domain.getyAxis()))
                .createdDate(domain.getCreatedDate());
    }

    public void setExplorerAdapter(ExplorerAdapter explorerAdapter) {
        this.explorerAdapter = explorerAdapter;
    }
}

package br.com.elo7.mars.explorer.api.resource.adapter;

import br.com.elo7.mars.explorer.api.resource.Dimension;
import br.com.elo7.mars.explorer.api.resource.SurfaceResource;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import org.springframework.hateoas.Resources;

/**
 * Surface Adapter
 *
 * @author pedrotoliveira
 */
public class SurfaceAdapter implements ResourceAdapter<Surface, SurfaceResource> {

	@Override
	public Resources<SurfaceResource> adapt(Surface domain, boolean expand) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	private SurfaceResource adaptExpandedResource(Surface domain) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	private SurfaceResource adaptResource(Surface domain) {
		SurfaceResource resource = new SurfaceResource()
				.id(domain.getId())
				.dimension(new Dimension(domain.getxAxis(), domain.getyAxis()))
				.createdDate(domain.getCreatedDate());

		throw new UnsupportedOperationException("Not supported yet.");
	}
}

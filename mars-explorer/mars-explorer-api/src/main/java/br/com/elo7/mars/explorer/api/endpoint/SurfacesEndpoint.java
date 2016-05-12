package br.com.elo7.mars.explorer.api.endpoint;

import br.com.elo7.mars.explorer.api.resource.SurfaceResource;
import br.com.elo7.mars.explorer.api.resource.adapter.ResourceAdapter;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import br.com.elo7.mars.explorer.engine.domain.surface.SurfaceRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

/**
 * The Surfaces Collections Endpoint
 *
 * @author pedrotoliveira
 */
@Component
@Path("surfaces")
@Api(value = "Surfaces Collections", description = "The Surfaces Collections", produces = "application/json")
public class SurfacesEndpoint {

	@Autowired
	private SurfaceRepository surfaceRepository;
	@Autowired
	private ResourceAdapter<Surface, SurfaceResource> adapter;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Retrieve Created Surfaces", notes = "Retrieve Created Surfaces", nickname = "get")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK", response = SurfaceResource.class, responseContainer = "List"),
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 500, message = "Failure")
	})
	public Response get(
			@ApiParam(value = "Max Results Per Page") @QueryParam("max") @DefaultValue("20") Integer max,
			@ApiParam(value = "Page") @QueryParam("page") @DefaultValue("0") Integer page) {

		PageRequest pageRequest = new PageRequest(page, max);
		Page<Surface> surfaceCollection = surfaceRepository.findAll(pageRequest);

		if (surfaceCollection.hasContent()) {
			Resources<SurfaceResource> resources = adapter.adaptAll(surfaceCollection.getContent());
			return Response.ok(resources).build();
		} else {
			return Response.noContent().build();
		}
	}

	public void setSurfaceRepository(SurfaceRepository surfaceRepository) {
		this.surfaceRepository = surfaceRepository;
	}

	public void setAdapter(ResourceAdapter<Surface, SurfaceResource> adapter) {
		this.adapter = adapter;
	}
}

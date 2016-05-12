package br.com.elo7.mars.explorer.api.endpoint;

import br.com.elo7.mars.explorer.api.resource.ExplorerResource;
import br.com.elo7.mars.explorer.api.resource.SurfaceResource;
import br.com.elo7.mars.explorer.api.resource.adapter.ResourceAdapter;
import br.com.elo7.mars.explorer.engine.application.SurfaceScanEngine;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import br.com.elo7.mars.explorer.engine.domain.surface.SurfaceRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.ArrayList;
import java.util.Collection;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * Surface Resource Endpoint
 *
 * @author pedrotoliveira
 */
@Component
@Path("/surface")
@Api(value = "Surface Resource", description = "Surface Resource", produces = "application/json")
public class SurfaceEndpoint {

	@Autowired
	private SurfaceRepository surfaceRepository;
	@Autowired
	private ResourceAdapter<Surface, SurfaceResource> adapter;
	@Autowired
	private SurfaceScanEngine surfaceScanEngine;

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Retrieve a Created Surface", notes = "Retrieve a Surface", nickname = "getById")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK", response = SurfaceResource.class),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Failure")
	})
	public Response get(
			@ApiParam("Surface Id") @NotNull @PathParam("id") String id,
			@ApiParam("Expand The Resource") @DefaultValue("false") @QueryParam("expand") Boolean expand) {
		
		Surface surface = surfaceRepository.findOne(id);
		if (surface == null) {
			throw new NotFoundException("Surface Not Found");
		}
		return Response.ok(adapter.adapt(surface, expand)).build();
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Create a Surface and Scan", notes = "Create a Surface and Scan", nickname = "createSurfaceAndScan")
	@ApiParam(value = "Surface Resource", name = "Surface Respurce", required = true)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK", response = SurfaceResource.class),
		@ApiResponse(code = 201, message = "Created", response = SurfaceResource.class),
		@ApiResponse(code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Failure")
	})
	public Response post(@NotNull SurfaceResource surfaceResource) {
		if (surfaceResource.getId() != null) {
			return updateResource(surfaceResource);
		}
		Collection<String> inputs = new ArrayList<>();
		inputs.add(surfaceResource.getDimension().formattedInput());
		if (surfaceResource.hasDeployedExplorers()) {
			surfaceResource.getDeployedExplorers().forEach((ExplorerResource deployed) -> {
				inputs.add(deployed.getCurrentPosition().formattedInput());
				inputs.add(deployed.getInstructions());
			});
		}
		surfaceScanEngine.createSurfaceAndScan(inputs);
		
		
		return null;
	}

	public void setSurfaceRepository(SurfaceRepository surfaceRepository) {
		this.surfaceRepository = surfaceRepository;
	}

	public void setAdapter(ResourceAdapter<Surface, SurfaceResource> adapter) {
		this.adapter = adapter;
	}

	private Response updateResource(SurfaceResource surfaceResource) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}

}

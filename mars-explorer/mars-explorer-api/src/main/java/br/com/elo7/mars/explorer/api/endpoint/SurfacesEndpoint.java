package br.com.elo7.mars.explorer.api.endpoint;

import br.com.elo7.mars.explorer.api.resource.SurfaceResource;
import br.com.elo7.mars.explorer.api.service.SurfaceService;
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
    private SurfaceService surfaceService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Retrieve Created Surfaces", notes = "Retrieve Created Surfaces", nickname = "get")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK", response = SurfaceResource.class, responseContainer = "List"),
		@ApiResponse(code = 204, message = "No Content"),
		@ApiResponse(code = 500, message = "Failure")
	})
	public Response get(
			@ApiParam(value = "Max Results Per Page") @QueryParam("max") @DefaultValue("20") int max,
			@ApiParam(value = "Page") @QueryParam("page") @DefaultValue("0") int page) {
        return Response.ok(surfaceService.findAll(max, page)).build();
	}

    public SurfaceService getSurfaceService() {
        return surfaceService;
    }

    public void setSurfaceService(SurfaceService surfaceService) {
        this.surfaceService = surfaceService;
    }
}

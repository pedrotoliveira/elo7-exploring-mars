package br.com.elo7.mars.explorer.api.endpoint;

import br.com.elo7.mars.explorer.api.resource.ExplorerResource;
import br.com.elo7.mars.explorer.api.resource.SurfaceResource;
import br.com.elo7.mars.explorer.api.resource.error.Message;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.stereotype.Component;

/**
 *
 * Explorer Resource Endpoint
 *
 * @author pedrotoliveira
 */
@Component
@Path("/explorer")
@Api(value = "Explorer Resource", description = "Explorer Resource", produces = "application/json")
public class ExplorerEndpoint {

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Retrieve a Deployed Explorer", notes = "Retrieve a Explorer", nickname = "getById")
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "OK", response = ExplorerResource.class),
		@ApiResponse(code = 404, message = "Not Found", response = Message.class),
		@ApiResponse(code = 500, message = "Failure", response = Message.class)
	})
	public Response get(@ApiParam("Explorer Id") @NotNull @PathParam("id") String id) {
		return Response.ok().build();
	}
}

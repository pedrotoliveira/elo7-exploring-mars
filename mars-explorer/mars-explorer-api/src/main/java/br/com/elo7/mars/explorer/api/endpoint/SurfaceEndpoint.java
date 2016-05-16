package br.com.elo7.mars.explorer.api.endpoint;

import br.com.elo7.mars.explorer.api.resource.ExplorerResource;
import br.com.elo7.mars.explorer.api.resource.SurfaceResource;
import br.com.elo7.mars.explorer.api.resource.error.Message;
import br.com.elo7.mars.explorer.api.service.SurfaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.jaxrs.PATCH;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
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

    @Context
    private UriInfo uriInfo;

    @Autowired
    private SurfaceService surfaceService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Retrieve a Created Surface", notes = "Retrieve a Surface", nickname = "getById")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = SurfaceResource.class),
        @ApiResponse(code = 404, message = "Not Found", response = Message.class),
        @ApiResponse(code = 500, message = "Failure", response = Message.class)
    })
    public Response get(
            @ApiParam("Surface Id") @PathParam("id") String id,
            @ApiParam("Expand The Resource") @DefaultValue("false") @QueryParam("expand") boolean expand) {
        return Response.ok(surfaceService.findById(id, expand)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Create a Surface and Scan", notes = "Create a Surface and Scan", nickname = "createSurfaceAndScan")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created", response = SurfaceResource.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Message.class),
        @ApiResponse(code = 404, message = "Not Found", response = Message.class),
        @ApiResponse(code = 500, message = "Failure", response = Message.class)
    })
    public Response post(
            @ApiParam(value = "SurfaceResource", required = true) @NotNull @Valid SurfaceResource surfaceResource,
            @ApiParam("Show Execution Details") @QueryParam("details") @DefaultValue("true") boolean details,
            @ApiParam("Expand The Resource") @DefaultValue("false") @QueryParam("expand") boolean expand) {
        Resource<SurfaceResource> resource = surfaceService.createSurfaceAndScan(surfaceResource, expand);
        return (details)
                ? Response.created(uriInfo.getRequestUri()).entity(resource).build()
                : Response.created(uriInfo.getRequestUri()).entity(resource.getContent().getExplorersPositions()).build();
    }

    @PATCH
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Update a Surface and Scan", notes = "Update a Surface and Scan", nickname = "updateSurfaceAndScan")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = SurfaceResource.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Message.class),
        @ApiResponse(code = 404, message = "Not Found", response = Message.class),
        @ApiResponse(code = 500, message = "Failure", response = Message.class)
    })
    public Response patch(
            @ApiParam(value = "SurfaceResource", required = true) @NotNull @Valid SurfaceResource surfaceResource,
            @ApiParam("Show Execution Details") @QueryParam("details") @DefaultValue("true") boolean details,
            @ApiParam("Expand The Resource") @DefaultValue("false") @QueryParam("expand") boolean expand) {
        Resource<SurfaceResource> resource = surfaceService.updateSurfaceAndScan(surfaceResource, expand);
        return (details)
                ? Response.ok(resource).build()
                : Response.ok(resource.getContent().getExplorersPositions()).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Delete a Surface", notes = "Delete a Surface", response = void.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "OK", response = void.class),
        @ApiResponse(code = 404, message = "Not Found", response = Message.class),
        @ApiResponse(code = 500, message = "Failure", response = Message.class)
    })
    public Response delete(@ApiParam("Surface Id") @PathParam("id") String id) {
        surfaceService.delete(id);
        return Response.ok().build();
    }

    @POST
    @Path("/{id}/explorers/explorer")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Deploy a Explorer and Move", notes = "Deploy a Explorer and Move", nickname = "deployAndScan")
    @ApiResponses(value = {
        @ApiResponse(code = 201, message = "Created", response = SurfaceResource.class),
        @ApiResponse(code = 400, message = "Bad Request", response = Message.class),
        @ApiResponse(code = 404, message = "Not Found", response = Message.class),
        @ApiResponse(code = 500, message = "Failure", response = Message.class)
    })
    public Response post(
            @ApiParam("Surface Id") @PathParam("id") String id,
            @ApiParam(value = "ExplorerResource", required = true) @NotNull @Valid ExplorerResource explorerResource,
            @ApiParam("Expand The Resource") @DefaultValue("false") @QueryParam("expand") boolean expand) {
        return Response.ok(surfaceService.deployAndScan(id, explorerResource, expand)).build();
    }

    public SurfaceService getSurfaceService() {
        return surfaceService;
    }
}

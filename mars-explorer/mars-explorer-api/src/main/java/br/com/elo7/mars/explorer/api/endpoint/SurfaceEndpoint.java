package br.com.elo7.mars.explorer.api.endpoint;

import static br.com.elo7.mars.explorer.api.resource.error.MessageType.Internal_Architecture_Error;
import static br.com.elo7.mars.explorer.api.resource.error.MessageType.Parameter_Error;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

import br.com.elo7.mars.explorer.api.resource.SurfaceResource;
import br.com.elo7.mars.explorer.api.resource.adapter.ResourceAdapter;
import br.com.elo7.mars.explorer.api.resource.error.Message;
import br.com.elo7.mars.explorer.engine.application.SurfaceScanEngine;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import br.com.elo7.mars.explorer.engine.domain.surface.SurfaceRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.net.URI;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(SurfaceEndpoint.class);

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
            @ApiParam("Expand The Resource") @DefaultValue("false") @QueryParam("expand") boolean expand) {
        Surface surface = findById(id);
        return Response.ok(adapter.adapt(surface, expand)).build();
    }

    private Surface findById(String id) throws NotFoundException {
        Surface surface = surfaceRepository.findOne(id);
        if (surface == null) {
            throw new NotFoundException("Surface Not Found");
        }
        return surface;
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
    public Response post(
            @ApiParam("Surface Resource") @NotNull @Valid SurfaceResource surfaceResource,
            @ApiParam("Show Execution Details") @QueryParam("details") @DefaultValue("true") boolean details,
            @ApiParam("Expand The Resource") @DefaultValue("false") @QueryParam("expand") boolean expand) {

        Surface surface = (surfaceResource.getId() == null)
                ? createSurface(surfaceResource)
                : findById(surfaceResource.getId());

        if (surfaceResource.hasDeployedExplorers()) {
            return deployExplorersAndScan(surface, surfaceResource, details, expand);
        } else {
            Resource<SurfaceResource> resource = adapter.adapt(surface, expand);
            return Response.created(URI.create(resource.getLink("surface").getHref())).entity(resource).build();
        }
    }

    private Surface createSurface(SurfaceResource surfaceResource) {
        return surfaceScanEngine.createSurface(surfaceResource.getDimension().formattedInput());
    }

    private Response deployExplorersAndScan(Surface surface, SurfaceResource surfaceResource, boolean details, boolean expand) {
        try {
            surfaceScanEngine.deployExplorers(surface.getId(), surfaceResource.extractExplorersInputCollection());
        } catch (IllegalArgumentException ex) {
            Message message = new Message(Parameter_Error, BAD_REQUEST);
            message.addNotification(ex.getMessage());
            logger.error(message.toString(), ex);
            Resource<SurfaceResource> resource = adapter.adaptWithErros(surface, expand, message);
            return Response.status(BAD_REQUEST).entity(resource).build();
        }

        try {
            surfaceScanEngine.scan(surface.getId());
            Resource<SurfaceResource> resource = adapter.adapt(surface, expand);
            return (details)
                    ? Response.ok(resource).build()
                    : Response.ok(resource.getContent().getExplorersPositions()).build();
        } catch (Exception ex) {
            Message message = new Message(Internal_Architecture_Error, INTERNAL_SERVER_ERROR);
            message.addNotification(ex.getMessage());
            logger.error(message.toString(), ex);
            Resource<SurfaceResource> resource = adapter.adaptWithErros(surface, expand, message);
            return Response.status(INTERNAL_SERVER_ERROR).entity(resource).build();
        }
    }

    public void setSurfaceRepository(SurfaceRepository surfaceRepository) {
        this.surfaceRepository = surfaceRepository;
    }

    public void setAdapter(ResourceAdapter<Surface, SurfaceResource> adapter) {
        this.adapter = adapter;
    }

}

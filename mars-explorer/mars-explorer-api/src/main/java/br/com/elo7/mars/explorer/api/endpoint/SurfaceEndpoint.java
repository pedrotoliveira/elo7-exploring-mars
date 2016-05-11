package br.com.elo7.mars.explorer.api.endpoint;

import io.swagger.annotations.Api;
import javax.ws.rs.Path;
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
}

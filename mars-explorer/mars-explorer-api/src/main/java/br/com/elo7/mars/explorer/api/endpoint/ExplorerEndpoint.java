package br.com.elo7.mars.explorer.api.endpoint;

import io.swagger.annotations.Api;
import javax.ws.rs.Path;
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

}

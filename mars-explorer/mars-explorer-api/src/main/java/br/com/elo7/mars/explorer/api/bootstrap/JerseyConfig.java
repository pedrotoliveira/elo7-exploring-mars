package br.com.elo7.mars.explorer.api.bootstrap;

import br.com.elo7.mars.explorer.api.endpoint.SurfacesEndpoint;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * Jersey Configuration
 *
 * @author pedrotoliveira
 */
@Configuration
@ApplicationPath("mars-explorer/api")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
        register(SurfacesEndpoint.class);
	}
}

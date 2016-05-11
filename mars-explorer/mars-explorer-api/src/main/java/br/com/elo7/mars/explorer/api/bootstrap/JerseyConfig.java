package br.com.elo7.mars.explorer.api.bootstrap;

import br.com.elo7.mars.explorer.api.endpoint.ExplorerEndpoint;
import br.com.elo7.mars.explorer.api.endpoint.SurfacesEndpoint;
import br.com.elo7.mars.explorer.api.endpoint.UnhandledExceptionMapper;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

/**
 * Jersey Configuration
 *
 * @author pedrotoliveira
 */
@Configuration
@ApplicationPath("api")
public class JerseyConfig extends ResourceConfig {

	public JerseyConfig() {
		packages("br.com.elo7.mars.explorer.api");
		registerClasses(registerEndpoints());
	}

	@PostConstruct
	public void init() {
		configureSwagger();
	}

	private Class<?>[] registerEndpoints() {
		return new Class<?>[]{
			SurfacesEndpoint.class,
			SurfacesEndpoint.class,
			ExplorerEndpoint.class,
			UnhandledExceptionMapper.class
		};
	}

	private void configureSwagger() {
		register(ApiListingResource.class);
		register(SwaggerSerializers.class);

		BeanConfig config = new BeanConfig();
		config.setTitle("Elo7 Mars Explorer API");
		config.setDescription("Elo7 Mars Explorer API - Teste de Programa\u00E7\u00E3o Elo7");
		config.setContact("Pedro T. Oliveira - pedro.oliveira20@gmail.com");
		config.setVersion("1.0-SNAPSHOT");
		config.setLicense("Apache V2");
		config.setLicenseUrl("http://www.apache.org/licenses/LICENSE-2.0");
		config.setSchemes(new String[]{"http"});
		config.setConfigId("Mars Explorer");
		config.setBasePath("/mars-explorer/api");
		config.setResourcePackage("br.com.elo7.mars.explorer.api.endpoint");
		config.setPrettyPrint(true);
		config.setScan(true);
	}
}

package br.com.elo7.mars.explorer.api.service;

import br.com.elo7.mars.explorer.api.resource.SurfaceResource;
import org.springframework.hateoas.Resources;

/**
 * Surface Service Layer
 * 
 * @author pedrotoliveira
 */
public interface SurfaceService {
	
	/**
	 * Find All Surfaces By Range.
	 * 
	 * @param max
	 * @param page
	 * @return 
	 */
	Resources<SurfaceResource> findAll(int max, int page);
	
	
	
}

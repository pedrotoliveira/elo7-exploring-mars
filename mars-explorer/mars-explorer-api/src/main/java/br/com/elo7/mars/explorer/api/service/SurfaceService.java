package br.com.elo7.mars.explorer.api.service;

import br.com.elo7.mars.explorer.api.resource.ExplorerResource;
import br.com.elo7.mars.explorer.api.resource.SurfaceResource;
import org.springframework.hateoas.Resource;
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
     * @param max  max results
     * @param page current page.
     *
     * @return a Resources<SurfaceResource>
     */
    Resources<SurfaceResource> findAll(int max, int page);

    /**
     * Find a surface resource by Id
     *
     * @param id     surface id
     * @param expand expand the resource
     *
     * @return a Resource<SurfaceResource>
     */
    Resource<SurfaceResource> findById(String id, boolean expand);

    /**
     * Create a Surface, Deploy Explorers and Scan.
     *
     * @param surfaceResource
     * @param details
     * @param expand
     *
     * @return a Resource<SurfaceResource>
     */
    Resource<SurfaceResource> createSurfaceAndScan(SurfaceResource surfaceResource, boolean expand);

    /**
     * Update a Surface, Deploy Explorers and Scan.
     *
     * @param surfaceResource
     * @param details
     * @param expand
     *
     * @return a Resource<SurfaceResource>
     */
    Resource<SurfaceResource> updateSurfaceAndScan(SurfaceResource surfaceResource, boolean expand);

    /**
     * Delete a Surface
     *
     * @param surfaceId the surface Id
     */
    void delete(String surfaceId);

    /**
     * Find a Deployed Explorer
     *
     * @param explorerId explorer id
     *
     * @return a Resource<ExplorerResource>
     */
    Resource<ExplorerResource> findExplorer(String explorerId);

    /**
     * Deploy a Explorer and Scan
     *
     * @param surfaceId        surface id
     * @param explorerResource explorer resource
     * @param expand           expand resource
     *
     * @return a Resource<ExplorerResource>
     */
    Resource<SurfaceResource> deployAndScan(String surfaceId, ExplorerResource explorerResource, boolean expand);

}

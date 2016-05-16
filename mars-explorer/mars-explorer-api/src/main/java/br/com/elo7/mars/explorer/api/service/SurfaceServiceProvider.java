package br.com.elo7.mars.explorer.api.service;

import static br.com.elo7.mars.explorer.api.resource.error.MessageType.Internal_Architecture_Error;
import static br.com.elo7.mars.explorer.api.resource.error.MessageType.Parameter_Error;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;

import br.com.elo7.mars.explorer.api.resource.ExplorerResource;
import br.com.elo7.mars.explorer.api.resource.SurfaceResource;
import br.com.elo7.mars.explorer.api.resource.adapter.ResourceAdapter;
import br.com.elo7.mars.explorer.api.resource.error.Message;
import br.com.elo7.mars.explorer.engine.application.SurfaceScanEngine;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import br.com.elo7.mars.explorer.engine.domain.surface.SurfaceRepository;
import java.util.ArrayList;
import java.util.Collection;
import javax.ws.rs.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

/**
 * Surface Services Provider
 *
 * @author pedrotoliveira
 */
@Service
public class SurfaceServiceProvider implements SurfaceService {

    private static final Logger logger = LoggerFactory.getLogger(SurfaceServiceProvider.class);

    @Autowired
    private SurfaceRepository surfaceRepository;
    @Autowired
    private ResourceAdapter<Surface, SurfaceResource> adapter;
    @Autowired
    private SurfaceScanEngine surfaceScanEngine;

    @Override
    public Resources<SurfaceResource> findAll(int max, int page) {
        PageRequest pageRequest = new PageRequest(page, max);
        Page<Surface> surfaceCollection = surfaceRepository.findAll(pageRequest);
        if (surfaceCollection.hasContent()) {
            return adapter.adaptAll(surfaceCollection.getContent());
        } else {
            return new Resources<>(new ArrayList<>());
        }
    }

    @Override
    public Resource<SurfaceResource> findById(String id, boolean expand) {
        return adapter.adapt(findById(id), expand);
    }

    private Surface findById(String id) throws NotFoundException {
        Surface surface = surfaceRepository.findOne(id);
        if (surface == null) {
            throw new NotFoundException("Surface Not Found");
        }
        return surface;
    }

    @Override
    public Resource<SurfaceResource> createSurfaceAndScan(SurfaceResource surfaceResource, boolean expand) {
        Surface surface = surfaceScanEngine.createSurface(surfaceResource.getDimension().formattedInput());
        if (surfaceResource.hasDeployedExplorers()) {
            return deployExplorersAndScan(surface, surfaceResource.extractExplorersInputCollection(), expand);
        } else {
            return adapter.adapt(surface, expand);
        }
    }

    @Override
    public Resource<SurfaceResource> updateSurfaceAndScan(SurfaceResource surfaceResource, boolean expand) {
        Surface surface = findById(surfaceResource.getId());
        if (surfaceResource.hasDeployedExplorers()) {
            return deployExplorersAndScan(surface, surfaceResource.extractExplorersInputCollection(), expand);
        } else {
            return adapter.adapt(surface, expand);
        }
    }

    private Resource<SurfaceResource> deployExplorersAndScan(Surface surface, Collection<String> explorersInputs, boolean expand) {
        try {
            surfaceScanEngine.deployExplorers(surface.getId(), explorersInputs);
            surfaceScanEngine.scan(surface.getId());
            return adapter.adapt(surface, expand);
        } catch (IllegalArgumentException ex) {
            Message message = new Message(Parameter_Error, BAD_REQUEST);
            message.addNotification(ex.getMessage());
            logger.error(message.toString(), ex);
            return adapter.adaptWithErros(surface, expand, message);
        } catch (Exception ex) {
            Message message = new Message(Internal_Architecture_Error, INTERNAL_SERVER_ERROR);
            message.addNotification(ex.getMessage());
            logger.error(message.toString(), ex);
            return adapter.adaptWithErros(surface, expand, message);
        }
    }

    @Override
    public void delete(String surfaceId) {
        surfaceScanEngine.deleteSurface(surfaceId);
    }

    @Override
    public Resource<SurfaceResource> deployAndScan(String surfaceId, ExplorerResource explorerResource, boolean expand) {
        Surface surface = findById(surfaceId);
        return deployExplorersAndScan(surface, explorerResource.extractInputCollection(), expand);
    }

    public void setSurfaceRepository(SurfaceRepository surfaceRepository) {
        this.surfaceRepository = surfaceRepository;
    }

    public void setAdapter(ResourceAdapter<Surface, SurfaceResource> adapter) {
        this.adapter = adapter;
    }

    public void setSurfaceScanEngine(SurfaceScanEngine surfaceScanEngine) {
        this.surfaceScanEngine = surfaceScanEngine;
    }

    @Override
    public Resource<ExplorerResource> findExplorer(String explorerId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}

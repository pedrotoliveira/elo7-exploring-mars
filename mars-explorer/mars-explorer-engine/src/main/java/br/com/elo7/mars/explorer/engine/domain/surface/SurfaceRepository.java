package br.com.elo7.mars.explorer.engine.domain.surface;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Surface Repository
 * 
 * @author pedrotoliveira
 */
@Repository
public interface SurfaceRepository extends MongoRepository<Surface, String> {
}

package br.com.elo7.mars.explorer.api.resource.adapter;

import br.com.elo7.mars.explorer.api.resource.BaseResource;
import java.util.Collection;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

/**
 * Generic Adapter Interface
 *
 * @author pedrotoliveira
 * @param <Domain> domain object
 * @param <T> resource
 */
public interface ResourceAdapter<Domain, T extends BaseResource> {

    /**
     * Adapt a Domain object to a Resource Representation Expanded
     *
     * @param domain object
     *
     * @return the resource
     */
    Resource<T> adaptExpandedResource(Domain domain);

    /**
     * Adapt a Domain object to a Resource Representation Compressed
     *
     * @param domain object
     *
     * @return the resource
     */
    Resource<T> adaptResource(Domain domain);

    /**
     * Adapt a Domain object to a Resource Representation
     *
     * @param domain object
     * @param expand the resource
     *
     * @return the resource
     */
    default Resource<T> adapt(Domain domain, boolean expand) {
        return (expand) ? adaptExpandedResource(domain) : adaptResource(domain);
    }

    /**
     * Adapt a Domain Collection to a Resource Collection
     *
	 * @param domainCollection
     * @return Resources
     */
    Resources<T> adaptAll(Collection<Domain> domainCollection);
}

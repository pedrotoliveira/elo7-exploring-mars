package br.com.elo7.mars.explorer.api.resource.adapter;

import br.com.elo7.mars.explorer.api.resource.BaseResource;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.hateoas.Resources;

/**
 * Generic Adapter Interface
 * 
 * @author pedrotoliveira
 */
public interface ResourceAdapter<Domain, T extends BaseResource> {
		
	/**
	 * Adapt a Domain object to a Resource Representation
	 * 
	 * @param domain object
	 * @param expand or the resource
	 * @return the resource
	 */
	Resources<T> adapt(Domain domain, boolean expand);
	
	/**
	 * Adapt a Domain Collection to a Resource Collection
	 * 
	 * @param domain
	 * @return 
	 */
	default Collection<Resources<T>> adaptAll(Collection<Domain> domainCollection) {
		Collection<Resources<T>> collection = new ArrayList<>();
		domainCollection.stream().forEach((domain) -> collection.add(adapt(domain, false)));
		return collection;
	}	
}

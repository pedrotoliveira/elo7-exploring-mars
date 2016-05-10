package br.com.elo7.mars.explorer.api.resource;

import java.util.ArrayList;
import java.util.List;
import org.springframework.hateoas.Link;

/**
 * Base Resource
 *
 * @author pedrotoliveira
 */
public abstract class BaseResource {

	private List<Link> links = new ArrayList<>();

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}
	
	public abstract Class<?> getEndpointClass();
}

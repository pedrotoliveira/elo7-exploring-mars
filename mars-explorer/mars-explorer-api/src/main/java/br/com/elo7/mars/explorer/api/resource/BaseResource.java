package br.com.elo7.mars.explorer.api.resource;

import br.com.elo7.mars.explorer.api.resource.error.Message;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

/**
 * Base Resource
 *
 * @author pedrotoliveira
 */
public abstract class BaseResource implements Serializable  {

	private static final long serialVersionUID = -6298081853568281878L;

	/**
	 * Endpoint Class that expose this resource.
	 *
	 * @return Endpoint Class
	 */
	@JsonIgnore
	public abstract Class<?> getEndpointClass();

	/**
	 * Link Relation to this Resource
	 *
	 * @return Link relation
	 */
	@ApiModelProperty("Resource Link Relation")
	public abstract String getRel();

	@JsonProperty("errors")
	@ApiModelProperty("Error Messages")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
	private List<Message> errors = new ArrayList<>();

	public BaseResource errors(List<Message> errors) {
		this.errors = errors;
		return this;
	}

	public List<Message>  getErrors() {
		return errors;
	}

	public void setErrors(List<Message> errors) {
		this.errors = errors;
	}

    public void addError(final Message error) {
        getErrors().add(error);
    }

	/**
	 * Build Resource Representation with Links
	 *
	 * @param links
	 * @return a Resource
	 */
	public abstract Resource<? extends BaseResource> buildResource(Link... links);

	/**
	 * Build Resource Representation with Links
	 *
	 * @param links
	 * @return a Resource
	 */
	public abstract Resource<? extends BaseResource> buildResource(List<Link> links);
}

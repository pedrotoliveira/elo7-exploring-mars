package br.com.elo7.mars.explorer.api.resource;

import br.com.elo7.mars.explorer.api.endpoint.SurfaceEndpoint;
import br.com.elo7.mars.explorer.api.resource.json.DateTimeJsonSerializer;
import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

/**
 * SurfaceResource Resource
 */
@JsonPropertyOrder({"id",
	"dimension",
	"explorersPositions",
	"createdDate",
	"deployedExplorers",
	"errors"})
@ApiModel(description = "surface", parent = BaseResource.class)
public class SurfaceResource extends BaseResource {

	private static final long serialVersionUID = 6438815192131169412L;

	@JsonProperty("id")
	@ApiModelProperty(value = "UUID of Created Surface", example = "6ed96c06-64ba-4e5f-8090-4a5884d2e597")
	private String id;

	@JsonProperty("dimension")
	@ApiModelProperty(required = true, value = "Surface Dimension")
	@NotNull
	private Dimension dimension;

	@JsonProperty("explorerPositions")
	@ApiModelProperty(value = "Deployed Explorer Positions", example = "1 3 N", readOnly = true)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Collection<String> explorersPositions;

	@JsonProperty("createdDate")
	@ApiModelProperty(value = "Creation Date", readOnly = true)
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	@JsonSerialize(using = DateTimeJsonSerializer.class)
	private DateTime createdDate;

	@JsonProperty("deployedExplorers")
	@ApiModelProperty(value = "Deployed Explorers")
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private Resources<ExplorerResource> deployedExplorers;

	public SurfaceResource id(String id) {
		this.id = id;
		return this;
	}

	public SurfaceResource dimension(Dimension dimension) {
		this.dimension = dimension;
		return this;
	}

	public SurfaceResource createdDate(Date createdDate) {
		this.createdDate = new DateTime(createdDate.getTime());
		return this;
	}

	public SurfaceResource deployedExplorers(Resources<ExplorerResource> deployedExplorers) {
		this.deployedExplorers = deployedExplorers;
		return this;
	}

	public SurfaceResource explorersPositions(Collection<String> explorersPositions) {
		this.explorersPositions = explorersPositions;
		return this;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}

	public Collection<String> getExplorersPositions() {
		return explorersPositions;
	}

	public void setExplorerPositions(List<String> explorersPositions) {
		this.explorersPositions = explorersPositions;
	}

	public DateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate;
	}

	public boolean hasDeployedExplorers() {
		return getDeployedExplorers().getContent() != null && !getDeployedExplorers().getContent().isEmpty();
	}

	public Resources<ExplorerResource> getDeployedExplorers() {
		return deployedExplorers;
	}

	public void setDeployedExplorers(Resources<ExplorerResource> deployedExplorers) {
		this.deployedExplorers = deployedExplorers;
	}

	public Collection<String> extractExplorersInputCollection() {
		Collection<String> inputs = new ArrayList<>();
		if (hasDeployedExplorers()) {
			getDeployedExplorers().forEach((ExplorerResource deployed) -> {
				inputs.addAll(deployed.extractInputCollection());
			});
		}
		return inputs;
	}

	@Override
	public Class<?> getEndpointClass() {
		return SurfaceEndpoint.class;
	}

	@Override
	public List<String> getRels() {
		return Arrays.asList(new String[]{"surface"});
	}

	@Override
	public Resource<SurfaceResource> buildResource(Link... links) {
		return new Resource<>(this, links);
	}

	@Override
	public Resource<SurfaceResource> buildResource(List<Link> links) {
		return new Resource<>(this, links);
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.id);
		hash = 97 * hash + Objects.hashCode(this.dimension);
		hash = 97 * hash + Objects.hashCode(this.deployedExplorers);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final SurfaceResource other = (SurfaceResource) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		if (!Objects.equals(this.dimension, other.dimension)) {
			return false;
		}
		if (!Objects.equals(this.deployedExplorers, other.deployedExplorers)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Surface[" + "id=" + id
				+ ", dimension=" + dimension
				+ ", createdDate=" + createdDate
				+ ", deployedExplorers=" + deployedExplorers
				+ ", errors=" + getErrors() + ']';
	}
}

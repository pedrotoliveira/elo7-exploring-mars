package br.com.elo7.mars.explorer.api.resource;

import br.com.elo7.mars.explorer.api.endpoint.ExplorerEndpoint;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import br.com.elo7.mars.explorer.engine.domain.explorer.InstructionAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

/**
 * A ExplorerResource
 */
@JsonPropertyOrder({"id",
	"xAxis",
	"yAxis",
	"direction",
	"instructions",
	"executionResults",
	"registeredInstructions",
	"errors"})
@ApiModel(description = "A Explorer", parent = BaseResource.class)
public class ExplorerResource extends BaseResource {

	private static final long serialVersionUID = -6181102769708333124L;

	@JsonProperty("id")
	@ApiModelProperty(name = "id", value = "UUID of explorer", example = "00d27a95-abc0-434d-8e2c-eb96bfea32f0")
	private String id;

	@JsonProperty("xAxis")
	@ApiModelProperty(name = "xAxis", value = "X Axis Position in the current surface", example = "1", required = true)
	@NotNull
	private Integer xAxis;

	@JsonProperty("yAxis")
	@ApiModelProperty(name = "yAxis", value = "Y Axis Position in the current Surface", example = "2", required = true)
	@NotNull
	private Integer yAxis;

	@JsonProperty("direction")
	@ApiModelProperty(name = "direction", value = "Explorer Direction", example = "N", required = true)
	@NotNull
	private String direction;

	@JsonProperty("instructions")
	@ApiModelProperty(name = "instructions", value = "Explorer movement instructions.", example = "LMLMLMLMM", required = true)
	@NotNull
	private String instructions;

	@JsonProperty("executionResults")
	@ApiModelProperty(value = "The Execution Results", readOnly = true)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<ExecutionResultResource> executionResults = new ArrayList<>();

	@JsonProperty("registeredInstructions")
	@ApiModelProperty(value = "The Registered Instrauctions", readOnly = true)
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<String> registeredInstructions = new ArrayList<>();

	public ExplorerResource() {
	}

	public ExplorerResource(Integer xAxis, Integer yAxis, String direction) {
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.direction = direction;
	}

	public ExplorerResource id(String id) {
		this.id = id;
		return this;
	}

	public ExplorerResource currentPosition(ExplorerPosition currentPosition) {
		xAxis(currentPosition.getxAxis()).yAxis(currentPosition.getyAxis()).direction(currentPosition.getDirectionAsString());
		return this;
	}

	public ExplorerResource xAxis(Integer xAxis) {
		this.xAxis = xAxis;
		return this;
	}

	public ExplorerResource yAxis(Integer yAxis) {
		this.yAxis = yAxis;
		return this;
	}

	public ExplorerResource direction(String direction) {
		this.direction = direction;
		return this;
	}

	public ExplorerResource executionResults(List<ExecutionResultResource> executionResults) {
		this.executionResults = executionResults;
		return this;
	}

	public ExplorerResource instructions(Collection<InstructionAction> instructionActions) {
		StringBuilder builder = new StringBuilder();
		instructionActions.stream().forEach((action) -> builder.append(action.getRepresentation()));
		this.instructions = builder.toString();
		return this;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getxAxis() {
		return xAxis;
	}

	public void setxAxis(Integer xAxis) {
		this.xAxis = xAxis;
	}

	public Integer getyAxis() {
		return yAxis;
	}

	public void setyAxis(Integer yAxis) {
		this.yAxis = yAxis;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public List<ExecutionResultResource> getExecutionResults() {
		return executionResults;
	}

	public void setExecutionResults(List<ExecutionResultResource> executionResults) {
		this.executionResults = executionResults;
	}

	public List<String> getRegisteredInstructions() {
		return registeredInstructions;
	}

	public void setRegisteredInstructions(List<String> registeredInstructions) {
		this.registeredInstructions = registeredInstructions;
	}

	@Override
	public Class<?> getEndpointClass() {
		return ExplorerEndpoint.class;
	}

	@Override
	public List<String> getRels() {
		return Arrays.asList(new String[]{"explorer"});
	}

	@Override
	public Resource<ExplorerResource> buildResource(Link... links) {
		return new Resource<>(this, links);
	}

	@Override
	public Resource<ExplorerResource> buildResource(List<Link> links) {
		return new Resource<>(this, links);
	}

	@JsonIgnore
	public String formattedInput() {
		return String.format("%d %d %s", getxAxis(), getyAxis(), getDirection());
	}

	public Collection<String> extractInputCollection() {
		Collection<String> inputs = new ArrayList<>();
		inputs.add(formattedInput());
		inputs.add(getInstructions());
		return inputs;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 37 * hash + Objects.hashCode(this.xAxis);
		hash = 37 * hash + Objects.hashCode(this.yAxis);
		hash = 37 * hash + Objects.hashCode(this.direction);
		hash = 37 * hash + Objects.hashCode(this.id);
		hash = 37 * hash + Objects.hashCode(this.instructions);
		hash = 37 * hash + Objects.hashCode(this.executionResults);
		hash = 37 * hash + Objects.hashCode(this.registeredInstructions);
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
		final ExplorerResource other = (ExplorerResource) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		if (!Objects.equals(this.direction, other.direction)) {
			return false;
		}
		if (!Objects.equals(this.xAxis, other.xAxis)) {
			return false;
		}
		if (!Objects.equals(this.yAxis, other.yAxis)) {
			return false;
		}
		if (!Objects.equals(this.instructions, other.instructions)) {
			return false;
		}
		if (!Objects.equals(this.executionResults, other.executionResults)) {
			return false;
		}
		if (!Objects.equals(this.registeredInstructions, other.registeredInstructions)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ExplorerResource[" + "id=" + id
				+ ", xAxis=" + xAxis
				+ ", yAxis=" + yAxis
				+ ", direction=" + direction
				+ ", instructions=" + instructions + ']';
	}
}

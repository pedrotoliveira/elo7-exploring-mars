package br.com.elo7.mars.explorer.api.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * A ExplorerResource
 */
@ApiModel(description = "A Explorer")
public class ExplorerResource {
	
	public static final String PATH = "explorer";

	@JsonProperty("id")
	@ApiModelProperty(value = "UUID of explorer")
	private String id;

	@JsonProperty("currentPosition")
	@ApiModelProperty(required = true, value = "The Explorer Current Position")
	@NotNull
	private Position currentPosition;

	@JsonProperty("instructions")
	@ApiModelProperty(required = true, value = "A char sequence representing the explorer movement instructions.\nExample: \"LMRMLMMRMLMRM\"")
	@NotNull
	private String instructions;

	@JsonProperty("executionResults")
	@ApiModelProperty(value = "The Execution Results")
	private List<ExecutionResultResource> executionResults = new ArrayList<ExecutionResultResource>();

	@JsonProperty("registeredInstructions")
	@ApiModelProperty(value = "The Registered Instrauctions")
	private List<String> registeredInstructions = new ArrayList<String>();

	public ExplorerResource() {
	}

	public ExplorerResource(String id, Position currentPosition, String instructions) {
		this.id = id;
		this.currentPosition = currentPosition;
		this.instructions = instructions;
	}

	public ExplorerResource id(String id) {
		this.id = id;
		return this;
	}

	public ExplorerResource currentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
		return this;
	}

	public ExplorerResource instructions(String instructions) {
		this.instructions = instructions;
		return this;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Position getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Position currentPosition) {
		this.currentPosition = currentPosition;
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
	public int hashCode() {
		int hash = 3;
		hash = 37 * hash + Objects.hashCode(this.id);
		hash = 37 * hash + Objects.hashCode(this.currentPosition);
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
		if (!Objects.equals(this.instructions, other.instructions)) {
			return false;
		}
		if (!Objects.equals(this.currentPosition, other.currentPosition)) {
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
		return "Explorer["
				+ "id=" + id
				+ ", currentPosition=" + currentPosition
				+ ", instructions=" + instructions
				+ ", executionResults=" + executionResults
				+ ", registeredInstructions=" + registeredInstructions
				+ ']';
	}

}

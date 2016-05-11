package br.com.elo7.mars.explorer.api.resource;

import br.com.elo7.mars.explorer.api.endpoint.ExplorerEndpoint;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import br.com.elo7.mars.explorer.engine.domain.explorer.InstructionAction;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * A ExplorerResource
 */
@ApiModel(description = "A Explorer")
public class ExplorerResource extends BaseResource {

    @Override
    public Class<?> getEndpointClass() {
        return ExplorerEndpoint.class;
    }

    @Override
    public String getRel() {
        return "explorer";
    }

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
    private List<ExecutionResultResource> executionResults = new ArrayList<>();

    @JsonProperty("registeredInstructions")
    @ApiModelProperty(value = "The Registered Instrauctions")
    private List<String> registeredInstructions = new ArrayList<>();

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

    public ExplorerResource currentPosition(ExplorerPosition currentPosition) {
        this.currentPosition = new Position()
                .xAxis(currentPosition.getxAxis())
                .yAxis(currentPosition.getyAxis())
                .direction(currentPosition.getDirectionAsString());
        return this;
    }

    public ExplorerResource executionResults(List<ExecutionResultResource> executionResults) {
        this.executionResults = executionResults;
        return this;
    }

    public ExplorerResource instructions(Collection<InstructionAction> instructionActions) {
        StringBuilder builder = new StringBuilder();
        instructionActions.stream().forEach((action)-> builder.append(action.getRepresentation()));
        this.instructions = builder.toString();
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

package br.com.elo7.mars.explorer.api.resource;

import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.NotNull;

/**
 * Result of a Instruction Execution.
 *
 * @author pedrotoliveira
 */
@ApiModel(description = "Result of a Instruction Execution.")
public class ExecutionResultResource {

	@JsonProperty("startPosition")
	@ApiModelProperty(required = true, value = "Start Position")
	@NotNull
	private Position startPosition;

	@JsonProperty("finalPosition")
	@ApiModelProperty(required = true, value = "Final position")
	@NotNull
	private Position finalPosition;

	@JsonProperty("instruction")
	@ApiModelProperty(required = true, value = "Instruction")
	@NotNull
	private String instruction;

	@JsonProperty("status")
	@ApiModelProperty(required = true, value = "Status of Execution")
	@NotNull
	private String status;

	@JsonProperty("notifications")
	@ApiModelProperty(value = "Details of Execution")
	@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
	private List<String> notifications = new ArrayList<>();

	public ExecutionResultResource() {
	}

	public ExecutionResultResource(Position startPosition, Position finalPosition, String instruction, String status) {
		this.startPosition = startPosition;
		this.finalPosition = finalPosition;
		this.instruction = instruction;
		this.status = status;
	}

	public ExecutionResultResource startPosition(ExplorerPosition startPosition) {
		this.startPosition = new Position()
				.xAxis(startPosition.getxAxis())
				.yAxis(startPosition.getyAxis())
				.direction(startPosition.getDirectionAsString());
		return this;
	}

	public ExecutionResultResource finalPosition(ExplorerPosition finalPosition) {
		this.finalPosition = new Position()
				.xAxis(finalPosition.getxAxis())
				.yAxis(finalPosition.getyAxis())
				.direction(finalPosition.getDirectionAsString());
		return this;
	}

	public ExecutionResultResource instruction(String instruction) {
		this.instruction = instruction;
		return this;
	}

	public ExecutionResultResource status(String status) {
		this.status = status;
		return this;
	}

	public ExecutionResultResource notifications(List<String> notifications) {
		this.notifications = notifications;
		return this;
	}

	public Position getStartPosition() {
		return startPosition;
	}

	public void setStartPosition(Position startPosition) {
		this.startPosition = startPosition;
	}

	public Position getFinalPosition() {
		return finalPosition;
	}

	public void setFinalPosition(Position finalPosition) {
		this.finalPosition = finalPosition;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getNotifications() {
		return notifications;
	}

	public void setNotifications(List<String> notifications) {
		this.notifications = notifications;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 79 * hash + Objects.hashCode(this.startPosition);
		hash = 79 * hash + Objects.hashCode(this.finalPosition);
		hash = 79 * hash + Objects.hashCode(this.instruction);
		hash = 79 * hash + Objects.hashCode(this.status);
		hash = 79 * hash + Objects.hashCode(this.notifications);
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
		final ExecutionResultResource other = (ExecutionResultResource) obj;
		if (!Objects.equals(this.instruction, other.instruction)) {
			return false;
		}
		if (!Objects.equals(this.status, other.status)) {
			return false;
		}
		if (!Objects.equals(this.startPosition, other.startPosition)) {
			return false;
		}
		if (!Objects.equals(this.finalPosition, other.finalPosition)) {
			return false;
		}
		if (!Objects.equals(this.notifications, other.notifications)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ExecutionResult["
				+ "startPosition=" + startPosition
				+ ", finalPosition=" + finalPosition
				+ ", instruction=" + instruction
				+ ", status=" + status
				+ ", notifications=" + notifications + ']';
	}
}

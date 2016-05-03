package br.com.elo7.mars.explorer.engine.domain.explorer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * Result of a Instruction Execution.
 *
 * @author pedrotoliveira
 */
public class ExecutionResult {

	private final Instruction instruction;
	private final String status;
	private final ExplorerPosition startPosition;
	private final ExplorerPosition finalPosition;
	private List<String> notifications;

	public ExecutionResult(Instruction instruction,
			boolean success,
			ExplorerPosition startPosition,
			ExplorerPosition finalPosition) {
		this.instruction = instruction;
		this.status = success ? "Success" : "Fail";
		this.startPosition = startPosition;
		this.finalPosition = finalPosition;
		this.notifications = new ArrayList<>();
	}

	public Instruction getInstruction() {
		return instruction;
	}

	public String getStatus() {
		return status;
	}

	public ExplorerPosition getStartPosition() {
		return startPosition;
	}

	public ExplorerPosition getFinalPosition() {
		return finalPosition;
	}

	public List<String> getNotifications() {
		return Collections.unmodifiableList(notifications);
	}

	public ExecutionResult addNotification(String notification) {
		this.notifications.add(notification);
		return this;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 29 * hash + Objects.hashCode(this.instruction);
		hash = 29 * hash + Objects.hashCode(this.status);
		hash = 29 * hash + Objects.hashCode(this.startPosition);
		hash = 29 * hash + Objects.hashCode(this.finalPosition);
		hash = 29 * hash + Objects.hashCode(this.notifications);
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
		final ExecutionResult other = (ExecutionResult) obj;
		if (!Objects.equals(this.status, other.status)) {
			return false;
		}
		if (this.instruction != other.instruction) {
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
		return "ExecutionResult ["
				+ "instruction=" + instruction
				+ ", status=" + status
				+ ", startPosition=" + startPosition
				+ ", finalPosition=" + finalPosition
				+ ", notifications=" + notifications
				+ ']';
	}
}

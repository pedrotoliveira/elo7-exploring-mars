package br.com.elo7.mars.explorer.engine.domain.explorer;

import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import br.com.elo7.mars.explorer.engine.domain.surface.SurfaceScanResult;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang.Validate;

/**
 * Class MarsExplorer
 *
 * @author pedrotoliveira
 */
class MarsExplorer implements Explorer {

	private String id;
	private ExplorerPosition currentPosition;
	private List<InstructionAction> registeredInstructions;
	private List<ExecutionResult> executionResults;

	public MarsExplorer() {
	}
	
	public MarsExplorer(String id, int xAxis, int yAxis, Direction direction) {
		this.id = id;
		this.currentPosition = new ExplorerPosition(xAxis, yAxis, direction);
		this.registeredInstructions = new ArrayList<>();
		this.executionResults = new ArrayList<>();
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public Explorer registerInstructions(Collection<InstructionAction> instructions) {
		Validate.notEmpty(instructions, "Instructions is Null or Empty");
		getRegisteredInstructions().addAll(instructions);
		return this;
	}

	@Override
	public ExplorerPosition getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(ExplorerPosition currentPosition) {
		this.currentPosition = currentPosition;
	}

	@Override
	public Collection<ExecutionResult> excuteInstructions(Surface surface) {
		Validate.notNull(surface, "Surface is Null");
		Validate.notEmpty(getRegisteredInstructions(), "No instructions registered, invoke registerInstructions first");

		getRegisteredInstructions().forEach(
				(instructionAction) -> {
					ExplorerPosition futurePosition = instructionAction.predictPosition(currentPosition);
					SurfaceScanResult scanResult = surface.scan(this, futurePosition);
					ExecutionResult executionResult = instructionAction.execute(currentPosition, scanResult);
					setCurrentPosition(executionResult.getFinalPosition());
					getExecutionResults().add(executionResult);
				}
		);
		return getExecutionResults();
	}
	
	@Override
	public List<ExecutionResult> getExecutionResults() {
		if (executionResults == null) {
			this.executionResults = new ArrayList<>();
		}
		return executionResults;
	}

	public void setExecutionResults(List<ExecutionResult> executionResults) {
		this.executionResults = executionResults;
	}

	@Override
	public List<InstructionAction> getRegisteredInstructions() {
		if (registeredInstructions == null) {
			this.registeredInstructions = new ArrayList<>();
		}
		return registeredInstructions;
	}

	public void setRegisteredInstructions(List<InstructionAction> registredInstructions) {
		this.registeredInstructions = registredInstructions;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 47 * hash + Objects.hashCode(this.id);
		hash = 47 * hash + Objects.hashCode(this.currentPosition);
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
		final MarsExplorer other = (MarsExplorer) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		if (!Objects.equals(this.currentPosition, other.currentPosition)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "MarsExplorer [" + "id=" + id
				+ ", currentPosition=" + currentPosition
				+ ", registeredInstructions=" + registeredInstructions
				+ ", executionResults=" + executionResults + ']';
	}
}

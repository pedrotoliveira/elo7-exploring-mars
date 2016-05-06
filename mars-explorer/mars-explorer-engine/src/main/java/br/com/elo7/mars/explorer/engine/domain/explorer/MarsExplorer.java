package br.com.elo7.mars.explorer.engine.domain.explorer;

import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;
import org.apache.commons.lang.Validate;

/**
 * Class MarsExplorer
 *
 * @author pedrotoliveira
 */
class MarsExplorer implements Explorer {

	private UUID id;
	private ExplorerPosition currentPosition;
	private Collection<InstructionAction> registeredInstructions;

	public MarsExplorer(UUID id, int xAxis, int yAxis, Direction direction) {
		this.id = id;
		this.currentPosition = new ExplorerPosition(xAxis, yAxis, direction);
		this.registeredInstructions = new ArrayList<>();
	}

	@Override
	public UUID getId() {
		return this.id;
	}

	@Override
	public Explorer registerInstructions(Collection<InstructionAction> instructions) {
		Validate.notEmpty(instructions, "Instructions is Null or Empty");
		registeredInstructions.addAll(instructions);
		return this;
	}

	@Override
	public ExplorerPosition getCurrentPosition() {
		return currentPosition;
	}

	@Override
	public Collection<ExecutionResult> excuteInstructions(Surface surface) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
	
	public Collection<InstructionAction> getRegisteredInstructions() {
		if (registeredInstructions == null) {
			this.registeredInstructions = new ArrayList<>();
		}
		//Comparação da Unmodified Quebra o teste.
		//return Collections.unmodifiableCollection(registeredInstructions);
		return registeredInstructions;
	}

	public void setRegisteredInstructions(Collection<InstructionAction> registredInstructions) {
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
}

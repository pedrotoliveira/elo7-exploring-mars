package br.com.elo7.mars.explorer.engine.model;

import br.com.elo7.mars.explorer.engine.model.validator.CoordinateValidator;
import java.util.Collection;
import java.util.UUID;

/**
 * Class MarsExplorer
 * 
 * @author pedrotoliveira
 */
class MarsExplorer implements Explorer {
	
	private final UUID id;
	private ExplorerPosition currentPosition;

	public MarsExplorer(UUID id, int xAxis, int yAxis, Direction direction) {
		this.id = id;
		this.currentPosition = new ExplorerPosition(xAxis, yAxis, direction);
	}

	@Override
	public UUID getId() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public void registerInstructions(Collection<Instruction> instructions) {		
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public ExecutionResult excuteInstructions(Surface surface) {		
		throw new UnsupportedOperationException("Not supported yet.");
	}
}

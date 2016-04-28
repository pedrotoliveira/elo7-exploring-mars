package br.com.elo7.mars.explorer.engine.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * A Common Area that the Explorers will have to scan.
 * 
 * @author pedrotoliveira
 */
class Plateu implements Surface {
	
	private final UUID id;
	private final Explorer[][] positions;
	private final Map<UUID, Explorer> deployed;
	
	protected Plateu(final UUID id, int xAxis, int yAxis) {		
		this.id = id;
		this.positions = new Explorer[xAxis][yAxis];
		this.deployed = new HashMap<>();
	}

	@Override
	public Explorer deployExplorer(Explorer explorer) {		
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public UUID getId() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Collection<Explorer> getDeployedExplorers() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public Explorer moveExplorerTo(UUID explorerId, ExplorerPosition newPosition) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}

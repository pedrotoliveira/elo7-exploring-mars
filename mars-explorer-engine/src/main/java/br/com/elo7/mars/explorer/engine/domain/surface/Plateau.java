package br.com.elo7.mars.explorer.engine.domain.surface;

import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 *
 * A Common Area that the Explorers will have to scan.
 *
 * @author pedrotoliveira
 */
class Plateau implements Surface {

	private final UUID id;
	private final Explorer[][] positions;
	private final Map<UUID, ExplorerPosition> deployed;

	protected Plateau(final UUID id, int xAxis, int yAxis) {
		this.id = id;
		this.positions = new Explorer[xAxis][yAxis];
		this.deployed = new HashMap<>();
	}

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public Explorer deployExplorer(final Explorer explorer) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public ScanResult scan(ExplorerPosition position) {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}

package br.com.elo7.mars.explorer.engine.domain.surface;

import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang.Validate;

/**
 *
 * A Common Area that the Explorers will have to scan.
 *
 * @author pedrotoliveira
 */
public class Plateau implements Surface {

	private final UUID id;
	private final int xAxis;
	private final int yAxis;
	private final Map<UUID, ExplorerPosition> deployed;

	public Plateau(final UUID id, int xAxis, int yAxis) {
		this.id = id;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.deployed = new HashMap<>();
	}

	@Override
	public UUID getId() {
		return id;
	}

	@Override
	public Explorer deployExplorer(final Explorer explorer) {
		ExplorerPosition position = explorer.getCurrentPosition();
		SurfaceScanResult scanResult = scan(position);
		//TODO IMPLEMENTAR
		return null;
	}

	@Override
	public SurfaceScanResult scan(ExplorerPosition position) {
		//TODO IMPLEMENTAR
		throw new UnsupportedOperationException("Not supported yet.");
	}
}

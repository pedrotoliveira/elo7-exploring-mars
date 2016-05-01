package br.com.elo7.mars.explorer.engine.domain.surface;

import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import java.util.UUID;

/**
 * The surface is the Area that a Explorer can be Deployed or Move in.
 *
 * @author pedrotoliveira
 */
public interface Surface {

	UUID getId();
	/**
	 * Deploy a Explorer in the Specified Position
	 *
	 * @param
	 * @return
	 */
	Explorer deployExplorer(final Explorer explorer);

	/**
	 * Scan a position
	 *
	 * @param position
	 * @return ScanResult
	 */
	ScanResult scan(final ExplorerPosition position);
}

package br.com.elo7.mars.explorer.engine.domain.surface;

import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import java.util.Collection;
import java.util.UUID;

/**
 * The surface is the Area that a Explorer can be Deployed or Move in.
 *
 * @author pedrotoliveira
 */
public interface Surface {

	/**
	 * Get the surface Id
	 *
	 * @return
	 */
	String getId();

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
	 * @return SurfaceScanResult
	 */
	SurfaceScanResult scan(final Explorer position);

	/**
	 * Retrieve a Immutable Collection with a copy of all deployed explorers in the current Surface.
	 *
	 * @return a Collection of Explorer
	 */
	Collection<Explorer> getDeployedExplorers();
}

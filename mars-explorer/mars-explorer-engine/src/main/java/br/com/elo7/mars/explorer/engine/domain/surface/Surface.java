package br.com.elo7.mars.explorer.engine.domain.surface;

import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import java.util.Collection;
import java.util.Date;

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
	 * Get Surface X Axis
	 *
	 * @return X Axis
	 */
	int getxAxis();

	/**
	 * Get Surface Y Axis
	 *
	 * @return Y Axis
	 */
	int getyAxis();

	/**
	 *
	 * @return created date.
	 */
	Date getCreatedDate();

	/**
	 * Deploy a Explorer in the Specified Position
	 *
	 * @param explorer a explorer to deploy
	 * @return deployed Explorer
	 */
	Explorer deployExplorer(final Explorer explorer);

	/**
	 * Scan a Position
	 *
	 * @param explorer current explorer
	 * @param position to be scanned
	 *
	 * @return SurfaceScanResult
	 */
	SurfaceScanResult scan(final Explorer explorer, ExplorerPosition position);

	/**
	 * Retrieve a Immutable Collection with a copy of all deployed explorers in the current Surface.
	 *
	 * @return a Collection of Explorer
	 */
	Collection<Explorer> getDeployedExplorers();
	
	/**
	 * Get Explorers Final Position.
	 * 
	 * @return A String collection with explorers positions.
	 */
	Collection<String> getExplorersPosition();
}

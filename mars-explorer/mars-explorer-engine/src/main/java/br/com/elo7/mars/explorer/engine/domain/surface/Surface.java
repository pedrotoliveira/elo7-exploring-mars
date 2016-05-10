package br.com.elo7.mars.explorer.engine.domain.surface;

import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import java.util.Collection;
import java.util.Date;
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
	 * Get the surface Dimension.
	 * 
	 * @return The surface dimension in the format: ( xAxis, yAxis)
	 */
	String getSurfaceDimension();
	
	/**
	 * 
	 * @return created date.
	 */
	Date getCreatedDate();

	/**
	 * Deploy a Explorer in the Specified Position
	 *
	 * @param Explorer to be deployed
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
}

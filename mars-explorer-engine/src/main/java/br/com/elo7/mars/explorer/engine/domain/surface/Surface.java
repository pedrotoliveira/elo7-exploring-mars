package br.com.elo7.mars.explorer.engine.domain.surface;

import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

/**
 * The surface is the Area that a Explorer can be Deployed or Move in.
 * 
 * @author pedrotoliveira
 */
public interface Surface {
	
	UUID getId();
	
	/**
	 * Try Deploy a Explorer in the Specified Position
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
	
	/**
	 * Try to move a Explorer to a new Position. 
	 * 
	 * If a collision is detected the explorer don't move.
	 * 
	 * @param explorer
	 * @return 
	 */
	Explorer moveExplorerTo(final UUID explorerId, final ExplorerPosition newPosition);
	
	/**
	 * Retrieve a Collection of Deployed Explorers.
	 * 
	 * @return a Collection of Explorer
	 */
	Collection<Explorer> getDeployedExplorers();	
}

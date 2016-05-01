package br.com.elo7.mars.explorer.engine.domain.action;

import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;

/**
 *
 * Movement Action Interface.
 * 
 * @author pedroliveira
 */
public interface MoveAction {
	
	
	/**
	 * Execute a Action in a current explorer position, generating a new one.
	 * 
	 * @param currentPosition
	 * @return 
	 */
	ExplorerPosition execute(ExplorerPosition currentPosition);
}

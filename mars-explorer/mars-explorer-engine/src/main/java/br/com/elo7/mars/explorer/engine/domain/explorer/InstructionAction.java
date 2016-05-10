package br.com.elo7.mars.explorer.engine.domain.explorer;

import br.com.elo7.mars.explorer.engine.domain.surface.SurfaceScanResult;

/**
 * Instruction Action.
 * 
 * @author pedrotoliveira
 */
public interface InstructionAction {
	
	/**
	 * Predict a future position for this action.
	 * 
	 * @param currentPosition
	 * @return future position
	 */
	ExplorerPosition predictPosition(ExplorerPosition currentPosition);
	
	/**
	 * Execute a Instruction based on a current ExplorerPosition and a SurfaceScanResult.
	 * 
	 * @param currentPosition ExplorerPosition
	 * @param scanResult SurfaceScanResult
	 * @return ExecutionResult - holds execution information.
	 * @see ExplorerPosition
	 * @see SurfaceScanResult
	 */
	ExecutionResult execute(ExplorerPosition currentPosition, SurfaceScanResult scanResult);
	
}

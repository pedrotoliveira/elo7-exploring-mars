package br.com.elo7.mars.explorer.engine.domain.explorer;

import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import java.util.Collection;

/**
 * Explorer Interface
 *
 * @author pedrotoliveira
 */
public interface Explorer {

	String getId();

	/**
	 * Register a instruction.
	 *
	 * @param instructions
	 */
	Explorer registerInstructions(final Collection<InstructionAction> instructions);

	/**
	 * Execute all registered instructions.
	 *
	 * @param surface the surface
	 * @return a Collection of ExecutionResult
	 */
	Collection<ExecutionResult> excuteInstructions(final Surface surface);

	/**
	 * Retrieve the Explorer Current Position.
	 *
	 * @return the current position
	 */
	ExplorerPosition getCurrentPosition();

	/**
	 * Get a Collection of registered instructions
	 *
	 * @return a Collection of InstructionAction
	 */
	Collection<InstructionAction> getRegisteredInstructions();

	/**
	 * Get the collection of instruction execution.
	 *
	 * @return a Collection of ExecutionResult
	 */
	Collection<ExecutionResult> getExecutionResults();
}

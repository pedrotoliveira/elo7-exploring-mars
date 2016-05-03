package br.com.elo7.mars.explorer.engine.domain.explorer;

import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import java.util.Collection;
import java.util.UUID;

/**
 * Explorer Interface
 * 
 * @author pedrotoliveira
 */
public interface Explorer {
	
	UUID getId();	
	/**
	 * 
	 * @param instructions 
	 */
	void registerInstructions(final Collection<InstructionAction> instructions);
	
	/**
	 * 
	 * @param surface
	 * @return 
	 */
	Collection<ExecutionResult> excuteInstructions(final Surface surface);
	
	/**
	 * Retrieve the Explorer Current Position.
	 * @return 
	 */
	ExplorerPosition getCurrentPosition();
}

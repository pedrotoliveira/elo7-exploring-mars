package br.com.elo7.mars.explorer.engine.model;

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
	void registerInstructions(final Collection<Instruction> instructions);
	
	/**
	 * 
	 * @param surface
	 * @return 
	 */
	ExecutionResult excuteInstructions(final Surface surface);
	
	/**
	 * Retrieve the Explorer Current Position.
	 * @return 
	 */
	ExplorerPosition getCurrentPosition();
}

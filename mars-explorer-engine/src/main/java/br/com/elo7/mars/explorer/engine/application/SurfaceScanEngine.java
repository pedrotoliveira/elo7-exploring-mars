package br.com.elo7.mars.explorer.engine.application;

import br.com.elo7.mars.explorer.engine.domain.Factory;
import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import br.com.elo7.mars.explorer.engine.domain.explorer.Instruction;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * A Surface Scan Engine.
 * 
 * @author pedrotoliveira
 */
public class SurfaceScanEngine {
	
	private final Factory<Surface> surfaceFactory;
	private final Factory<Explorer> explorerFactory;
	private final Factory<Collection<Instruction>> instructionCollectionFactory;

	public SurfaceScanEngine(Factory<Surface> surfaceFactory, 
			Factory<Explorer> explorerFactory, 
			Factory<Collection<Instruction>> instructionCollectionFactory) {
		
		this.surfaceFactory = surfaceFactory;
		this.explorerFactory = explorerFactory;
		this.instructionCollectionFactory = instructionCollectionFactory;
	}
	
	/**
	 * Parse the inputs, process and generate results.
	 * 
	 * @param inputs
	 * @return 
	 */
	public Collection<String> process(Collection<String> inputs) {		
		Iterator<String> it = inputs.iterator();
		String createSurfaceInput = it.next();
		Surface surface = surfaceFactory.create(createSurfaceInput);
		
		List<Explorer> deployedExplorers = new ArrayList<>();		
		while(it.hasNext()) {
			String createExplorerInput = it.next();
			String createInstructionsInput = it.next();
			
			Explorer explorer = explorerFactory.create(createExplorerInput);
			Collection<Instruction> instructions = instructionCollectionFactory.create(createInstructionsInput);
			explorer.registerInstructions(instructions);			
			Explorer deployed = surface.deployExplorer(explorer);
			deployedExplorers.add(deployed);
		}
		
		for (Explorer explorer : deployedExplorers) {
			explorer.excuteInstructions(surface);
		}
		
		Collection<String> processResult = new ArrayList<>();
		for (ExplorerPosition position : surface.getExplorerPositions()) {			
			processResult.add(position.getFormmatedPosition());
		}
		return processResult;
	}
}

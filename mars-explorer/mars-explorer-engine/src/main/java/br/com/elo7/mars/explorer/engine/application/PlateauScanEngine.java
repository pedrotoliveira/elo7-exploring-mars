package br.com.elo7.mars.explorer.engine.application;

import br.com.elo7.mars.explorer.engine.domain.Factory;
import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.InstructionAction;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import org.apache.commons.lang.Validate;

/**
 * A Plateau Surface Scan Engine.
 *
 * @author pedrotoliveira
 */
public class PlateauScanEngine implements SurfaceScanEngine {

	private final Factory<Surface> surfaceFactory;
	private final Factory<Explorer> explorerFactory;
	private final Factory<Collection<InstructionAction>> instructionCollectionFactory;

	public PlateauScanEngine(Factory<Surface> surfaceFactory,
			Factory<Explorer> explorerFactory,
			Factory<Collection<InstructionAction>> instructionCollectionFactory) {
		this.surfaceFactory = surfaceFactory;
		this.explorerFactory = explorerFactory;
		this.instructionCollectionFactory = instructionCollectionFactory;
	}

	@Override
	public Collection<String> createSurfaceAndScan(Collection<String> inputs) {
		Validate.notEmpty(inputs, "Missing Inputs");
		Iterator<String> inputsIterator = inputs.iterator();
		Surface surface = createSurface(inputsIterator.next());
		return moveExplorers(createAndDeployExplorers(inputsIterator, surface));
	}

	private Surface createSurface(String surfaceInput) {
		Validate.notEmpty(surfaceInput, "Missing Surface Input");
		return surfaceFactory.create(surfaceInput);
	}

	private Surface createAndDeployExplorers(Iterator<String> inputsIterator, Surface surface) {
		Validate.isTrue(inputsIterator.hasNext(), "Missing Explorer Inputs");
		while (inputsIterator.hasNext()) {
			String createExplorerInput = inputsIterator.next();

			Validate.isTrue(inputsIterator.hasNext(), "Missing Instructions Input");
			String createInstructionsInput = inputsIterator.next();

			createAndDeployExplorer(createExplorerInput, createInstructionsInput, surface);
		}
		return surface;
	}

	private Explorer createAndDeployExplorer(String explorerInput, String instructionInput, Surface surface) {
		Explorer explorer = explorerFactory.create(explorerInput);
		explorer.registerInstructions(instructionCollectionFactory.create(instructionInput));
		return surface.deployExplorer(explorer);
	}

	private Collection<String> moveExplorers(Surface surface) {
		Collection<String> results = new ArrayList<>();
		surface.getDeployedExplorers().forEach(executeInstructionsAndFillResults(surface, results));
		return results;
	}

	public Consumer<Explorer> executeInstructionsAndFillResults(Surface surface, Collection<String> results) {
		return (explorer) -> {
			explorer.excuteInstructions(surface);
			results.add(explorer.getCurrentPosition().getFormmatedPosition());
		};
	}
}

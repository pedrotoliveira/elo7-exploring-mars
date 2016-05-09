package br.com.elo7.mars.explorer.engine.application;

import br.com.elo7.mars.explorer.engine.domain.Factory;
import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.InstructionAction;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import br.com.elo7.mars.explorer.engine.domain.surface.SurfaceRepository;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.function.Consumer;
import org.apache.commons.lang.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * A Plateau Surface Scan Engine.
 *
 * @author pedrotoliveira
 */
@Service
public class PlateauScanEngine implements SurfaceScanEngine {

	private final Factory<Surface> surfaceFactory;
	private final Factory<Explorer> explorerFactory;
	private final Factory<Collection<InstructionAction>> instructionCollectionFactory;
	private final SurfaceRepository surfaceRepository;

	@Autowired
	public PlateauScanEngine(Factory<Surface> surfaceFactory,
			Factory<Explorer> explorerFactory,
			Factory<Collection<InstructionAction>> instructionCollectionFactory,
			SurfaceRepository surfaceRepository) {
		this.surfaceFactory = surfaceFactory;
		this.explorerFactory = explorerFactory;
		this.instructionCollectionFactory = instructionCollectionFactory;
		this.surfaceRepository = surfaceRepository;
	}

	@Override
	@Transactional
	public Collection<String> createSurfaceAndScan(Collection<String> inputs) {
		Validate.notEmpty(inputs, "Missing Inputs");
		Iterator<String> inputsIterator = inputs.iterator();
		Surface surface = createSurface(inputsIterator.next());
		return moveExplorers(createAndDeployExplorers(inputsIterator, surface));
	}

	@Override
	@Transactional
	public Surface createSurface(String surfaceInput) {
		Validate.notEmpty(surfaceInput, "Missing Surface Input");
		Surface surface = surfaceFactory.create(surfaceInput);
		return surfaceRepository.save(surface);
	}

	@Override
	@Transactional
	public Surface deployExplorers(String surfaceId, Collection<String> explorerInputs) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	@Transactional
	public Collection<String> scan(String surfaceId) {
		throw new UnsupportedOperationException("Not supported yet.");
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
		surfaceRepository.save(surface);
		return results;
	}

	private Consumer<Explorer> executeInstructionsAndFillResults(Surface surface, Collection<String> results) {
		return (explorer) -> {
			explorer.excuteInstructions(surface);
			results.add(explorer.getCurrentPosition().getFormmatedPosition());
		};
	}
}

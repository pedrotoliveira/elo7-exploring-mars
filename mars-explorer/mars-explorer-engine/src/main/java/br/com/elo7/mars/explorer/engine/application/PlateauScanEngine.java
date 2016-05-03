package br.com.elo7.mars.explorer.engine.application;

import br.com.elo7.mars.explorer.engine.domain.Factory;
import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.Instruction;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import org.apache.commons.lang.Validate;

/**
 * A Plateau Surface Scan Engine.
 *
 * @author pedrotoliveira
 */
public class PlateauScanEngine implements SurfaceScanEngine {

    private final Factory<Surface> surfaceFactory;
    private final Factory<Explorer> explorerFactory;
    private final Factory<Collection<Instruction>> instructionCollectionFactory;

    public PlateauScanEngine(Factory<Surface> surfaceFactory,
            Factory<Explorer> explorerFactory,
            Factory<Collection<Instruction>> instructionCollectionFactory) {

        this.surfaceFactory = surfaceFactory;
        this.explorerFactory = explorerFactory;
        this.instructionCollectionFactory = instructionCollectionFactory;
    }

    @Override
    public Collection<String> createSurfaceAndScan(Collection<String> inputs) {
        Validate.notEmpty(inputs, "Missing Inputs");
        Iterator<String> inputsIterator = inputs.iterator();
        Surface surface = createSurface(inputsIterator.next());
        return moveExplorers(createAndDeployExplorers(inputsIterator, surface), surface);
    }

    private Surface createSurface(String createSurfaceInput) {
		Validate.notEmpty(createSurfaceInput, "Cannot Find Surface Input!");
        return surfaceFactory.create(createSurfaceInput);
    }

    private Collection<Explorer> createAndDeployExplorers(Iterator<String> inputsIterator, Surface surface) {
        Collection<Explorer> deployedExplorers = new ArrayList<>();
        Validate.isTrue(inputsIterator.hasNext(), "Missing Explorer Inputs");
        while (inputsIterator.hasNext()) {
            String createExplorerInput = inputsIterator.next();

            Validate.isTrue(inputsIterator.hasNext(), "Missing Instructions Input!");
            String createInstructionsInput = inputsIterator.next();

            deployedExplorers.add(createAndDeployExplorer(createExplorerInput, createInstructionsInput, surface));
        }
        return deployedExplorers;
    }

    private Explorer createAndDeployExplorer(String createExplorerInput, String createInstructionsInput, Surface surface) {
        Explorer explorer = explorerFactory.create(createExplorerInput);
        Collection<Instruction> instructions = instructionCollectionFactory.create(createInstructionsInput);
        explorer.registerInstructions(instructions);
        return surface.deployExplorer(explorer);
    }

    private Collection<String> moveExplorers(Collection<Explorer> deployedExplorers, Surface surface) {
        Collection<String> processResult = new ArrayList<>();
        deployedExplorers.forEach((explorer) -> {
            explorer.excuteInstructions(surface);
            processResult.add(explorer.getCurrentPosition().getFormmatedPosition());
        });
        return processResult;
    }
}

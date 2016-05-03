package br.com.elo7.mars.explorer.engine.application;

import br.com.elo7.mars.explorer.engine.domain.Factory;
import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.InstructionAction;
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
        return moveExplorers(createAndDeployExplorers(inputsIterator, surface), surface);
    }

    private Surface createSurface(String surfaceInput) {
		Validate.notEmpty(surfaceInput, "Missing Surface Input");
        return surfaceFactory.create(surfaceInput);
    }

    private Collection<Explorer> createAndDeployExplorers(Iterator<String> inputsIterator, Surface surface) {
        Collection<Explorer> deployedExplorers = new ArrayList<>();
        Validate.isTrue(inputsIterator.hasNext(), "Missing Explorer Inputs");
        while (inputsIterator.hasNext()) {
            String createExplorerInput = inputsIterator.next();

            Validate.isTrue(inputsIterator.hasNext(), "Missing Instructions Input");
            String createInstructionsInput = inputsIterator.next();

            deployedExplorers.add(createAndDeployExplorer(createExplorerInput, createInstructionsInput, surface));
        }
        return deployedExplorers;
    }

    private Explorer createAndDeployExplorer(String explorerInput, String instructionInput, Surface surface) {
        Explorer explorer = explorerFactory.create(explorerInput);
        explorer.registerInstructions(instructionCollectionFactory.create(instructionInput));        
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

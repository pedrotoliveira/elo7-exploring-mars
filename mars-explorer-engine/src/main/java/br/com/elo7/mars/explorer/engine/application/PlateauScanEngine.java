package br.com.elo7.mars.explorer.engine.application;

import br.com.elo7.mars.explorer.engine.domain.Factory;
import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.Instruction;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

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
        Iterator<String> it = inputs.iterator();
        Surface surface = createSurface(it.next());
        return moveExplorers(createAndDeployExplorers(it, surface), surface);
    }

    private Surface createSurface(String createSurfaceInput) {
        if (createSurfaceInput == null) {
            throw new IllegalArgumentException("Cannot Find Surface Input!");
        }
        return surfaceFactory.create(createSurfaceInput);
    }

    private Collection<Explorer> createAndDeployExplorers(Iterator<String> it, Surface surface) {
        Collection<Explorer> deployedExplorers = new ArrayList<>();
        while (it.hasNext()) {
            if (!it.hasNext()) {
                throw new IllegalArgumentException("Cannot Find Explorer Coordinations Input!");
            }
            String createExplorerInput = it.next();

            if (!it.hasNext()) {
                throw new IllegalArgumentException("Cannot Find Instructions Input!");
            }
            String createInstructionsInput = it.next();
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

package br.com.elo7.mars.explorer.engine.application;

import br.com.elo7.mars.explorer.engine.domain.Factory;
import br.com.elo7.mars.explorer.engine.domain.explorer.Explorer;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import br.com.elo7.mars.explorer.engine.domain.explorer.Instruction;
import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * A Surface Scan Engine.
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

    /**
     * Parse the inputs, process and generate results.
     *
     * <blockquote><pre>
     * Input Collection Example:
     * 5 5          // Surface Dimension
     * 1 2 N        // 1st Explorer Position
     * LMLMLMLMM    // 1st Explorer Instructions
     * 3 3 E        // 2st Explorer Position
     * MMRMMRMRRM   // 2st Exlorer Instructions
     * </pre></blockquote>
     *
     * <blockquote><pre>
     * Output Collection Example:
     * 1 3 N        // 1st Explorer Final Position
     * 5 1 E        // 2st Explorer Final Position
     * </pre></blockquote>
     *
     * @see
     * https://gist.github.com/elo7-developer/1a40c96a5d062b69f02c#entrada
     * @see
     * https://gist.github.com/elo7-developer/1a40c96a5d062b69f02c#sa%C3%ADda-esperada
     *
     * @param inputs a collection of raw input lines.     *
     * @return A Collection of <code>String</code> represents explorers coordinations
     */
    @Override
    public Collection<String> createSurfaceAndScan(Collection<String> inputs) {
        Iterator<String> it = inputs.iterator();
        Surface surface = createSurface(it);
        Collection<Explorer> deployedExplorers = createAndDeployExplorers(it, surface);
        return executeExplorersInstructions(deployedExplorers, surface);
    }

    private Surface createSurface(Iterator<String> it) {
        String createSurfaceInput = it.next();
        return surfaceFactory.create(createSurfaceInput);
    }

    private Collection<Explorer> createAndDeployExplorers(Iterator<String> it, Surface surface) {
        Collection<Explorer> deployedExplorers = new ArrayList<>();
        while (it.hasNext()) {
            String createExplorerInput = it.next();
            String createInstructionsInput = it.next();

            Explorer explorer = explorerFactory.create(createExplorerInput);
            Collection<Instruction> instructions = instructionCollectionFactory.create(createInstructionsInput);
            explorer.registerInstructions(instructions);
            Explorer deployed = surface.deployExplorer(explorer);
            deployedExplorers.add(deployed);
        }
        return deployedExplorers;
    }

    private Collection<String> executeExplorersInstructions(Collection<Explorer> deployedExplorers, Surface surface) {
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

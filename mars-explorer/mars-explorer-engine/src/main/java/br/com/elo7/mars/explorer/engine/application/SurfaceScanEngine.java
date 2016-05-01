package br.com.elo7.mars.explorer.engine.application;

import java.util.Collection;

/**
 * A Surface Scan Engine
 *
 * @author pedrotoliveira
 */
public interface SurfaceScanEngine {

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
    Collection<String> createSurfaceAndScan(Collection<String> inputs);

}

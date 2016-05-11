package br.com.elo7.mars.explorer.engine.application;

import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
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
     * @param inputs a collection of raw input lines.
     * @return A Collection of <code>String</code> represents explorers coordinations
     */
    Collection<String> createSurfaceAndScan(Collection<String> inputs);
	
	/**
	 * Create a Surface based on input.
	 * 
	 * @param surfaceInput
	 * @return a Created Surface.
	 */
	Surface createSurface(String surfaceInput);
	
	/**
	 * Delete a Surface
	 * 
	 * @param surfaceId the surface id (UUID)
	 */
	void deleteSurface(String surfaceId);
	
	/**
	 * Parse the inputs and create and deploys a collection of explorers in a surface.
	 * 
	 * <blockquote><pre>
	 * Explorer Id: 52e354da-f985-4aec-a111-c656291a0565
	 * 
     * Input Collection Example:
     * 1 2 N        // 1st Explorer Position
     * LMLMLMLMM    // 1st Explorer Instructions
     * 3 3 E        // 2st Explorer Position
     * MMRMMRMRRM   // 2st Exlorer Instructions
     * </pre></blockquote>
	 * 
	 * @param surfaceId the surface id.
	 * @param explorerInputs explorers inputs
	 * @return the Surface with deployed Explorers
	 */
	Surface deployExplorers(String surfaceId, Collection<String> explorerInputs);
	
	/**
	 * Scan a created surface with deployed explorers.
	 * 
	 * @param surfaceId the surface id.
	 * @return A Collection of <code>String</code> represents explorers coordinations
	 */
	Collection<String> scan(String surfaceId);
}

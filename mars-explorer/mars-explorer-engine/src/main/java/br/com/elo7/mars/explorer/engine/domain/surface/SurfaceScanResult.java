package br.com.elo7.mars.explorer.engine.domain.surface;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Results of a Surface Scan
 *
 * @author pedrotoliveira
 */
public enum SurfaceScanResult {

	OK("Area is Open"),
	COLLISION("Obstruction Detected"),
	OUT_OF_BOUNDARY("Area is Out Of Boundary");

	private final String message;

	private SurfaceScanResult(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}

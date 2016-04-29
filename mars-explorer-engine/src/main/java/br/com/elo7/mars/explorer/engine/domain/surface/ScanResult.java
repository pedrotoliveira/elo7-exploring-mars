package br.com.elo7.mars.explorer.engine.domain.surface;

/**
 * Results of a Surface Scan
 * 
 * @author pedrotoliveira
 */
public enum ScanResult {
	
	OK("Area is Open"),
	COLLISION("Obstruction Detected"),
	OUT_OF_BOUNDARY("Area is Out Of Boundary");
	
	private final String message;

	private ScanResult(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}

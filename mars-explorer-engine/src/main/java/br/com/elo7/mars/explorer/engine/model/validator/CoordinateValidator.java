package br.com.elo7.mars.explorer.engine.model.validator;

/**
 * Validate a X and Y Coordinate Pair
 *
 * @author pedortoliveira
 */
public final class CoordinateValidator {

	public static final String ERROR_MESSAGE = "X and Y Axis must be greater than 0";
	
	private CoordinateValidator() {		
	}
	
	public static void validate(int xAxis, int yAxis) {
		if (xAxis < 0 || yAxis < 0) {
			throw new IllegalArgumentException(ERROR_MESSAGE);
		}
	}
}

package br.com.elo7.mars.explorer.engine.domain.explorer;

/**
 * The Directions
 * 
 * @author pedrotoliveira
 */
public enum Direction {
	
	NORTH('N'), SOUTH('S'), EAST('E'), WEST('W');
	
	public static final String ERROR_MESSAGE = "Invalid direction: ";
	
	private final char direction;
	
	private Direction(char direction) {
		this.direction = direction;
	}
	
	public static Direction translate(char direction) {
		for (Direction d : Direction.values()) {
			if (d.getDirection() == direction) {
				return d;
			}
		}
		throw new IllegalArgumentException(ERROR_MESSAGE + direction);
	}

	protected char getDirection() {
		return direction;
	}
}

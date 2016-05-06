package br.com.elo7.mars.explorer.engine.domain.explorer;

/**
 * The Directions
 * 
 * @author pedrotoliveira
 */
public enum Direction {
	
	NORTH("N"), SOUTH("S"), EAST("E"), WEST("W");
	
	public static final String ERROR_MESSAGE = "Invalid direction: ";
	
	private final String direction;
	
	private Direction(String direction) {
		this.direction = direction;
	}
	
	public static Direction translate(String direction) {
		for (Direction d : Direction.values()) {
			if (d.getDirection().equals(direction)) {
				return d;
			}
		}
		throw new IllegalArgumentException(ERROR_MESSAGE + direction);
	}

	public String getDirection() {
		return direction;
	}

	@Override
	public String toString() {
		return "Direction[" + "direction=" + direction + ']';
	}
}

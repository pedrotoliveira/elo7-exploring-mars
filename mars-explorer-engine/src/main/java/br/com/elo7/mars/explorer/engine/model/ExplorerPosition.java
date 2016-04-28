package br.com.elo7.mars.explorer.engine.model;

/**
 *
 * @author pedrotoliveira
 */
final class ExplorerPosition {
	
	private final int xAxis;
	private final int yAxis;
	private final Direction direction;

	public ExplorerPosition(int xAxis, int yAxis, Direction direction) {
		this.xAxis = xAxis;
		this.yAxis = yAxis;
		this.direction = direction;
	}

	public int getxAxis() {
		return xAxis;
	}

	public int getyAxis() {
		return yAxis;
	}

	public Direction getDirection() {
		return direction;
	}
}

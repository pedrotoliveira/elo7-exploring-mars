package br.com.elo7.mars.explorer.engine.domain.explorer;

import java.util.Objects;

/**
 * Represents a Explorer Position in the surface
 * 
 * @author pedrotoliveira
 */
public class ExplorerPosition {
	
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
	
	public String getDirectionAsString() {
		return direction.getDirection();
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 47 * hash + this.xAxis;
		hash = 47 * hash + this.yAxis;
		hash = 47 * hash + Objects.hashCode(this.direction);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ExplorerPosition other = (ExplorerPosition) obj;
		if (this.xAxis != other.xAxis) {
			return false;
		}
		if (this.yAxis != other.yAxis) {
			return false;
		}
		if (this.direction != other.direction) {
			return false;
		}
		return true;
	}

	public String getFormmatedPosition() {
		return String.format("%d %d %s", getxAxis(), getyAxis(), getDirectionAsString());
	}
	
	@Override
	public String toString() {
		return "ExplorerPosition[" + "xAxis=" + xAxis + ", yAxis=" + yAxis + ", direction=" + direction + ']';
	}
}

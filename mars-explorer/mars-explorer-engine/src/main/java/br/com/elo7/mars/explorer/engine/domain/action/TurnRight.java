package br.com.elo7.mars.explorer.engine.domain.action;

import br.com.elo7.mars.explorer.engine.domain.explorer.Direction;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import org.apache.commons.lang.Validate;

/**
 * Turn the Explorer 90 degrees to right keeping current point.
 *
 * @author pedrotoliveira
 */
public final class TurnRight implements MoveAction {

	@Override
	public ExplorerPosition execute(ExplorerPosition currentPosition) {
		Validate.notNull(currentPosition, "CurrentPosition is Null");
		switch (currentPosition.getDirection()) {
			case NORTH:
				return new ExplorerPosition(currentPosition.getxAxis(), currentPosition.getyAxis(), Direction.EAST);
			case SOUTH:
				return new ExplorerPosition(currentPosition.getxAxis(), currentPosition.getyAxis(), Direction.WEST);
			case EAST:
				return new ExplorerPosition(currentPosition.getxAxis(), currentPosition.getyAxis(), Direction.SOUTH);
			case WEST:
				return new ExplorerPosition(currentPosition.getxAxis(), currentPosition.getyAxis(), Direction.NORTH);
			default:
				return currentPosition;
		}
	}
}

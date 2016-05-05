package br.com.elo7.mars.explorer.engine.domain.action;

import br.com.elo7.mars.explorer.engine.domain.explorer.Direction;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import org.apache.commons.lang.Validate;

/**
 * Turn the Explorer 90 degrees to left keeping current point.
 *
 * @author pedrotoliveira
 */
public final class TurnLeft implements MoveAction {

	@Override
	public ExplorerPosition execute(ExplorerPosition currentPosition) {
		Validate.notNull(currentPosition, "CurrentPosition is Null");
		switch(currentPosition.getDirection()) {
			case NORTH:
				return new ExplorerPosition(currentPosition.getxAxis(), currentPosition.getyAxis(), Direction.WEST);
			case SOUTH: 
				return  new ExplorerPosition(currentPosition.getxAxis(), currentPosition.getyAxis(), Direction.EAST);
			case EAST:
				return new ExplorerPosition(currentPosition.getxAxis(), currentPosition.getyAxis(), Direction.NORTH);
			case WEST:
				return new ExplorerPosition(currentPosition.getxAxis(), currentPosition.getyAxis(), Direction.SOUTH);
			default:
				return currentPosition;
		}
	}
}

package br.com.elo7.mars.explorer.engine.domain.action;

import br.com.elo7.mars.explorer.engine.domain.explorer.Direction;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;

/**
 *
 * @author pedrotoliveira
 */
public final class MoveFoward implements MoveAction {

	@Override
	public ExplorerPosition execute(ExplorerPosition currentPosition) {		
		switch (currentPosition.getDirection()) {
			case NORTH:
				return new ExplorerPosition(currentPosition.getxAxis(), currentPosition.getyAxis() + 1, Direction.NORTH);
			case SOUTH:
				return new ExplorerPosition(currentPosition.getxAxis(), currentPosition.getyAxis() - 1, Direction.SOUTH);
			case EAST:
				return new ExplorerPosition(currentPosition.getxAxis() + 1, currentPosition.getyAxis(), Direction.EAST);
			case WEST:
				return new ExplorerPosition(currentPosition.getxAxis() - 1, currentPosition.getyAxis(), Direction.WEST);
			default:
				return currentPosition;
		}
	}
}

package br.com.elo7.mars.explorer.engine.domain.explorer;

import br.com.elo7.mars.explorer.engine.domain.action.MoveAction;
import br.com.elo7.mars.explorer.engine.domain.action.MoveFoward;
import br.com.elo7.mars.explorer.engine.domain.action.TurnLeft;
import br.com.elo7.mars.explorer.engine.domain.action.TurnRight;
import br.com.elo7.mars.explorer.engine.domain.surface.SurfaceScanResult;
import org.apache.commons.lang.Validate;

/**
 * Explorer Instructions
 *
 * @author pedrotoliveira
 */
enum Instruction implements InstructionAction {

	TURN_LEFT("L", new TurnLeft()),
	TURN_RIGHT("R", new TurnRight()),
	MOVE_FOWARD("M", new MoveFoward());

	public static final String ERROR_MESSAGE = "Invalid Instruction: ";

	private final String representation;
	private final MoveAction moveAction;

	private Instruction(String instruction, MoveAction moveAction) {
		this.representation = instruction;
		this.moveAction = moveAction;
	}

	public static InstructionAction translate(String representation) {
		for (Instruction instruction : values()) {
			if (instruction.getRepresentation().equals(representation)) {
				return instruction;
			}
		}
		throw new IllegalArgumentException(ERROR_MESSAGE + representation);
	}

	public String getRepresentation() {
		return representation;
	}

	public MoveAction getMoveAction() {
		return moveAction;
	}

	@Override
	public ExecutionResult execute(ExplorerPosition currentPosition, SurfaceScanResult scanResult) {
		Validate.notNull(scanResult, "Provide a Surface Scan Result");
		switch (scanResult) {
			case COLLISION:
				return failedExecutionResult(currentPosition, scanResult);
			case OUT_OF_BOUNDARY:
				return failedExecutionResult(currentPosition, scanResult);				
			case OK:
				return new ExecutionResult(this, true, currentPosition, moveAction.execute(currentPosition));
			default:
				return new ExecutionResult(this, true, currentPosition, currentPosition).addNotification("Nothing Done");
		}
	}

	private ExecutionResult failedExecutionResult(ExplorerPosition currentPosition, SurfaceScanResult scanResult) {
		return new ExecutionResult(this, false, currentPosition, currentPosition).addNotification(scanResult.getMessage());
	}
}

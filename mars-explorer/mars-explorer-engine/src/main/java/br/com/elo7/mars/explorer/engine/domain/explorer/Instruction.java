package br.com.elo7.mars.explorer.engine.domain.explorer;

import br.com.elo7.mars.explorer.engine.domain.action.MoveAction;
import br.com.elo7.mars.explorer.engine.domain.action.MoveFoward;
import br.com.elo7.mars.explorer.engine.domain.action.TurnLeft;
import br.com.elo7.mars.explorer.engine.domain.action.TurnRight;

/**
 * Explorer Instructions
 *
 * @author pedrotoliveira
 */
public enum Instruction {

	TURN_LEFT('L', new TurnLeft()), TURN_RIGHT('R', new TurnRight()), MOVE_FOWARD('M', new MoveFoward());

	public static final String ERROR_MESSAGE = "Invalid Instruction: ";

	private final char instruction;
	private final MoveAction action;

	private Instruction(char instruction, MoveAction action) {
		this.instruction = instruction;
		this.action = action;
	}

	public static Instruction translate(char instruction) {
		for (Instruction i : values()) {
			if (i.getInstruction() == instruction) {
				return i;
			}
		}
		throw new IllegalArgumentException(ERROR_MESSAGE + instruction);
	}

	public ExecutionResult execute(ExplorerPosition currentPosition) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	protected char getInstruction() {
		return instruction;
	}

    public MoveAction getAction() {
        return action;
    }
}

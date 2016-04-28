package br.com.elo7.mars.explorer.engine.model;

/**
 * Explorer Instructions
 * 
 * @author pedrotoliveira
 */
public enum Instruction {
	
	TURN_LEFT('L'), TURN_RIGHT('R'), MOVE_FOWARD('M');
	
	public static final String ERROR_MESSAGE = "Invalid Instruction: ";
	
	private final char instruction;

	private Instruction(char instruction) {
		this.instruction = instruction;
	}
	
	public static Instruction translate(char instruction) {
		for (Instruction i : values()) {
			if (i.getInstruction() == instruction) {
				return i;
			}
		}
		throw new IllegalArgumentException(ERROR_MESSAGE + instruction);
	}
	
	protected char getInstruction() {
		return instruction;
	}
}

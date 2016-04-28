package br.com.elo7.mars.explorer.engine.model;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 *
 * @author pedrotoliveira
 */
@RunWith(value = Parameterized.class)
public class InstructionTest {
	
	private final char instructionChar;
	private final Instruction expected;

	public InstructionTest(char instructionChar, Instruction expected) {
		this.instructionChar = instructionChar;
		this.expected = expected;
	}
			
	@Parameterized.Parameters(name = "Test Case {index}:")
	public static Collection<Object[]> testData() {
		return Arrays.asList(new Object[][]{
			{'M', Instruction.MOVE_FOWARD},
			{'L', Instruction.TURN_LEFT},
			{'R', Instruction.TURN_RIGHT}			
		});
	}
	

	@Test
	public void testTranslate() {
		assertThat(Instruction.translate(instructionChar), equalTo(expected));
	}
}

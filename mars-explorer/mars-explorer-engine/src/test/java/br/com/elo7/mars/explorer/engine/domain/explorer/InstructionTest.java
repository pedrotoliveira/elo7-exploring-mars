package br.com.elo7.mars.explorer.engine.domain.explorer;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import br.com.elo7.mars.explorer.engine.domain.action.MoveAction;
import br.com.elo7.mars.explorer.engine.domain.action.MoveFoward;
import br.com.elo7.mars.explorer.engine.domain.action.TurnLeft;
import br.com.elo7.mars.explorer.engine.domain.action.TurnRight;

/**
 *
 * @author pedrotoliveira
 */
@RunWith(value = Parameterized.class)
public class InstructionTest {

	private final char instructionChar;
	private final Instruction expected;
    private final MoveAction expectedAction;

	public InstructionTest(char instructionChar, Instruction expected, MoveAction expectedAction) {
		this.instructionChar = instructionChar;
		this.expected = expected;
        this.expectedAction = expectedAction;
	}

	@Parameterized.Parameters(name = "Test Case {index}:")
	public static Collection<Object[]> testData() {
		return Arrays.asList(new Object[][]{
			{'M', Instruction.MOVE_FOWARD, new MoveFoward()},
			{'L', Instruction.TURN_LEFT, new TurnLeft()},
			{'R', Instruction.TURN_RIGHT, new TurnRight()}
		});
	}


	@Test
	public void testTranslate() {
		assertThat(Instruction.translate(instructionChar), equalTo(expected));
	}

    @Test
	public void testAction() {
		assertThat(Instruction.translate(instructionChar).getAction().getClass(), equalTo(expectedAction.getClass()));
	}
}

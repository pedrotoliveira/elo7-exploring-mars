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
import br.com.elo7.mars.explorer.engine.domain.surface.SurfaceScanResult;

/**
 * Instruction UnitTests
 * 
 * @author pedrotoliveira
 */
@RunWith(value = Parameterized.class)
public class InstructionTest {

	private static final ExplorerPosition startPosition = new ExplorerPosition(0, 0, Direction.NORTH);

	private final String representation;
	private final Instruction instruction;
	private final MoveAction expectedAction;
	private final ExecutionResult expectedResult;

	public InstructionTest(String representation,
			Instruction instruction,
			MoveAction expectedAction,
			ExecutionResult expectedResult) {
		this.representation = representation;
		this.instruction = instruction;
		this.expectedAction = expectedAction;
		this.expectedResult = expectedResult;
	}

	@Parameterized.Parameters(name = "Test Case {index}:")
	public static Collection<Object[]> testData() {
		return Arrays.asList(new Object[][]{
			{"M",
				Instruction.MOVE_FOWARD,
				new MoveFoward(),
				new ExecutionResult(Instruction.MOVE_FOWARD, true, startPosition, new ExplorerPosition(0, 1, Direction.NORTH))
			},
			{"L",
				Instruction.TURN_LEFT,
				new TurnLeft(),
				new ExecutionResult(Instruction.TURN_LEFT, true, startPosition, new ExplorerPosition(0, 0, Direction.WEST))
			},				
			{"R",
				Instruction.TURN_RIGHT,
				new TurnRight(),
				new ExecutionResult(Instruction.TURN_RIGHT, true, startPosition, new ExplorerPosition(0, 0, Direction.EAST))
			}
		});
	}

	@Test
	public void testTranslate() {
		assertThat(Instruction.translate(representation), equalTo(instruction));
	}

	@Test
	public void testAction() {
		assertThat(instruction.getMoveAction().getClass(), equalTo(expectedAction.getClass()));
	}
	
	@Test
	public void testExecute() {
		assertThat(instruction.execute(startPosition, SurfaceScanResult.OK), equalTo(expectedResult));
	}
}

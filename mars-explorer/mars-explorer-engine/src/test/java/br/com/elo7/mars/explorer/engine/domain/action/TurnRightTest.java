package br.com.elo7.mars.explorer.engine.domain.action;

import br.com.elo7.mars.explorer.engine.domain.explorer.Direction;
import br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * TurnRight Unit Tests
 *
 * @author pedrotoliveira
 */
@RunWith(value = Parameterized.class)
public class TurnRightTest {

	private final ExplorerPosition currentPosition;
	private final ExplorerPosition expectedPosition;

	public TurnRightTest(ExplorerPosition currentPosition, ExplorerPosition expectedPosition) {
		this.currentPosition = currentPosition;
		this.expectedPosition = expectedPosition;
	}

	@Parameterized.Parameters(name = "Test Case {index}:")
	public static Collection<Object[]> testData() {
		return Arrays.asList(new Object[][]{
			{new ExplorerPosition(2, 2, Direction.NORTH), new ExplorerPosition(2, 2, Direction.EAST)},
			{new ExplorerPosition(2, 2, Direction.SOUTH), new ExplorerPosition(2, 2, Direction.WEST)},
			{new ExplorerPosition(2, 2, Direction.EAST), new ExplorerPosition(2, 2, Direction.SOUTH)},
			{new ExplorerPosition(2, 2, Direction.WEST), new ExplorerPosition(2, 2, Direction.NORTH)}
		});
	}

	@Test
	public void testExecute() {
		assertThat(new TurnRight().execute(currentPosition), equalTo(expectedPosition));
	}

}

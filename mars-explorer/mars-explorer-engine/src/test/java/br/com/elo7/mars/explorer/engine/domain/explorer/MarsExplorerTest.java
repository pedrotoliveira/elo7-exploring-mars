package br.com.elo7.mars.explorer.engine.domain.explorer;

import br.com.elo7.mars.explorer.engine.domain.surface.Surface;
import br.com.elo7.mars.explorer.engine.domain.surface.SurfaceScanResult;
import br.com.elo7.mars.explorer.engine.test.FixtureTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import org.junit.Test;

import static br.com.elo7.mars.explorer.engine.domain.explorer.ExplorerPosition.copyFrom;
import static java.util.UUID.randomUUID;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Mars Explorer Unit Tests
 *
 * @author pedrotoliveira
 */
public class MarsExplorerTest extends FixtureTest {

	private final InstructionCollectionFactory instructionFactory = new InstructionCollectionFactory();

	@Test
	public void testGetId() {
		UUID id = randomUUID();
		assertThat(validExplorer(id).getId(), equalTo(id.toString()));
	}

	private MarsExplorer validExplorer(UUID id) {
		return new MarsExplorer(id.toString(), randomInt(0, 100), randomInt(0, 100), Direction.NORTH);
	}

	@Test
	public void testGetCurrentPosition() {
		UUID id = randomUUID();
		int xAxis = randomInt(0, 100);
		int yAxis = randomInt(0, 100);
		Direction direction = Direction.EAST;
		Explorer explorer = new MarsExplorer(id.toString(), xAxis, yAxis, direction);
		assertThat(explorer.getCurrentPosition(), equalTo(new ExplorerPosition(xAxis, yAxis, direction)));
	}

	@Test
	public void testRegisterInstructions() {
		MarsExplorer explorer = validExplorer();
		Collection<InstructionAction> instructions1 = instructionFactory.create(validInput());
		Collection<InstructionAction> instructions2 = instructionFactory.create(validInput());

		Collection<InstructionAction> expected = new ArrayList<>();
		expected.addAll(instructions1);
		expected.addAll(instructions2);

		explorer.registerInstructions(instructions1).registerInstructions(instructions2);
		assertThat(explorer.getRegisteredInstructions(), equalTo(expected));
	}

	private MarsExplorer validExplorer() {
		return validExplorer(randomUUID());
	}

	private String validInput() {
		return regexValue("[MLR]{5}");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExcuteInstructionsNullSurface() {
		MarsExplorer explorer = validExplorer();
		try {
			explorer.excuteInstructions(null);
		} catch (Exception ex) {
			assertThat(ex.getMessage(), equalTo("Surface is Null"));
			throw ex;
		}
	}

	@Test
	public void testExcuteInstructions() {
		MarsExplorer explorer = validExplorer();
		Collection<InstructionAction> instructions = instructionFactory.create(validInput());
		explorer.registerInstructions(instructions);
				
		Surface surface = mock(Surface.class);		
		when(surface.scan(eq(explorer), any(ExplorerPosition.class))).thenReturn(SurfaceScanResult.OK);

		Collection<ExecutionResult> expectedResults = new ArrayList<>();
		ExplorerPosition currentPosition = copyFrom(explorer.getCurrentPosition());

		for (InstructionAction instructionAction : instructions) {
			ExecutionResult result = instructionAction.execute(currentPosition, SurfaceScanResult.OK);
			currentPosition = result.getFinalPosition();
			expectedResults.add(result);
		}
		
		assertThat(explorer.excuteInstructions(surface), equalTo(expectedResults));
		verify(surface, times(instructions.size())).scan(eq(explorer), any(ExplorerPosition.class));
	}
}

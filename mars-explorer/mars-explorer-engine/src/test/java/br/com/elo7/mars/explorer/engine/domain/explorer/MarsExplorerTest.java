package br.com.elo7.mars.explorer.engine.domain.explorer;

import br.com.elo7.mars.explorer.engine.test.FixtureTest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import org.junit.Test;

import static java.util.UUID.randomUUID;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 *
 * @author pedrotoliveira
 */
public class MarsExplorerTest extends FixtureTest {

	private InstructionCollectionFactory instructionFactory = new InstructionCollectionFactory();

	@Test
	public void testGetId() {
		UUID id = randomUUID();
		assertThat(validExplorer(id).getId(), equalTo(id));
	}

	private MarsExplorer validExplorer(UUID id) {
		return new MarsExplorer(id, randomInt(0, 100), randomInt(0, 100), Direction.NORTH);
	}

	@Test
	public void testGetCurrentPosition() {
		UUID id = randomUUID();
		int xAxis = randomInt(0, 100);
		int yAxis = randomInt(0, 100);
		Direction direction = Direction.EAST;
		Explorer explorer = new MarsExplorer(id, xAxis, yAxis, direction);
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
		return regexValue("[MLR]{2}");
	}

	@Test
	public void testExcuteInstructions() {
		fail("To Implement");
	}

}

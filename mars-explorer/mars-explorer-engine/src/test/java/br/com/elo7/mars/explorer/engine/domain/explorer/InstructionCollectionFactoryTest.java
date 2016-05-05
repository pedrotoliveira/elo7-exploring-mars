package br.com.elo7.mars.explorer.engine.domain.explorer;

import java.util.Collection;
import org.junit.Test;

import static org.junit.Assert.*;

import br.com.elo7.mars.explorer.engine.domain.Factory;
import br.com.elo7.mars.explorer.engine.test.FixtureTest;
import org.junit.Before;

import static org.hamcrest.CoreMatchers.is;

/**
 * InstructionCollectionFactory UnitTests
 *
 * @author pedrotoliveira
 */
public class InstructionCollectionFactoryTest extends FixtureTest {

	private Factory<Collection<InstructionAction>> factory;

	@Before
	public void setUp() {
		this.factory = new InstructionCollectionFactory();
	}

	@Test
	public void testCreate() {
		String input = validInput();
		Collection<InstructionAction> instructions = factory.create(input);
		assertNotNull(instructions);
		assertThat(instructions.isEmpty(), is(false));
	}

	private String validInput() {
		return regexValue("[MLR]{25}");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullInput() {
		factory.create(null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidParameters() {
		factory.create(invalidInput());
	}

	private String invalidInput() {
		return regexValue("[A-z]{25}");
	}
}

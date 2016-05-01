package br.com.elo7.mars.explorer.engine.domain.explorer;

import java.util.Collection;
import org.junit.Test;

import static org.junit.Assert.*;

import br.com.elo7.mars.explorer.engine.domain.Factory;
import br.com.elo7.mars.explorer.engine.test.FixtureTest;
import org.junit.Before;

/**
 * InstructionCollectionFactory UnitTests
 *
 * @author pedrotoliveira
 */
public class InstructionCollectionFactoryTest extends FixtureTest {

    private Factory<Collection<Instruction>> factory;

    @Before
    public void setUp() {
        this.factory = new InstructionCollectionFactory();
    }

	@Test
	public void testCreate() {
		System.out.println("create");
		String input = "";
		InstructionCollectionFactory instance = new InstructionCollectionFactory();
		Collection<Instruction> expResult = null;
		Collection<Instruction> result = instance.create(input);
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}

}

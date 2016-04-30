package br.com.elo7.mars.explorer.engine.domain.explorer;

import org.junit.Test;

import static org.junit.Assert.*;

import br.com.elo7.mars.explorer.engine.domain.Factory;
import br.com.elo7.mars.explorer.engine.test.FixtureTest;
import org.junit.Before;


/**
 *
 * @author pedrotoliveira
 */
public class ExplorerFactoryTest extends FixtureTest {

    private Factory<Explorer> factory;

    @Before
    public void setUp() {
        this.factory = new ExplorerFactory();
    }

    @Test
    public void testCreate() {
        String input = validInput();
        Explorer explorer = factory.create(input);
        assertNotNull(explorer);
    }

    private String validInput() {
        return String.format("%d %d %s", randomInt(0, 1000), randomInt(0, 1000), regexValue("[NEWS]"));
    }

    @Test(expected = NullPointerException.class)
    public void testNullInput() {
        factory.create(null);
    }
}

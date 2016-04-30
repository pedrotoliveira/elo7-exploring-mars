package br.com.elo7.mars.explorer.engine.domain.explorer;

import org.junit.Test;

import static org.junit.Assert.*;

import br.com.elo7.mars.explorer.engine.domain.Factory;
import br.com.elo7.mars.explorer.engine.test.FixtureTest;


/**
 *
 * @author pedrotoliveira
 */
public class ExplorerFactoryTest extends FixtureTest {

    private Factory<Explorer> factory;

    @Test
    public void testCreate() {
        String input = validInput();
        Explorer explorer = factory.create(input);
        assertNotNull(explorer);
    }

    private String validInput() {
        return String.format("%d %d", randomInt(0, 1000), randomInt(0, 1000));
    }
}

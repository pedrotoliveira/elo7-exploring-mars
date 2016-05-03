package br.com.elo7.mars.explorer.engine.domain.surface;

import org.junit.Test;

import static org.junit.Assert.*;

import br.com.elo7.mars.explorer.engine.domain.Factory;
import br.com.elo7.mars.explorer.engine.test.FixtureTest;
import br.com.six2six.fixturefactory.function.impl.RandomFunction;
import org.junit.Before;

/**
 * Surface Factory Tests
 *
 * @author pedrotoliveira
 */
public class SurfaceFactoryTest extends FixtureTest {

    private Factory<Surface> factory;

    @Before
    public void setUp() {
        factory = new SurfaceFactory();
    }

    @Test
    public void testCreate() {
        String input = validInput();
        Surface surface = factory.create(input);
        assertNotNull(surface);
    }

    private String validInput() {
        return String.format("%d %d", randomInt(0, 1000), randomInt(0, 1000));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullInput() {
        factory.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidArgument() {
        String input = invalidInput();
        factory.create(input);
    }

    private String invalidInput() {
        return new RandomFunction(String.class,
                new String[]{"2 s 1", "  5   5 ", "2 3 4", "q 1"}).generateValue();
    }
}

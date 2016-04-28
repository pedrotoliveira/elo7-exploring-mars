package br.com.elo7.mars.explorer.engine.model;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static br.com.elo7.mars.explorer.engine.model.Direction.translate;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 *
 * @author pedrotoliveira
 */
@RunWith(value = Parameterized.class)
public class DirectionTest {
	
	private final char directionChar;
	private final Direction expected;

	public DirectionTest(char directionChar, Direction expected) {
		this.expected = expected;
		this.directionChar = directionChar;
	}
	
	@Parameterized.Parameters(name = "Test Case {index}:")
	public static Collection<Object[]> testData() {
		return Arrays.asList(new Object[][]{
			{'N', Direction.NORTH},
			{'S', Direction.SOUTH},
			{'E', Direction.EAST},
			{'W', Direction.WEST}
		});
	}
	
	@Test
	public void testTranslate() {
		assertThat(Direction.translate(directionChar), equalTo(expected));
	}
}

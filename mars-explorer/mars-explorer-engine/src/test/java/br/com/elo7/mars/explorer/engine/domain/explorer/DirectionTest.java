package br.com.elo7.mars.explorer.engine.domain.explorer;

import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * Direction Unit Tests
 *
 * @author pedrotoliveira
 */
@RunWith(value = Parameterized.class)
public class DirectionTest {

	private final String directionChar;
	private final Direction expected;

	public DirectionTest(String directionChar, Direction expected) {
		this.expected = expected;
		this.directionChar = directionChar;
	}

	@Parameterized.Parameters(name = "Test Case {index}:")
	public static Collection<Object[]> testData() {
		return Arrays.asList(new Object[][]{
			{"N", Direction.NORTH},
			{"S", Direction.SOUTH},
			{"E", Direction.EAST},
			{"W", Direction.WEST}
		});
	}

	@Test
	public void testTranslate() {
		assertThat(Direction.translate(directionChar), equalTo(expected));
	}
}

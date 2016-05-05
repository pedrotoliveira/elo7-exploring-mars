package br.com.elo7.mars.explorer.engine.domain.explorer;

import br.com.elo7.mars.explorer.engine.test.FixtureTest;
import br.com.six2six.fixturefactory.function.impl.RandomFunction;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * ExplorerPosition Unit Tests
 * 
 * @author pedrotoliveira
 */
public class ExplorerPositionTest extends FixtureTest {
	
	@Test
	public void getFormmatedPosition() {
		int xAxis = randomInt(0, 100);
		int yAxis = randomInt(0, 100);
		Direction direction = new RandomFunction(Direction.class).generateValue();
		ExplorerPosition explorerPosition = new ExplorerPosition(xAxis, yAxis, direction);
		assertThat(explorerPosition.getFormmatedPosition(), equalTo(String.format("%d %d %s", xAxis, yAxis, direction.getDirection())));
	}
	
}

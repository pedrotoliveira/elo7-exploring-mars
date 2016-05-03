package br.com.elo7.mars.explorer.engine.domain.validator;

import br.com.elo7.mars.explorer.engine.test.FixtureTest;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * CoordinateValidator UnitTests
 * 
 * @author pedrotoliveira
 */
public class CoordinateValidatorTest extends FixtureTest {

	@Test
	public void testValidate() throws Exception {
		for (int i = 0; i < 100; i++) {
			CoordinateValidator.validate(randomInt(0, 100), randomInt(0, 100));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testValidateXAxis() throws Exception {
		try {
			CoordinateValidator.validate(randomInt(-100, -1), randomInt(-100, -1));
		} catch (Exception ex) {
			assertThat(ex.getMessage(), equalTo(CoordinateValidator.ERROR_MESSAGE));
			throw ex;
		}
	}
}

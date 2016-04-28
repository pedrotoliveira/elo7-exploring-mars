package br.com.elo7.mars.explorer.engine.model.validator;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 *
 * @author pedrotoliveira
 */
public class CoordinateValidatorTest {
	
	@Test
	public void testValidate() throws Exception {
		for (int i = 0; i < 100; i++) {
			CoordinateValidator.validate(0, 0);
			CoordinateValidator.validate(100 - i, i);
			CoordinateValidator.validate(i, 100 - i);
			return;
		}	
	}	
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidateXAxisFail() throws Exception {
		try {			
			CoordinateValidator.validate(-1, 0);
		} catch (Exception ex) {
			assertThat(ex.getMessage(), equalTo(CoordinateValidator.ERROR_MESSAGE));
			throw ex;
		}
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testValidateYAxisFail() throws Exception {
		try {			
			CoordinateValidator.validate(0, -1);
		} catch (Exception ex) {
			assertThat(ex.getMessage(), equalTo(CoordinateValidator.ERROR_MESSAGE));
			throw ex;
		}		
	}
}

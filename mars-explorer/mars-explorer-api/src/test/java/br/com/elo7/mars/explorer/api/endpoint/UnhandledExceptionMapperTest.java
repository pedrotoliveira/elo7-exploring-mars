package br.com.elo7.mars.explorer.api.endpoint;

import javax.ws.rs.core.Response;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 *
 * @author pedrotoliveira
 */
public class UnhandledExceptionMapperTest {
	
	public UnhandledExceptionMapperTest() {
	}

	@Test
	public void testToResponse() {
		System.out.println("toResponse");
		Exception exception = null;
		UnhandledExceptionMapper instance = new UnhandledExceptionMapper();
		Response expResult = null;
		Response result = instance.toResponse(exception);
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}
	
}

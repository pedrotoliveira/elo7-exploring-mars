package br.com.elo7.mars.explorer.api.endpoint;

import javax.ws.rs.core.Response;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Surfaces Endpoint Tests
 * 
 * @author pedrotoliveira
 */
public class SurfacesEndpointTest {
	
	public SurfacesEndpointTest() {
	}

	@Test
	public void testGet() {
		System.out.println("get");
		Boolean expand = null;
		Integer max = null;
		Integer page = null;
		SurfacesEndpoint instance = new SurfacesEndpoint();
		Response expResult = null;
		Response result = instance.get(expand, max, page);
		assertEquals(expResult, result);
		fail("The test case is a prototype.");
	}
	
}

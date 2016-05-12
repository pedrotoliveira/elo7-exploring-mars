package br.com.elo7.mars.explorer.api.endpoint;

import br.com.elo7.mars.explorer.api.resource.error.Message;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.junit.Before;
import org.junit.Test;

import static br.com.elo7.mars.explorer.api.resource.error.MessageType.Endpoint_Error;
import static br.com.elo7.mars.explorer.api.resource.error.MessageType.Internal_Architecture_Error;
import static br.com.elo7.mars.explorer.api.resource.error.MessageType.Parameter_Error;
import static br.com.elo7.mars.explorer.api.resource.error.MessageType.Unexpected_Error;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

/**
 * @author pedrotoliveira
 */
public class UnhandledExceptionMapperTest {
	
	private UnhandledExceptionMapper mapper;

	@Before
	public void setUp() {
		this.mapper = new UnhandledExceptionMapper();
	}

	@Test
	public void testBadRequest() {
		Exception ex = new IllegalArgumentException("Validation Error");
		assertThat(mapper.toResponse(ex).getEntity(), equalTo(badRequest(ex).getEntity()));
	}
	
	private Response badRequest(Exception ex) {
		Message message = new Message(Parameter_Error, BAD_REQUEST);
		message.addNotification(ex.getMessage());
		return Response.status(BAD_REQUEST).type(MediaType.APPLICATION_JSON).entity(message).build();
	}
	
	@Test
	public void testNotFound() {
		Exception ex = new NotFoundException("Not Found");
		assertThat(mapper.toResponse(ex).getEntity(), equalTo(notFound(ex).getEntity()));
	}

	private Response notFound(Exception ex) {
		Message message = new Message(Endpoint_Error, NOT_FOUND);
		message.addNotification(ex.getMessage());
		return Response.status(NOT_FOUND).type(MediaType.APPLICATION_JSON).entity(message).build();
	}
	
	@Test
	public void testInternalArchtectureError() {
		Exception ex = new RuntimeException("Some Framework/Implementation Error");
		assertThat(mapper.toResponse(ex).getEntity(), equalTo(internalArchtectureError(ex).getEntity()));
	}

	private Response internalArchtectureError(Exception ex) {
		Message message = new Message(Internal_Architecture_Error, INTERNAL_SERVER_ERROR);
		message.addNotification(ex.getMessage());
		return Response.status(INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).entity(message).build();
	}
	
	@Test
	public void testUnexpectedError() {
		Exception ex = new Exception("Big Exception");
		assertThat(mapper.toResponse(ex).getEntity(), equalTo(unexpectedError(ex).getEntity()));
	}

	private Response unexpectedError(Exception ex) {
		Message message = new Message(Unexpected_Error, INTERNAL_SERVER_ERROR);
		message.addNotification(ex.getMessage());
		return Response.status(INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).entity(message).build();
	}
}

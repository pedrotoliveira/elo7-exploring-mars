package br.com.elo7.mars.explorer.api.endpoint;

import br.com.elo7.mars.explorer.api.resource.error.Message;
import javax.validation.ValidationException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import static br.com.elo7.mars.explorer.api.resource.error.MessageType.Endpoint_Error;
import static br.com.elo7.mars.explorer.api.resource.error.MessageType.Internal_Architecture_Error;
import static br.com.elo7.mars.explorer.api.resource.error.MessageType.Parameter_Error;
import static br.com.elo7.mars.explorer.api.resource.error.MessageType.Unexpected_Error;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * Map a Unhandled Exception to some error message.
 *
 * @author pedrotoliveira
 */
@Component
@Provider
public class UnhandledExceptionMapper implements ExceptionMapper<Exception> {

	private static final Logger logger = LoggerFactory.getLogger(UnhandledExceptionMapper.class);

	@Override
	public Response toResponse(Exception ex) {		
		if (ex instanceof IllegalArgumentException
				|| ex instanceof ValidationException) {
			return badRequest(ex);
		} else if (ex instanceof NotFoundException) {
			return notFound(ex);
		} else if (ex instanceof RuntimeException) {
			return internalArchtectureError(ex);
		} else {
			return unexpectedError(ex);
		}
	}

	private Response badRequest(Exception ex) {
		Message message = new Message(Parameter_Error, BAD_REQUEST);
		message.addNotification(ex.getMessage());
		logError(message, ex);
		return Response.status(BAD_REQUEST).type(MediaType.APPLICATION_JSON).entity(message).build();
	}

	private Response notFound(Exception ex) {
		Message message = new Message(Endpoint_Error, NOT_FOUND);
		message.addNotification(ex.getMessage());
		logError(message, ex);
		return Response.status(NOT_FOUND).type(MediaType.APPLICATION_JSON).entity(message).build();
	}

	private Response internalArchtectureError(Exception ex) {
		Message message = new Message(Internal_Architecture_Error, INTERNAL_SERVER_ERROR);
		message.addNotification(ex.getMessage());
		logError(message, ex);
		return Response.status(INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).entity(message).build();
	}

	private Response unexpectedError(Exception ex) {
		Message message = new Message(Unexpected_Error, INTERNAL_SERVER_ERROR);
		message.addNotification(ex.getMessage());
		logError(message, ex);
		return Response.status(INTERNAL_SERVER_ERROR).type(MediaType.APPLICATION_JSON).entity(message).build();
	}

	private void logError(Message message, Exception ex) {
		logger.error(message.toString(), ex);
	}
}

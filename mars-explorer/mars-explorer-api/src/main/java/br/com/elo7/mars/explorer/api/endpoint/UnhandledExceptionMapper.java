package br.com.elo7.mars.explorer.api.endpoint;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
	public Response toResponse(Exception exception) {
		logger.info("Unexpected Exception", exception);
		return Response
				.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity("Oops! We Have A Problem.")
				.type(MediaType.APPLICATION_JSON)
				.build();
	}
}

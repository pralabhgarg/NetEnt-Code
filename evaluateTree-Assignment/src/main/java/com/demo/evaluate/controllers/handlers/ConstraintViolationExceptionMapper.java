package com.demo.evaluate.controllers.handlers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.spi.ExtendedExceptionMapper;

import com.demo.evaluate.domain.ErrorResponse;


/**
 * This class will generate a JSON response and a BAD_REQUEST (400) error for all the constraint violations.
 */
@Provider
public class ConstraintViolationExceptionMapper implements ExtendedExceptionMapper<ConstraintViolationException> {

	private static final List<MediaType> PREFERRED_CONTENT = new ArrayList<>();

    static {
        PREFERRED_CONTENT.add(MediaType.APPLICATION_JSON_TYPE);
        PREFERRED_CONTENT.add(MediaType.APPLICATION_XML_TYPE);
    }

    private static final MediaType DEFAULT_CONTENT = MediaType.APPLICATION_JSON_TYPE;

    /**
     * This annotation is used to inject information into a class field, bean
     * property or method parameter.
     */
    @Context
    private HttpHeaders headers;

    @Context
    private UriInfo uriInfo;
    
    
	
	/**
     * Find a decent MediaType for the response based on the request header.
     *
     * @return MediaType
     */
    private MediaType getAcceptType() {
        final List<MediaType> accepts = this.headers.getAcceptableMediaTypes();
        if (accepts != null && !accepts.isEmpty()) {
            for (final MediaType mediaType : accepts) {
                if (PREFERRED_CONTENT.contains(mediaType)) {
                    return mediaType;
                }
            }
        }

        return DEFAULT_CONTENT;
    }

	@Override
	public boolean isMappable(ConstraintViolationException exception) {
		return true;
	}

	@Override
	public Response toResponse(ConstraintViolationException exception) {
		StringBuilder msg=new StringBuilder();
		for (@SuppressWarnings("rawtypes") ConstraintViolation cv: exception.getConstraintViolations()) {
			msg.append(cv.getMessage()).append(". ");
		}
		
		return Response.status(Response.Status.BAD_REQUEST)
                .entity(new ErrorResponse(Response.Status.BAD_REQUEST.getStatusCode(),
                        Response.Status.BAD_REQUEST.getReasonPhrase(),
                        msg.toString(), this.uriInfo.getPath()))
                .type(this.getAcceptType()).build();
	}
    
    
}

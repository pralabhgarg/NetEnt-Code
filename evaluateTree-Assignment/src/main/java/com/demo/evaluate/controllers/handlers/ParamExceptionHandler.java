package com.demo.evaluate.controllers.handlers;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.server.ParamException;
import org.glassfish.jersey.spi.ExtendedExceptionMapper;

import com.demo.evaluate.domain.ErrorResponse;

/**
 * Custom handler for NotFoundException (404) - Overriding the default support so that it can handle null
 * parameter values. In case there is no path parameter, this exception handler will raise 
 * a 404 -Not Found exception
 * Handler for 400 error code - parameter name doesn't match
 * 
 */

@Provider
public class ParamExceptionHandler implements ExtendedExceptionMapper<ParamException.PathParamException> {
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

    public ParamExceptionHandler() {
        super();
    }

    /**
     * Build our own response using a response type that matches something from
     * the request header.(non-Javadoc)
     * 
     * @see javax.ws.rs.ext.ExceptionMapper#toResponse(java.lang.Throwable)
     * @return Response
     */
    @Override
    public Response toResponse(ParamException.PathParamException exception) {
        // If we have the Parameter name that fails, build a 400 - Bad request
        // response
        if (exception.getParameterName() != null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(new ErrorResponse(Response.Status.BAD_REQUEST.getStatusCode(),
                            Response.Status.BAD_REQUEST.getReasonPhrase(),
                            "Invalid parameter " + exception.getParameterName(), this.uriInfo.getPath()))
                    .type(this.getAcceptType()).build();
        }
        // no path parameter, so create a general 404 not found.
        return Response.status(Response.Status.NOT_FOUND)
                .entity(new ErrorResponse(Response.Status.NOT_FOUND.getStatusCode(),
                        Response.Status.NOT_FOUND.getReasonPhrase(),
                        "Invalid parameter " + exception.getParameterName(), this.uriInfo.getPath()))
                .type(this.getAcceptType()).build();
    }

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
    public boolean isMappable(ParamException.PathParamException exception) {
        return true;
    }
}

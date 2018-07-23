package com.demo.evaluate.controllers.handlers;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import org.glassfish.jersey.spi.ExtendedExceptionMapper;

import com.demo.evaluate.domain.ErrorResponse;

/**
 * Custom handler for NotFoundException (404) - Overriding the default support so that it can handle XML and JSON
 * responses without generating a 406 because it's trying to send back a JSON response when the caller indicates it
 * only accepts XML.
 * 
 * Handler for 405 error code - request method is not supported for the requested resource. Only Get supported.
 * 
 */

@Provider
public class NotFoundExceptionHandler implements ExtendedExceptionMapper<WebApplicationException> {
    private static final List<MediaType> PREFERRED_CONTENT = new ArrayList<>();

    // set the media types that are acceptable
    static {
        PREFERRED_CONTENT.add(MediaType.APPLICATION_JSON_TYPE);
        PREFERRED_CONTENT.add(MediaType.APPLICATION_XML_TYPE);
    }

    private static final MediaType DEFAULT_CONTENT = MediaType.APPLICATION_JSON_TYPE;

    /*
     * This annotation is used to inject information into a class field, bean
     * property or method parameter.
     */
    @Context
    private HttpHeaders headers;

    @Context
    private UriInfo uriInfo;

    public NotFoundExceptionHandler() {
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
    public Response toResponse(WebApplicationException exception) {
        final Response.StatusType status = exception.getResponse().getStatusInfo();
        return Response
                .status(status.getStatusCode()).entity(new ErrorResponse(status.getStatusCode(),
                        status.getReasonPhrase(), exception.getMessage(), this.uriInfo.getPath()))
                .type(this.getAcceptType()).build();
    }

    /**
     * Try to find an acceptable "Accept" value from the request header for our
     * response.
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
    public boolean isMappable(WebApplicationException exception) {
        return true;
    }
}

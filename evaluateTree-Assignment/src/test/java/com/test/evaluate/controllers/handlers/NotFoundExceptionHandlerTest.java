package com.test.evaluate.controllers.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.demo.evaluate.controllers.handlers.NotFoundExceptionHandler;
import com.demo.evaluate.domain.ErrorResponse;


public class NotFoundExceptionHandlerTest {

	@Mock
	private HttpHeaders headers;
	
	@Mock
	private UriInfo uriInfo;
	
	@InjectMocks
	private NotFoundExceptionHandler notFoundExceptionHandler;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testToResponse() {
		WebApplicationException webApplicationException = new WebApplicationException("Not Found",404);
        Mockito.when(headers.getAcceptableMediaTypes()).thenReturn(null);
		Mockito.when(uriInfo.getPath()).thenReturn("/media-metadata/xyz");
		Response  response = notFoundExceptionHandler.toResponse(webApplicationException);
		Assert.assertNotNull("Response should not be null",response);
		Assert.assertEquals(404, response.getStatusInfo().getStatusCode());
		Assert.assertTrue(response.getEntity() instanceof ErrorResponse);
		ErrorResponse errorResponse = (ErrorResponse) response.getEntity();
		Assert.assertEquals("Not Found", errorResponse.getMessage());
	}
	
	@Test
	public void testToResponseWithEmptyMediaType() {
		WebApplicationException webApplicationException = new WebApplicationException("Not Found",404);
        Mockito.when(headers.getAcceptableMediaTypes()).thenReturn(Collections.emptyList());
		Mockito.when(uriInfo.getPath()).thenReturn("/media-metadata/xyz");
		Response  response = notFoundExceptionHandler.toResponse(webApplicationException);
		Assert.assertNotNull("Response should not be null",response);
		Assert.assertEquals(404, response.getStatusInfo().getStatusCode());
		Assert.assertTrue(response.getEntity() instanceof ErrorResponse);
		ErrorResponse errorResponse = (ErrorResponse) response.getEntity();
		Assert.assertEquals("Not Found", errorResponse.getMessage());
	}
	
	@Test
	public void testToResponseWithMediaType() {
		WebApplicationException webApplicationException = new WebApplicationException("Not Found",404);
        List<MediaType> mediaTypes = new ArrayList<>();
        MediaType mediaType = new MediaType("application","json");
        mediaTypes.add(mediaType);
        Mockito.when(headers.getAcceptableMediaTypes()).thenReturn(mediaTypes);
		Mockito.when(uriInfo.getPath()).thenReturn("/media-metadata/xyz");
		Response  response = notFoundExceptionHandler.toResponse(webApplicationException);
		Assert.assertNotNull("Response should not be null",response);
		Assert.assertEquals("Response should return 404 status",404, response.getStatusInfo().getStatusCode());
		Assert.assertTrue("Response Entity should be an instance of Error Response",response.getEntity() instanceof ErrorResponse);
		ErrorResponse errorResponse = (ErrorResponse) response.getEntity();
		Assert.assertEquals("Not Found", errorResponse.getMessage());
	}
	
	@Test
	public void testToResponseWithDefaultMediaType() {
		WebApplicationException webApplicationException = new WebApplicationException("Not Found",404);
        List<MediaType> mediaTypes = new ArrayList<>();
        MediaType mediaType = new MediaType();
        mediaTypes.add(mediaType);
        Mockito.when(headers.getAcceptableMediaTypes()).thenReturn(mediaTypes);
		Mockito.when(uriInfo.getPath()).thenReturn("/media-metadata/xyz");
		Response  response = notFoundExceptionHandler.toResponse(webApplicationException);
		Assert.assertNotNull("Response should not be null",response);
		Assert.assertEquals("Response should return 404 status",404, response.getStatusInfo().getStatusCode());
		Assert.assertTrue("Response Entity should be an instance of Error Response",response.getEntity() instanceof ErrorResponse);
		ErrorResponse errorResponse = (ErrorResponse) response.getEntity();
		Assert.assertEquals("Not Found", errorResponse.getMessage());
	}
	
	@Test
	public void testIsMappableWithNullException() {
		boolean result = notFoundExceptionHandler.isMappable(null);
		Assert.assertNotNull("Result should not be null",result);
		Assert.assertTrue("Result should not be empty",result);
	}
	
	@Test
	public void testIsMappableWithException() {
		WebApplicationException webApplicationException = new WebApplicationException("Not Found",404);
		boolean result = notFoundExceptionHandler.isMappable(webApplicationException);
		Assert.assertNotNull("Result should not be null",result);
		Assert.assertTrue("Result should not be empty",result);
	}
	
	@Test(expected=NullPointerException.class)
	public void testToResponseWithNullException() {
		notFoundExceptionHandler.toResponse(null);
	}
}

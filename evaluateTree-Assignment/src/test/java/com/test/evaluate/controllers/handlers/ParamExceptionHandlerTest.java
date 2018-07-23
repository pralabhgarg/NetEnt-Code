package com.test.evaluate.controllers.handlers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.server.ParamException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.demo.evaluate.controllers.handlers.ParamExceptionHandler;
import com.demo.evaluate.domain.ErrorResponse;

public class ParamExceptionHandlerTest {

	@Mock
	private HttpHeaders headers;
	
	@Mock
	private UriInfo uriInfo;
	
	@Mock
	private ParamException.PathParamException exception;
	
	@InjectMocks
	private ParamExceptionHandler paramExceptionHandler;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testToResponseWhenPathParamsIsNull() {
		List<MediaType> mediaTypes = new ArrayList<>();
        MediaType mediaType = new MediaType("application","json");
        mediaTypes.add(mediaType);
        Mockito.when(headers.getAcceptableMediaTypes()).thenReturn(mediaTypes);
		Mockito.when(uriInfo.getPath()).thenReturn("/media-metadata/xyz");
		Mockito.when(exception.getParameterName()).thenReturn(null);
		Response  response = paramExceptionHandler.toResponse(exception);
		Assert.assertNotNull("Response should not be null",response);
		Assert.assertEquals("Response should return 404 status",404, response.getStatusInfo().getStatusCode());
		Assert.assertTrue(response.getEntity() instanceof ErrorResponse);
	}
	
	@Test
	public void testToResponseWhenPathParamsAndMediaTypeAreNull() {
        Mockito.when(headers.getAcceptableMediaTypes()).thenReturn(null);
		Mockito.when(uriInfo.getPath()).thenReturn("/media-metadata/xyz");
		Mockito.when(exception.getParameterName()).thenReturn(null);
		Response  response = paramExceptionHandler.toResponse(exception);
		Assert.assertNotNull("Response should not be null",response);
		Assert.assertEquals("Response should return 404 status",404, response.getStatusInfo().getStatusCode());
		Assert.assertTrue(response.getEntity() instanceof ErrorResponse);
	}
	
	@Test
	public void testToResponseWhenPathParamsIsNullAndMediaTypeIsEmpty() {
        Mockito.when(headers.getAcceptableMediaTypes()).thenReturn(Collections.emptyList());
		Mockito.when(uriInfo.getPath()).thenReturn("/media-metadata/xyz");
		Mockito.when(exception.getParameterName()).thenReturn(null);
		Response  response = paramExceptionHandler.toResponse(exception);
		Assert.assertNotNull("Response should not be null",response);
		Assert.assertEquals("Response should return 404 status",404, response.getStatusInfo().getStatusCode());
		Assert.assertTrue(response.getEntity() instanceof ErrorResponse);
	}
	
	@Test
	public void testToResponseWhenMediaTypeIsDefault() {
		List<MediaType> mediaTypes = new ArrayList<>();
        MediaType mediaType = new MediaType();
        mediaTypes.add(mediaType);
        Mockito.when(headers.getAcceptableMediaTypes()).thenReturn(mediaTypes);
		Mockito.when(uriInfo.getPath()).thenReturn("/media-metadata/xyz");
		Mockito.when(exception.getParameterName()).thenReturn("Ccode");
		Response  response = paramExceptionHandler.toResponse(exception);
		Assert.assertNotNull("Response should not be null",response);
		Assert.assertEquals("Response should return 400 status",400, response.getStatusInfo().getStatusCode());
		Assert.assertTrue(response.getEntity() instanceof ErrorResponse);
	}
	
	@Test
	public void testToResponse() {
		List<MediaType> mediaTypes = new ArrayList<>();
        MediaType mediaType = new MediaType("application","json");
        mediaTypes.add(mediaType);
        Mockito.when(headers.getAcceptableMediaTypes()).thenReturn(mediaTypes);
		Mockito.when(uriInfo.getPath()).thenReturn("/media-metadata/xyz");
		Mockito.when(exception.getParameterName()).thenReturn("Ccode");
		Response  response = paramExceptionHandler.toResponse(exception);
		Assert.assertNotNull("Response should not be null",response);
		Assert.assertEquals("Response should return 400 status",400, response.getStatusInfo().getStatusCode());
		Assert.assertTrue(response.getEntity() instanceof ErrorResponse);
	}
	
	@Test
	public void testIsMappableWithNullException() {
		boolean result = paramExceptionHandler.isMappable(null);
		Assert.assertNotNull("Result should not be null",result);
		Assert.assertTrue("Result should not be empty",result);
	}
	
	@Test
	public void testIsMappableWithException() {
		boolean result = paramExceptionHandler.isMappable(exception);
		Assert.assertNotNull("Result should not be null",result);
		Assert.assertTrue("Result should not be empty",result);
	}
	
	@Test(expected=NullPointerException.class)
	public void testToResponseWithNullException() {
		paramExceptionHandler.toResponse(null);
	}
}

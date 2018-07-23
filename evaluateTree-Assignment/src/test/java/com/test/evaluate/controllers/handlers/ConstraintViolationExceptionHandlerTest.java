package com.test.evaluate.controllers.handlers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
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

import com.demo.evaluate.controllers.handlers.ConstraintViolationExceptionMapper;

public class ConstraintViolationExceptionHandlerTest {

	@Mock
	private ConstraintViolationException constraintViolationException ;
	
	@Mock
	private ConstraintViolation constraintViolation;
	
	@Mock
	private HttpHeaders headers;
	
	@Mock
	private UriInfo uriInfo;
	
	@InjectMocks
	private ConstraintViolationExceptionMapper constraintViolationExceptionHandler;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testIsMappableWithNullException() {
		boolean result = constraintViolationExceptionHandler.isMappable(null);
		Assert.assertNotNull("Result should not be null",result);
		Assert.assertTrue("Result should not be empty",result);
	}
	
	@Test
	public void testIsMappableWithException() {
		boolean result = constraintViolationExceptionHandler.isMappable(constraintViolationException);
		Assert.assertNotNull("Result should not be null",result);
		Assert.assertTrue("Result should not be empty",result);
	}
	
	@Test(expected=NullPointerException.class)
	public void testToResponseWithNullException() {
		constraintViolationExceptionHandler.toResponse(null);
	}
	
	@Test
	public void testToResponseWithException() {
        List<MediaType> mediaTypes = new ArrayList<>();
        MediaType mediaType = new MediaType("application","json");
        mediaTypes.add(mediaType);
        Mockito.when(headers.getAcceptableMediaTypes()).thenReturn(mediaTypes);
		Set<ConstraintViolation<?>> exceptions = new HashSet<>();
		Mockito.when(constraintViolation.getMessage()).thenReturn("ConstraintViolation Exception.");
		Mockito.when(constraintViolation.getInvalidValue()).thenReturn("Invalid Value.");
		Mockito.when(uriInfo.getPath()).thenReturn("/media-metadata/xyz");
        exceptions.add(constraintViolation);
		Mockito.when(constraintViolationException.getConstraintViolations()).thenReturn(exceptions);
		Response  response = constraintViolationExceptionHandler.toResponse(constraintViolationException);
		Assert.assertNotNull("Response should not be null",response);
		Assert.assertEquals("Response should return 400 status",400, response.getStatusInfo().getStatusCode());
	}
	
	@Test
	public void testToResponseWithEmptyException() {
		Set<ConstraintViolation<?>> exceptions = new HashSet<>();
		Mockito.when(uriInfo.getPath()).thenReturn("/media-metadata/xyz");
        exceptions.add(constraintViolation);
		Mockito.when(constraintViolationException.getConstraintViolations()).thenReturn(Collections.emptySet());
        List<MediaType> mediaTypes = new ArrayList<>();
        MediaType mediaType = new MediaType();
        mediaTypes.add(mediaType);
        Mockito.when(headers.getAcceptableMediaTypes()).thenReturn(mediaTypes);
		Response  response = constraintViolationExceptionHandler.toResponse(constraintViolationException);
		Assert.assertNotNull("Response should not be null",response);
		Assert.assertEquals("Response should return 400 status",400, response.getStatusInfo().getStatusCode());
	}
	
	@Test
	public void testToResponseWithEmptyMediaTypesException() {
		Set<ConstraintViolation<?>> exceptions = new HashSet<>();
		Mockito.when(uriInfo.getPath()).thenReturn("/media-metadata/xyz");
        exceptions.add(constraintViolation);
		Mockito.when(constraintViolationException.getConstraintViolations()).thenReturn(Collections.emptySet());
        List<MediaType> mediaTypes = new ArrayList<>();
        MediaType mediaType = new MediaType();
        mediaTypes.add(mediaType);
        Mockito.when(headers.getAcceptableMediaTypes()).thenReturn(Collections.emptyList());
		Response  response = constraintViolationExceptionHandler.toResponse(constraintViolationException);
		Assert.assertNotNull("Response should not be null",response);
		Assert.assertEquals("Response should return 400 status",400, response.getStatusInfo().getStatusCode());
	}
	
	@Test
	public void testToResponseWithNullMediaTypesException() {
		Set<ConstraintViolation<?>> exceptions = new HashSet<>();
		Mockito.when(uriInfo.getPath()).thenReturn("/media-metadata/xyz");
        exceptions.add(constraintViolation);
		Mockito.when(constraintViolationException.getConstraintViolations()).thenReturn(Collections.emptySet());
        List<MediaType> mediaTypes = new ArrayList<>();
        MediaType mediaType = new MediaType();
        mediaTypes.add(mediaType);
        Mockito.when(headers.getAcceptableMediaTypes()).thenReturn(null);
		Response  response = constraintViolationExceptionHandler.toResponse(constraintViolationException);
		Assert.assertNotNull("Response should not be null",response);
		Assert.assertEquals("Response should return 400 status",400, response.getStatusInfo().getStatusCode());
	}
}

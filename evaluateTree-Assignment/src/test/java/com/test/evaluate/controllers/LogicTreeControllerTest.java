package com.test.evaluate.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.demo.evaluate.controllers.LogicTreeController;
import com.demo.evaluate.domain.LogicTree;
import com.demo.evaluate.service.LogicTreeService;

public class LogicTreeControllerTest {
	
	@Mock
	private LogicTreeService logicTreeService;
    private LogicTreeController logicTreeController;
    
    @Before
    public void setUp() {
    	MockitoAnnotations.initMocks(this);
    	logicTreeController = new LogicTreeController(logicTreeService);
    }

    @Test
    public void getLogicTreeByteArrayTest(){
    	
    	when(logicTreeService.findByCcodeAndLlpCodeAndLanguageCode(anyString(), anyString(), anyString()))
    	.thenReturn(getMockLogicTreeList());
    	
    	Response response = logicTreeController.getLogicTreeByteArray("EN", "CUC201501LXCL48B", "2DD");
    	assertNotNull("Exected a non null logic tree", response.getEntity());
    	assertEquals("Expected a 200 OK status", response.getStatus(), 200);
    }
    
    @Test
    public void getLogicTreeByteArrayNullResponseTest(){
    	
    	when(logicTreeService.findByCcodeAndLlpCodeAndLanguageCode(anyString(), anyString(), anyString()))
    	.thenReturn(null);
    	try{
    		logicTreeController.getLogicTreeByteArray("EN", "CUC201501LXCL48B", "2DD");
    	}catch(NotFoundException e){
    		assertNotNull("Exception should not be empty",e);
    	}
    }
    
    @Test
    public void testGetLogicTreeByteArrayWhenLogicTreeIsEmpty() {
    	when(logicTreeService.findByCcodeAndLlpCodeAndLanguageCode(anyString(), anyString(), anyString()))
    	.thenReturn(new ArrayList<>());
    	try{
    		logicTreeController.getLogicTreeByteArray("EN", "CUC201501LXCL48B", "2DD");
    	}catch(NotFoundException e){
    		assertNotNull("Exception should not be empty",e);
    		assertEquals("LogicTree not found.", e.getMessage());
    	}
    }
    
    private List<LogicTree> getMockLogicTreeList(){
    	List<LogicTree> logicTreeList = new ArrayList<>();
    	LogicTree logicTree = new LogicTree();
    	logicTree.setCode("CUC201501LXCL48B");
    	logicTree.setLanguageCode("EN");
    	logicTree.setPackCode("2DD");
    	logicTree.setLogicTreeUID(21987);
    	logicTree.setPack(2866);
    	
    	logicTreeList.add(logicTree);
    	return logicTreeList;
    }

}

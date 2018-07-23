package com.test.evaluate.service;

import static org.mockito.Mockito.when;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.demo.evaluate.domain.LogicTree;
import com.demo.evaluate.repository.LogicTreeRepository;
import com.demo.evaluate.service.LogicTreeService;
import com.demo.evaluate.service.LogicTreeServiceImpl;

/*
 * Test case for calss LogicTreeServiceImpl
 */
public class LogicTreeServiceImplTest {

    /**
     * Mock LogicTreeRepository
     */
    @Mock
    private LogicTreeRepository logicTreeRepository;

    @InjectMocks
    private LogicTreeService logicTreeService = new LogicTreeServiceImpl();;

    /**
     * Runs before every test present in the class
     */
    @Before
    public void init() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        MockitoAnnotations.initMocks(this);

        // set the LogicTreeServiceImpl.releaseLevel by reflection in logicTreeService
        Field f1 = LogicTreeServiceImpl.class.getDeclaredField("releaseLevel");
        f1.setAccessible(true);
        f1.set(logicTreeService, 40);

    }

    // create a logictree data list for mocked object
    private List<LogicTree> getLogicTree() {
        LogicTree logicTree = new LogicTree();
        logicTree.setCode("CUC201501LXCL48B");
        logicTree.setLanguageCode("EN");
        logicTree.setPackCode("2DD");
        logicTree.setLogicTreeUID(21987);
        logicTree.setPack(2866);
        List<LogicTree> catalogOptionLogicTrees = new ArrayList<>();
        catalogOptionLogicTrees.add(logicTree);
        return catalogOptionLogicTrees;
    }
    /**@param String dataMode
     * set the dataMode in logicTreeService, passed as parameter
     */

    private void mockObject(String dataMode) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
      //set datamode by reflection
        Field f2 = LogicTreeServiceImpl.class.getDeclaredField("dataMode");
        f2.setAccessible(true);
        f2.set(logicTreeService, dataMode);

    }
    /**
     *
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * Test when data mode is topmaster
     */
    @Test
    public void testFindByCcodeAndLlpCodeAndLanguageCode_WithTopMaster() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        mockObject("topmaster");
        when(this.logicTreeRepository.findByCodeAndPackAndLanguageCodeIgnoreCaseAndReleaseLevel(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyInt())).thenReturn(getLogicTree());
        List<LogicTree> result1 = logicTreeService.findByCcodeAndLlpCodeAndLanguageCode("CUC201501LXCL48B", "2DD", "EN");
        Assert.assertNotNull(result1);
        Assert.assertEquals(1, result1.size());
        Assert.assertEquals("CUC201501LXCL48B", result1.get(0).getCode());
    }

    /**
     *
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * Test when data mode is topmaster but logic tree retrun null for given CCode,llpCode,languageCode,releaseLevel
     */

    @Test
    public void testFindByCcodeAndLlpCodeAndLanguageCode_WithTopMasterNullResult() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        mockObject("topmaster");
        when(this.logicTreeRepository.findByCodeAndPackAndLanguageCodeIgnoreCaseAndReleaseLevel(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyInt())).thenReturn(null);
        List<LogicTree> result1 = logicTreeService.findByCcodeAndLlpCodeAndLanguageCode("CUC201501LXCL48B", "2DD", "EN");
        Assert.assertNull(result1);
    }

    /**
    *
    * @throws NoSuchFieldException
    * @throws IllegalArgumentException
    * @throws IllegalAccessException
    * Test when data mode is topmaster but logic tree retrun null for given CCode,llpCode,languageCode,releaseLevel
    */

   @Test
   public void testFindByCcodeAndLlpCodeAndLanguageCode_WithNulldataset() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
       mockObject(null);
       when(this.logicTreeRepository.findByCodeAndPackAndLanguageCodeIgnoreCase(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(null);
       List<LogicTree> result1 = logicTreeService.findByCcodeAndLlpCodeAndLanguageCode("CUC201501LXCL48B", "2DD", "EN");
       Assert.assertNull(result1);
   }

    /**
     *
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * Test when Active profile contains runtime
     */
    @Test
    public void testFindByCcodeAndLlpCodeAndLanguageCode_WithRuntime() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        mockObject("prod");
        when(this.logicTreeRepository.findByCodeAndPackAndLanguageCodeIgnoreCase(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(getLogicTree());
        List<LogicTree> result1 = logicTreeService.findByCcodeAndLlpCodeAndLanguageCode("CUC201501LXCL48B", "2DD", "EN");
        Assert.assertNotNull(result1);
        Assert.assertEquals(1, result1.size());
        Assert.assertEquals("2DD", result1.get(0).getPackCode());
    }

    /**
     *
     * @throws NoSuchFieldException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * Test when Active profile contains runtime but logic tree returns null for given CCode,llpCode,languageCode
     */
    @Test
    public void testFindByCcodeAndLlpCodeAndLanguageCode_WithRuntimeNullResult() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        mockObject("prod");
        when(this.logicTreeRepository.findByCodeAndPackAndLanguageCodeIgnoreCase(Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(null);
        List<LogicTree> result1 = logicTreeService.findByCcodeAndLlpCodeAndLanguageCode("CUC201501LXCL48B", "2DD", "EN");
        Assert.assertNull(result1);
    }
}

package com.test.evaluate.validator;

import org.junit.Test;

import com.demo.evaluate.controllers.validator.LanguageValidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;

/**
 * Test cases for {@link LanguageValidator}
 * Test language validator - ignore the ConstraintValidatorContext as we don't use it.
 */
public class LanguageValidatorTest {
    private LanguageValidator languageValidator = new LanguageValidator();

    @Before
    public void setup() {
        languageValidator.setLangCodeLength(2);
        languageValidator.setLangCodePattern("[A-Za-z]{2}");
    }
    
    @Test
    public void testNull() {
        assertFalse("Expected invalid for null value", languageValidator.isValid(null,null));
    }

    @Test
    public void testEmpty() {
        assertFalse("Expected invalid for empty string", languageValidator.isValid("", null));
    }

    @Test
    public void testShort() {
        assertFalse("Expected invalid for too short language code", languageValidator.isValid("E", null));
    }

    @Test
    public void testLong() {
        assertFalse("Expected invalid for too long language code", languageValidator.isValid("ENGLISH", null));
    }

    @Test
    public void testFrench() {
        assertTrue("Expected valid for fr", languageValidator.isValid("fr", null));
        assertTrue("Expected valid for FR", languageValidator.isValid("FR", null));
    }

    @Test
    public void testAlphanumeric() {
        assertFalse("Expected invalid for alphanumeric value", languageValidator.isValid("A1",null));
        assertFalse("Expected invalid for alphanumeric value", languageValidator.isValid("1A",null));
    }

    @Test
    public void testValid() {        
        assertTrue("Expected valid for EN", languageValidator.isValid("EN", null));
        assertTrue("Expected valid for En", languageValidator.isValid("En", null));
        assertTrue("Expected valid for eN", languageValidator.isValid("eN", null));
    }
}

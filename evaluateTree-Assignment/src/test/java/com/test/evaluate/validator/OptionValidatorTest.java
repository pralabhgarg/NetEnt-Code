package com.test.evaluate.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.demo.evaluate.controllers.validator.OptionValidator;

public class OptionValidatorTest {
	
	private OptionValidator optionValidator = new OptionValidator();



    @Before

    public void setup() {

    }



    @Test

    public void testEmpty() {

        assertTrue("Expected valid for empty option", optionValidator.isValid("", null));

    }



    @Test

    public void testInvalidOption() {

        assertFalse("Expected false for an option which is a list of comma seperated string",

                optionValidator.isValid("cvd,vcd", null));

    }


    @Test

    public void legitOption() {

        assertTrue("Expected valid for a string without comma.", optionValidator.isValid("hghagjagw", null));

    }

}

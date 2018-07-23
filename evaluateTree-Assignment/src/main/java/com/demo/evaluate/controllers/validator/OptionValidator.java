package com.demo.evaluate.controllers.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

/**
 * Validate that the Option provided is a single option and not a list.
 */
@Component
public class OptionValidator implements ConstraintValidator<Option, String> {

    /**
     * Generic constructor
     *
     * @param constraintAnnotation Option annotation
     */
    @Override
    public void initialize(Option constraintAnnotation) {
        // nothing required
    }

    /**
     * Check for validity.
     *
     * @param toValidate string to validate
     * @param context    ConstraintValidatorContext - not used
     * @return true if string is not a comma separated list.
     */
    @Override
    public boolean isValid(String toValidate, ConstraintValidatorContext context) {
        return toValidate.indexOf(',') <= -1;
    }
}

package com.demo.evaluate.controllers.validator;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Bean Validation class to validate an Language code.
 */
@Component
public class LanguageValidator implements ConstraintValidator<Language, String> {
    private int langCodeLength;
    private Pattern langCodePattern;
    
    /**
     * Setter for property 'langCodeLength'.
     * @param langCodeLength value to set for property 'langCodeLength'.
     */
    @Value("${catalogoption.validator.langCodeLength}")
    public void setLangCodeLength(int langCodeLength) {
        this.langCodeLength = langCodeLength;
    }
    
    /**
     * Setter for property 'langCodePattern'.
     *
     * @param langCodePattern Value to set for property 'langCodePattern'.
     */
    @Value("${catalogoption.validator.lang-code-pattern}")
    public void setLangCodePattern(String langCodePattern) {
        this.langCodePattern = Pattern.compile(langCodePattern, Pattern.UNICODE_CASE);
    }
        
    /**
     * Need to override, but we don't have anything that needs to be done
     *
     * @param language Language annotation.
     */
    @Override
    public void initialize(Language language) {
        // nothing required
    }

    /**
     * Check if the toValidate string is a valid language code.  Normalize to upper case.
     *
     * @param toValidate                 String to be validated
     * @param constraintValidatorContext ConstraintValidatorContext
     * @return true if valid.
     */
    @Override
    public boolean isValid(String toValidate, ConstraintValidatorContext constraintValidatorContext) {
        return null != toValidate
                && toValidate.length() == this.langCodeLength
                && this.langCodePattern.matcher(toValidate).matches();
    }
}

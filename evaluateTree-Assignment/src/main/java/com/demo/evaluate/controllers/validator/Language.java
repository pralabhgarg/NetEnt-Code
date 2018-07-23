package com.demo.evaluate.controllers.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Annotation to allow us to validate Language Code passed to REST APIs.
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LanguageValidator.class)
public @interface Language {
    /**
     * Message that is returned by default to the caller when the Language Code does not pass validation
     *
     * @return String
     */
    String message() default "Invalid or unsupported Language Code";

    /**
     * No specific grouping for this constraint
     *
     * @return empty set
     */
    Class<?>[] groups() default {};

    /**
     * No custom payloads for this constraint.
     *
     * @return empty set.
     */
    Class<? extends Payload>[] payload() default {};
}

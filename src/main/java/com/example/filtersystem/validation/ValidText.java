package com.example.filtersystem.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = TextValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidText {
    String message() default "Invalid title. Please use only letters, numbers, spaces, and basic punctuation.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

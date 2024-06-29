package com.example.filtersystem.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TextValidator  implements ConstraintValidator<ValidText, String> {

    private static final String TEXT_REGEX = "^[a-zA-Z0-9\\s.,-]*$";

    @Override
    public void initialize(ValidText constraintAnnotation) {
    }

    @Override
    public boolean isValid(String input, ConstraintValidatorContext context) {
        if (input == null) {
            return false;
        }
        return input.matches(TEXT_REGEX);
    }
}

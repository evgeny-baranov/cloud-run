package com.lp.web.dto.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.validator.routines.EmailValidator;

public class NullOrEmailValidator implements ConstraintValidator<NullOrEmail, String> {

    @Override
    public void initialize(NullOrEmail constraintAnnotation) {
    }

    @Override
    public boolean isValid(String emailField, ConstraintValidatorContext context) {
        if (emailField == null) {
            return true;
        }

        return EmailValidator.getInstance().isValid(emailField);
    }
}

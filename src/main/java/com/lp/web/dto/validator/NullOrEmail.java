package com.lp.web.dto.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = NullOrEmailValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface NullOrEmail {
    String message() default "NULL_OR_EMAIL";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

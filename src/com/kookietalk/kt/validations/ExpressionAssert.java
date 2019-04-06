package com.kookietalk.kt.validations;

import java.lang.annotation.*;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy=ExpressionAssertValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExpressionAssert {
    String message() default "expression must evaluate to true";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String value();
}
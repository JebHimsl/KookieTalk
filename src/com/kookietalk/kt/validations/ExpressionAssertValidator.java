package com.kookietalk.kt.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class ExpressionAssertValidator implements ConstraintValidator<ExpressionAssert, Object> {
    private Expression exp;

    public void initialize(ExpressionAssert annotation) {
        ExpressionParser parser = new SpelExpressionParser();
        exp = parser.parseExpression(annotation.value());
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {
        return exp.getValue(value, Boolean.class);
    }
}

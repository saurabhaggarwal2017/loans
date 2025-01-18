package com.eazybytes.loan.custom.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LoanIdValidatorImpl implements ConstraintValidator<ValidLoanId,Long> {
    @Override
    public boolean isValid(Long value, ConstraintValidatorContext context) {
        return Long.toString(value).length()==15;
    }
}

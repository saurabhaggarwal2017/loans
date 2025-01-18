package com.eazybytes.loan.custom.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.Size;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = LoanIdValidatorImpl.class)
public @interface ValidLoanId {
    String message() default "Loan id length exactly should be 15.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

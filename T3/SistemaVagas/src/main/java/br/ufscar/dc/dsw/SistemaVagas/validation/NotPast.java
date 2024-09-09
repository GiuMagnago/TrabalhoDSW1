package br.ufscar.dc.dsw.SistemaVagas.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotPastValidator.class)
public @interface NotPast {
    String message() default "The date cannot be in the past!";
    Class<?>[] groups() default {}; // Add this line
    Class<? extends Payload>[] payload() default {};
}

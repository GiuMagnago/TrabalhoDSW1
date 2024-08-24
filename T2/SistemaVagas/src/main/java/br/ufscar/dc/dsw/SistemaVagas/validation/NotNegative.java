package br.ufscar.dc.dsw.SistemaVagas.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotNegativeValidator.class)
public @interface NotNegative {
    String message() default "The value cannot be negative!";
    Class<?>[] groups() default {}; // Add this line
    Class<? extends Payload>[] payload() default {};
}

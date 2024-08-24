package br.ufscar.dc.dsw.SistemaVagas.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotFutureBornValidator.class)
public @interface NotFutureBorn {
    String message() default "The user must be at least 16 years old to be registered!";
    Class<?>[] groups() default {}; // Add this line
    Class<? extends Payload>[] payload() default {};
}

package br.ufscar.dc.dsw.SistemaVagas.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidator.class)
public @interface UniqueEmail {
    String message() default "There is already a registration with this e-mail!";
    Class<?>[] groups() default {}; // Add this line
    Class<? extends Payload>[] payload() default {};
}

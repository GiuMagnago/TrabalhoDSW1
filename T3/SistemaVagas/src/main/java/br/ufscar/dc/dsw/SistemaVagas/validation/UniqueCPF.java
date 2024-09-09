package br.ufscar.dc.dsw.SistemaVagas.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCPFValidator.class)
public @interface UniqueCPF {
    String message() default "There is already a registration with this CPF!";
    Class<?>[] groups() default {}; // Add this line
    Class<? extends Payload>[] payload() default {};
    long id() default 0L;
}

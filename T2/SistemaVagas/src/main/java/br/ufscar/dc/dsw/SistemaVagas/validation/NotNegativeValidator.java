package br.ufscar.dc.dsw.SistemaVagas.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotNegativeValidator implements ConstraintValidator<NotNegative, Double> {
    @Override
    public boolean isValid(Double value, ConstraintValidatorContext context) {
        return value >= 0;
    }
}

package br.ufscar.dc.dsw.SistemaVagas.validation;

import java.util.Date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotPastValidator implements ConstraintValidator<NotPast, Date> {
    @Override
    public boolean isValid(Date date, ConstraintValidatorContext context) {
        return date.after(new Date(System.currentTimeMillis()));
    }
}

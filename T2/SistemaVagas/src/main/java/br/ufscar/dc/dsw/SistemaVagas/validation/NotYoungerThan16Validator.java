package br.ufscar.dc.dsw.SistemaVagas.validation;

import java.util.Calendar;
import java.util.Date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotYoungerThan16Validator implements ConstraintValidator<NotYoungerThan16, Date> {
    @Override
    public boolean isValid(Date date, ConstraintValidatorContext context) {
        if (date != null) {
            Date now = new Date(System.currentTimeMillis());
            Calendar cal = Calendar.getInstance();
            cal.setTime(now);
            int actualYear = cal.get(Calendar.YEAR);
            cal.setTime(date);
            int birthYear = cal.get(Calendar.YEAR);
            return actualYear - birthYear >= 16; // anoAtual - anoNascimento >= 16 ?
        }
        return true;
    }
}

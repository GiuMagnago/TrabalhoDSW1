package br.ufscar.dc.dsw.SistemaVagas.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.SistemaVagas.dao.IProfissionalDAO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueCPFValidator implements ConstraintValidator<UniqueCPF, String> {
    @Autowired
    IProfissionalDAO dao;

    @Override
    public boolean isValid(String CPF, ConstraintValidatorContext context) {
        if (dao != null) {
            return dao.findByCpf(CPF) == null;
        }
        return true;
    }
}

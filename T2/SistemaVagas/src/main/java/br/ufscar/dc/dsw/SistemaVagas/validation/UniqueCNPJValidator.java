package br.ufscar.dc.dsw.SistemaVagas.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.SistemaVagas.dao.IEmpresaDAO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueCNPJValidator implements ConstraintValidator<UniqueCNPJ, String> {
    @Autowired
    IEmpresaDAO dao;
    
    @Override
    public boolean isValid(String CNPJ, ConstraintValidatorContext context) {
        if (dao != null) {
            return dao.findByCnpj(CNPJ) == null;
        }
        return true;
    }
}

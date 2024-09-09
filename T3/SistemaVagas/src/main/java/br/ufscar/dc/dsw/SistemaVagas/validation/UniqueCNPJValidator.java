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

    private long id;

    @Override
    public void initialize(UniqueCNPJ constraintAnnotation) {
        this.id = constraintAnnotation.id();
    }

    @Override
    public boolean isValid(String CNPJ, ConstraintValidatorContext context) {
        if (dao != null) {
            return dao.findByCnpjAndId(CNPJ, id) == null;
        }
        return true;
    }
}

package br.ufscar.dc.dsw.SistemaVagas.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.SistemaVagas.dao.IUsuarioDAO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Autowired
    IUsuarioDAO dao;

    private long id;

    @Override
    public void initialize(UniqueEmail constraintAnnotation) {
        this.id = constraintAnnotation.id();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (dao != null) {
            return dao.findByEmailAndId(email, id) == null;
        }
        return true;
    }    
}

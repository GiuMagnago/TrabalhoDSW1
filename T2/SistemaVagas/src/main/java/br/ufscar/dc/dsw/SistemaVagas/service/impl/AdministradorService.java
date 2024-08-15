package br.ufscar.dc.dsw.SistemaVagas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.SistemaVagas.dao.IAdministradorDAO;
import br.ufscar.dc.dsw.SistemaVagas.domain.Administrador;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IAdministradorService;

@Service
@Transactional(readOnly = false)
public class AdministradorService implements IAdministradorService {
    @Autowired
    IAdministradorDAO dao;

    public Administrador buscarPorEmail(String email) {
        return dao.findByEmail(email);
    }
}

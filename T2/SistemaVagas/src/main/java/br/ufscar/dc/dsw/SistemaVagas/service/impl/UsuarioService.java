package br.ufscar.dc.dsw.SistemaVagas.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.SistemaVagas.dao.IUsuarioDAO;
import br.ufscar.dc.dsw.SistemaVagas.domain.Usuario;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IUsuarioService;

@Service
@Transactional(readOnly = true)
public class UsuarioService implements IUsuarioService {
    @Autowired
    IUsuarioDAO dao;

    public Usuario buscarPorEmail(String email) {
        return dao.findByEmail(email);
    }
}

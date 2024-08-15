package br.ufscar.dc.dsw.SistemaVagas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.SistemaVagas.dao.IEmpresaDAO;
import br.ufscar.dc.dsw.SistemaVagas.domain.Empresa;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IEmpresaService;

@Service
@Transactional(readOnly = false)
public class EmpresaService implements IEmpresaService {
    
    @Autowired
    IEmpresaDAO dao;

    public void salvar(Empresa empresa) {
        dao.save(empresa);
    }

    @Transactional(readOnly = true)
    public Empresa buscarPorId(long id) {
        return dao.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Empresa> buscarTodos() {
        return dao.findAll();
    }

    public void excluir(long id) {
        dao.deleteById(id);
    }
}

package br.ufscar.dc.dsw.SistemaVagas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.SistemaVagas.dao.IProfissionalDAO;
import br.ufscar.dc.dsw.SistemaVagas.domain.Profissional;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IProfissionalService;

@Service
@Transactional(readOnly = false)
public class ProfissionalService implements IProfissionalService {
    
    @Autowired
    IProfissionalDAO dao;

    public void salvar(Profissional profissional) {
        dao.save(profissional);
    }

    @Transactional(readOnly = true)
    public Profissional buscarPorId(long id) {
        return dao.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Profissional> buscarTodos() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public Profissional buscarPorCpf(String cpf) {
        return dao.findByCpf(cpf);
    }

    public void excluir(long id) {
        dao.deleteById(id);
    }
}

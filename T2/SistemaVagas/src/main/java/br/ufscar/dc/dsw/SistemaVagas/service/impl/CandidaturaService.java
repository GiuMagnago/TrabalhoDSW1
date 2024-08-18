package br.ufscar.dc.dsw.SistemaVagas.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.SistemaVagas.dao.ICandidaturaDAO;
import br.ufscar.dc.dsw.SistemaVagas.domain.Candidatura;
import br.ufscar.dc.dsw.SistemaVagas.domain.Profissional;
import br.ufscar.dc.dsw.SistemaVagas.domain.Vaga;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.ICandidaturaService;

@Service
@Transactional(readOnly = false)
public class CandidaturaService implements ICandidaturaService {
    @Autowired
    ICandidaturaDAO dao;

    @Transactional(readOnly = true)
    public Candidatura buscarPorId(long id) {
        return dao.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Candidatura> buscarPorProfissional(Profissional profissional) {
        return dao.findByProfissional(profissional);
    }

    @Transactional(readOnly = true)
    public List<Candidatura> buscarPorVaga(Vaga vaga) {
        return dao.findByVaga(vaga);
    }

    @Transactional(readOnly = true)
    public boolean existeCandidatura(Profissional profissional, Vaga vaga) {
        return dao.existsProfissionalVaga(profissional, vaga);
    }

    public Candidatura salvar(Candidatura candidatura) {
        return dao.save(candidatura);
    }    

    public void excluir(long id) {
        dao.deleteById(id);
    }
}

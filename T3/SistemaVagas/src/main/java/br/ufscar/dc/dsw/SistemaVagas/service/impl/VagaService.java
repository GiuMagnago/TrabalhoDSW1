package br.ufscar.dc.dsw.SistemaVagas.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.SistemaVagas.dao.IVagaDAO;
import br.ufscar.dc.dsw.SistemaVagas.domain.Empresa;
import br.ufscar.dc.dsw.SistemaVagas.domain.Vaga;
import br.ufscar.dc.dsw.SistemaVagas.service.spec.IVagaService;

@Service
@Transactional(readOnly = false)
public class VagaService implements IVagaService{
    @Autowired
    IVagaDAO dao;

    @Transactional(readOnly = true)
    public Vaga buscarPorId(long id) {
        return dao.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Vaga> buscarEmAberto(Date dataAtual) {
        return dao.findByDataLimiteAfter(dataAtual);
    }

    @Transactional(readOnly = true)
    public List<Vaga> buscarPorEmpresa(Empresa empresa) {
        return dao.findByEmpresa(empresa);
    }

    @Transactional(readOnly = true)
    public List<Vaga> buscarPorEmpresaEmAberto(Empresa empresa, Date dataAtual) {
        return dao.findByEmpresaAndDataLimiteAfter(empresa, dataAtual);
    }

    @Transactional(readOnly = true)
    public List<Vaga> buscarPorCidadeEmAberto(String cidade, Date dataAtual) {
        return dao.findByEmpresaCidadeAndDataLimiteAfter(cidade, dataAtual);
    }

    @Transactional(readOnly = true)
    public List<Vaga> buscarTodos() {
        return dao.findAll();
    }

    @Transactional(readOnly = true)
    public List<String> buscarCidadesDistintas() {
        return dao.findDistinctCidade();
    }

    public Vaga salvar(Vaga vaga) {
        return dao.save(vaga);
    }

    public void excluir(long id) {
        dao.deleteById(id);
    }
}

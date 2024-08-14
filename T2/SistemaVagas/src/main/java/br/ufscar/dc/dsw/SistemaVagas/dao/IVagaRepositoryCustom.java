package br.ufscar.dc.dsw.SistemaVagas.dao;

import java.util.List;

import br.ufscar.dc.dsw.SistemaVagas.domain.Vaga;

public interface IVagaRepositoryCustom {
    List<Vaga> findVagasByCidade(String cidade);
}
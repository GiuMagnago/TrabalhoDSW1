package br.ufscar.dc.dsw.SistemaVagas.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.SistemaVagas.domain.Empresa;
import br.ufscar.dc.dsw.SistemaVagas.domain.Vaga;

public interface IVagaService {
    Vaga buscarPorId(long id);

    List<Vaga> buscarPorEmpresa(Empresa empresa);

    List<Vaga> buscarPorCidade(String cidade);

    List<Vaga> buscarTodos();

    List<String> buscarCidadesDistintas();

    Vaga salvar(Vaga vaga);

    void excluir(long id);
}
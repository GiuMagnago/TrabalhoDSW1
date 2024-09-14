package br.ufscar.dc.dsw.SistemaVagas.service.spec;

import java.util.Date;
import java.util.List;

import br.ufscar.dc.dsw.SistemaVagas.domain.Empresa;
import br.ufscar.dc.dsw.SistemaVagas.domain.Vaga;

public interface IVagaService {
    Vaga buscarPorId(long id);

    List<Vaga> buscarEmAberto(Date dataAtual);

    List<Vaga> buscarPorEmpresa(Empresa empresa);

    List<Vaga> buscarPorEmpresaEmAberto(Empresa empresa, Date dataAtual);

    List<Vaga> buscarPorCidadeEmAberto(String cidade, Date dataAtual);

    List<Vaga> buscarTodos();

    List<String> buscarCidadesDistintas();

    Vaga salvar(Vaga vaga);

    void excluir(long id);
}

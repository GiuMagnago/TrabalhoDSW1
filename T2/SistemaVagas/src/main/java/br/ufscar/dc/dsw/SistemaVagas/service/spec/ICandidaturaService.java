package br.ufscar.dc.dsw.SistemaVagas.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.SistemaVagas.domain.Candidatura;
import br.ufscar.dc.dsw.SistemaVagas.domain.Profissional;
import br.ufscar.dc.dsw.SistemaVagas.domain.Vaga;

public interface ICandidaturaService {
    Candidatura buscarPorId(long id);

    List<Candidatura> buscarPorProfissional(Profissional profissional);

    List<Candidatura> buscarPorVaga(Vaga vaga);

    Candidatura salvar(Candidatura candidatura);

    void excluir(long id);
}

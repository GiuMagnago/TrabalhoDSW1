package br.ufscar.dc.dsw.SistemaVagas.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.SistemaVagas.domain.Candidatura;
import br.ufscar.dc.dsw.SistemaVagas.domain.Profissional;
import br.ufscar.dc.dsw.SistemaVagas.domain.Vaga;

public interface ICandidaturaService {
    public Candidatura buscarPorId(long id);

    public List<Candidatura> buscarPorProfissional(Profissional profissional);

    public List<Candidatura> buscarPorVaga(Vaga vaga);

    public Candidatura salvar(Candidatura candidatura);

    public void excluir(long id);
}

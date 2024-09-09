package br.ufscar.dc.dsw.SistemaVagas.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.SistemaVagas.domain.Profissional;

public interface IProfissionalService {
    Profissional salvar(Profissional profissional);

    Profissional buscarPorId(long id);

    List<Profissional> buscarTodos();
    
    Profissional buscarPorCpf(String cpf);
    
    void excluir(long id);
}

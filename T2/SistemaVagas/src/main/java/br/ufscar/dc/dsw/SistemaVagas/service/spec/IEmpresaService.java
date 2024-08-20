package br.ufscar.dc.dsw.SistemaVagas.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.SistemaVagas.domain.Empresa;

public interface IEmpresaService {
    void salvar(Empresa empresa);

    Empresa buscarPorId(long id);

    List<Empresa> buscarTodos();

    Empresa buscarPorCnpj(String cnpj);

    void excluir(long id);
}

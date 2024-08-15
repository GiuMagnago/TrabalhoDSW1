package br.ufscar.dc.dsw.SistemaVagas.service.spec;

import br.ufscar.dc.dsw.SistemaVagas.domain.Administrador;

public interface IAdministradorService {
    Administrador buscarPorEmail(String email);
}

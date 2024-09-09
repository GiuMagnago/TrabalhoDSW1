package br.ufscar.dc.dsw.SistemaVagas.service.spec;

import br.ufscar.dc.dsw.SistemaVagas.domain.Usuario;

public interface IUsuarioService {
    Usuario buscarPorEmail(String email);
}

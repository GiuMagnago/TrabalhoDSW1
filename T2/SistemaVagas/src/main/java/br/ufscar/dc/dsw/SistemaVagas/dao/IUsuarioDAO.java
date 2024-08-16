package br.ufscar.dc.dsw.SistemaVagas.dao;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.SistemaVagas.domain.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {
    Usuario findByEmail(String email);
}
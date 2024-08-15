package br.ufscar.dc.dsw.SistemaVagas.dao;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.SistemaVagas.domain.Administrador;


public interface IAdministradorDAO extends CrudRepository<Administrador, Long> {
    Administrador findByEmail(String email);
}

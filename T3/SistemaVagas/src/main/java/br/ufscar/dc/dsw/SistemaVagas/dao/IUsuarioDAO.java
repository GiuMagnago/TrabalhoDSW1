package br.ufscar.dc.dsw.SistemaVagas.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.SistemaVagas.domain.Usuario;

public interface IUsuarioDAO extends CrudRepository<Usuario, Long> {
    List<Usuario> findAll();

    Usuario findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.email = :email AND u.id = :id")
    Usuario findByEmailAndId(String email, long id);
}
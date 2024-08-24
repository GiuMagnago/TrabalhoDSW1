package br.ufscar.dc.dsw.SistemaVagas.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.SistemaVagas.domain.Profissional;
import br.ufscar.dc.dsw.SistemaVagas.domain.Usuario;

@SuppressWarnings("unchecked")
public interface IProfissionalDAO extends CrudRepository<Profissional, Long> {
    Profissional findById(long id);

    List<Profissional> findAll();

    Profissional findByCpf(String cpf);

    @Query("SELECT p FROM Profissional p WHERE p.cpf = :cpf AND p.id = :id")
    Usuario findByCpfAndId(String cpf, long id);
    
    Profissional save(Profissional profissional);

    void deleteById(long id);
}

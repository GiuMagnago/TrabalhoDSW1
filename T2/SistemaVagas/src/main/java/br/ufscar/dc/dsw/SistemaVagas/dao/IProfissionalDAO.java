package br.ufscar.dc.dsw.SistemaVagas.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.SistemaVagas.domain.Profissional;

@SuppressWarnings("unchecked")
public interface IProfissionalDAO extends CrudRepository<Profissional, Long> {
    Profissional findById(long id);

    List<Profissional> findAll();

    Profissional findByCpf(String cpf);
    
    Profissional save(Profissional profissional);

    void deleteById(long id);
}

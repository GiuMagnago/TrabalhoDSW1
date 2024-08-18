package br.ufscar.dc.dsw.SistemaVagas.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.SistemaVagas.domain.Candidatura;
import br.ufscar.dc.dsw.SistemaVagas.domain.Profissional;
import br.ufscar.dc.dsw.SistemaVagas.domain.Vaga;

@SuppressWarnings("unchecked")
public interface ICandidaturaDAO extends CrudRepository<Candidatura, Long> {
    Candidatura findById(long id);

    List<Candidatura> findByProfissional(Profissional profissional);

    List<Candidatura> findByVaga(Vaga vaga);
    
    Candidatura save(Candidatura candidatura);

    @Query("SELECT COUNT(c) > 0 FROM Candidatura c WHERE c.profissional = :profissional AND c.vaga = :vaga")
    boolean existsProfissionalVaga(Profissional profissional, Vaga vaga);

    void deleteById(long id);
}

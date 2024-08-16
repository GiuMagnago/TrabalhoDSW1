package br.ufscar.dc.dsw.SistemaVagas.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.SistemaVagas.domain.Empresa;
import br.ufscar.dc.dsw.SistemaVagas.domain.Vaga;

@SuppressWarnings("unchecked")
public interface IVagaDAO extends CrudRepository<Vaga, Long>/*, IVagaRepositoryCustom*/ {
    Vaga findById(long id);

    List<Vaga> findByEmpresa(Empresa empresa);

    List<Vaga> findAll();

    @Query("SELECT v FROM Vaga v JOIN v.empresa e WHERE e.cidade = :cidade")
    List<Vaga> findByCidade(String cidade);
    
    Vaga save(Vaga vaga);

    void deleteById(long id);
}

package br.ufscar.dc.dsw.SistemaVagas.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.util.List;

import br.ufscar.dc.dsw.SistemaVagas.domain.Vaga;

public class VagaRepositoryImpl implements IVagaRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Vaga> findVagasByCidade(String cidade) {
        String jpql = "SELECT v FROM Vaga v JOIN v.empresa e WHERE e.cidade = :cidade";
        TypedQuery<Vaga> query = entityManager.createQuery(jpql, Vaga.class);
        query.setParameter("cidade", cidade);
        return query.getResultList();
    }
}


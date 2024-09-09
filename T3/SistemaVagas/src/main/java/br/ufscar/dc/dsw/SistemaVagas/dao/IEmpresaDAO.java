package br.ufscar.dc.dsw.SistemaVagas.dao;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.SistemaVagas.domain.Empresa;
import br.ufscar.dc.dsw.SistemaVagas.domain.Usuario;

@SuppressWarnings("unchecked")
public interface IEmpresaDAO extends CrudRepository<Empresa, Long> {
    Empresa findById(long id);

    List<Empresa> findAll();

    Empresa findByCnpj(String cnpj);

    @Query("SELECT e FROM Empresa e WHERE e.cnpj = :cnpj AND e.id = :id")
    Usuario findByCnpjAndId(String cnpj, long id);
    
    Empresa save(Empresa empresa);

    void deleteById(long id);
}

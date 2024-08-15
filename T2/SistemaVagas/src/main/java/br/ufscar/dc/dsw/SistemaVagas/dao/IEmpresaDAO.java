package br.ufscar.dc.dsw.SistemaVagas.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.SistemaVagas.domain.Empresa;

@SuppressWarnings("unchecked")
public interface IEmpresaDAO extends CrudRepository<Empresa, Long> {
    Empresa findById(long id);

    List<Empresa> findAll();
    
    List<Empresa> findByCidade(String cidade);
    
    Empresa save(Empresa empresa);

    void deleteById(long id);
}

package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Vagas;

public class CandidaturaDAO extends GenericDAO{

    public void insert(Candidatura candidatura) {
        String sql = "INSERT INTO Candidatura (id_empresa, id_profissional, status_candidatura) VALUES (?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, candidatura.getIdEmpresa());
            statement.setLong(2, candidatura.getProfissional().getId_profissional());
            statement.setString(3, candidatura.getStatusCandidatura());
            statement.executeUpdate();
            
            statement.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Candidatura candidatura) {
        String sql = "UPDATE Candidatura SET status_candidatura=? WHERE id=?";
        try {
            Connection conn = this.getConnection();
            statement.setString(1, candidatura.getStatusCandidatura());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void excluir(long id_candidatura) {
        String sql = "DELETE FROM Candidatura WHERE id=?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id_candidatura);
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Candidatura> getAllbyEmpresa() {

        List<Candidatura> listaCandidatura_Empresas = new ArrayList<>();

        String sql = "SELECT * FROM Candidatura JOIN Empresa ON Empresa.id_empresa = Candidatura.id_empresa";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Long idEmpresa = resultSet.getLong("id_empresa");
                String statusCandidatura = resultSet.getString("status_candidatura");
                Candidatura candidatura = new Candidatura(id_empresa, status_candidatura);
                listaCandidatura_Empresas.add(candidatura);
            }

            resultSet.close();
            statement.close();
            conn.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return candidatura;
    }

    public List<Candidatura> getAllbyProfissional(Profissional profissional) {

        List<Candidatura> listaCandidatura_Profissional = new ArrayList<>();

        String sql = "SELECT * FROM Candidatura JOIN Empresa, Profissional ON Empresa.id_empresa = Candidatura.id_empresa, Profissional.id_profissional = Candidatura.id_profissional";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                Long idEmpresa = resultSet.getLong("id_empresa");
                Long idProfissional = resultSet.getLong(Profissional.getId_profissional(););
                String statusCandidatura = resultSet.getString("status_candidatura");
                Candidatura candidatura = new Candidatura(id_empresa, profissional, status_candidatura);
                listaCandidatura_Profissional.add(candidatura);
            }

            resultSet.close();
            statement.close();
            conn.close();
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return candidatura;
    }

    public Candidatura get(Long id_empresa, Long id_profissional, String status candidatura){
        Candidatura candidatura = null;

        String sql = "SELECT * from Candidatura WHERE id = ?";

        try{
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id_candidatura);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                Long idEmpresa = resultSet.getLong("id_empresa");
                Long idProfissional = resultSet.getLong(Profissional.getId_profissional(););
                String statusCandidatura = resultSet.getString("status_candidatura");
                Candidatura candidatura = new Candidatura(id_empresa, profissional, status_candidatura);
                candidatura.setIdCandidatura(idEmpresa);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    return candidatura;
}

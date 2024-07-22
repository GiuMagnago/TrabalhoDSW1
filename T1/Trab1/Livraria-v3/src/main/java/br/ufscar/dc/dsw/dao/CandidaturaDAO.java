package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Candidatura;

public class CandidaturaDAO extends GenericDAO {
    public void insert(Candidatura candidatura) {
        String sql = "INSERT INTO Candidatura (id_profissional, id_vaga, statusCandidatura) VALUES (?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, candidatura.getIdProfissional());
            statement.setLong(2, candidatura.getIdVaga());
            statement.setString(3, candidatura.getStatusCandidatura());
            statement.executeUpdate();
            
            statement.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(Candidatura candidatura) {
        String sql = "UPDATE Candidatura SET status_candidatura = ? WHERE id_profissional = ? AND id_vaga = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, candidatura.getStatusCandidatura());
            statement.setLong(2, candidatura.getIdProfissional());
            statement.setLong(3, candidatura.getIdVaga());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Candidatura> getAllFromProfissional(long id_profissional) {
        List<Candidatura> listaCandidaturas = new ArrayList<>();
        String sql = "SELECT * FROM Candidatura JOIN Profissional ON Profissional.id_profissional = Candidatura.id_profissional JOIN Vaga ON Vaga.id_vaga = Candidatura.id_vaga WHERE id_profissional = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id_profissional);

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id_vaga = resultSet.getLong("id_vaga");
                String statusCandidatura = resultSet.getString("statusCandidatura");

                Candidatura vaga = new Candidatura(id_profissional, id_vaga, statusCandidatura);
                listaCandidaturas.add(vaga);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaCandidaturas;
    }

    public List<Candidatura> getAllFromVaga(long id_vaga) {
        List<Candidatura> listaCandidaturas = new ArrayList<>();
        String sql = "SELECT * FROM Candidatura JOIN Profissional ON Profissional.id_profissional = Candidatura.id_profissional JOIN Vaga ON Vaga.id_vaga = Candidatura.id_vaga WHERE id_vaga = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id_vaga);

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                long id_profissional = resultSet.getLong("id_profissional");
                String statusCandidatura = resultSet.getString("statusCandidatura");

                Candidatura vaga = new Candidatura(id_profissional, id_vaga, statusCandidatura);
                listaCandidaturas.add(vaga);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaCandidaturas;
    }

}

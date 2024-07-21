package br.ufscar.dc.dsw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.ufscar.dc.dsw.domain.Vagas;

public class VagasDAO extends GenericDAO{

    public void insert(long id_empresa, Vagas vaga) {
        String sql = "INSERT INTO Vagas (id_empresa, dataLimite, descricao) VALUES (?, ?, ?)";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);

            statement.setLong(1, id_empresa);
            statement.setDate(2, new java.sql.Date(vaga.getDataLimite().getTime()));
            statement.setString(3, vaga.getDescricao());
            statement.executeUpdate();

            statement.close();
            conn.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vagas> getAll() {
        List<Vagas> listaVagas = new ArrayList<>();
        String sql = "SELECT * FROM Vagas JOIN Empresa ON Empresa.id_empresa = Vaga.id_empresa";

        try {
            Connection conn = this.getConnection();
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                long id_empresa = resultSet.getLong("id_empresa");
                Date dataLimite = resultSet.getDate("dataLimite");
                String descricao = resultSet.getString("descricao");

                Vagas vaga = new Vagas(id_vaga, id_empresa, dataLimite, descricao);
                listaVagas.add(vaga);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaVagas;
    }

    public Vagas get(long id_vaga) {
        Vagas vaga = null;
        String sql = "SELECT * FROM Vagas WHERE id_vaga = ?";

        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id_vaga);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                long id_empresa = resultSet.getLong("id_vaga");
                Date dataLimite = resultSet.getDate("dataLimite");
                String descricao = resultSet.getString("descricao");

                vaga = new Vagas(id_empresa, dataLimite, descricao);
            }

            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return vaga;
    }


    public void update(Vagas vaga) {
        String sql = "UPDATE Vagas SET dataLimite = ?, descricao = ? WHERE id_vaga = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            
            statement.setDate(1, new java.sql.Date(vaga.getDataLimite().getTime()));
            statement.setString(2, vaga.getDescricao());
            statement.setLong(3, vaga.getId_vaga());
            statement.executeUpdate();

            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void delete(long id_vaga) {
        String sql = "DELETE FROM Vagas WHERE id_vaga = ?";
        try {
            Connection conn = this.getConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setLong(1, id_vaga);
            statement.executeUpdate();
            
            statement.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
